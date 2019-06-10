package xyz.qwerty.lobetoolapis.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import xyz.qwerty.lobetoolapis.entity.User;
import xyz.qwerty.lobetoolapis.repository.UserRepository;
import xyz.qwerty.lobetoolapis.service.AuthUserService;
import xyz.qwerty.lobetoolapis.util.Constants;
import xyz.qwerty.lobetoolapis.util.PasswordHashing;

@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Value("${jwt.secret.key}")
	private String				jwtSecretKey;

	@Value("${refresh.token.expiry.in.seconds}")
	private Long				refreshTokenExpiry;

	@Value("${access.token.expiry.in.seconds}")
	private Long				accessTokenExpiry;

	@Value("${reset.password.token.expiry.in.seconds}")
	private Long				resetPasswordTokenExpiry;

	@Value("${frontend.reset.password.url}")
	private String				resetPasswordUrl;

	@Autowired
	JavaMailSender				emailSender;

	@Autowired
	UserRepository				userRepository;

	private static final String	KEY_PERMISSIONS		= "permissions";
	private static final String	KEY_TOKEN_TYPE		= "tokenType";
	private static final String	KEY_PASSWORD_HASH	= "pHash";

	@Override
	public Boolean validateUserCredentials(String email, String password) {

		Optional<User> user = userRepository.findById(email);
		if (!user.isPresent()) {
			return false;
		}
		else {
			try {
				return user.get().getPassword().equals(PasswordHashing.getSHA256Hash(password));
			}
			catch (NoSuchAlgorithmException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing request");
			}
		}
	}

	@Override
	public String getRefreshToken(String userId) {

		if (StringUtils.isEmpty(userId)) {
			throw new IllegalArgumentException("Cannot create JWT Token without username");
		}

		Claims claims = Jwts.claims().setSubject(userId);
		claims.put(KEY_PERMISSIONS, getPermissions(userId));
		claims.put(KEY_TOKEN_TYPE, Constants.TOKEN_TYPE_REFRESH);

		long now = System.currentTimeMillis();
		long expiryTime = now + refreshTokenExpiry * 1000;

		return Jwts.builder().setClaims(claims).setIssuer("ankit").setIssuedAt(new Date()).setExpiration(new Date(expiryTime)).signWith(SignatureAlgorithm.HS512, jwtSecretKey)
				.compact();
	}

	@Override
	public String getAccessToken(String refreshToken) {

		Claims tokenClaims = getClaimsFromToken(refreshToken);

		if (!checkTokenTypeAndExpiry(tokenClaims, Constants.TOKEN_TYPE_REFRESH)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "WRONG_TOKEN_TYPE");
		}

		String userId = tokenClaims.getSubject();

		Claims claims = Jwts.claims().setSubject(userId);
		claims.put(KEY_PERMISSIONS, tokenClaims.get(KEY_PERMISSIONS));
		claims.put(KEY_TOKEN_TYPE, Constants.TOKEN_TYPE_ACCESS);

		long now = System.currentTimeMillis();
		long expiryTime = now + accessTokenExpiry * 1000;

		return Jwts.builder().setClaims(claims).setIssuer("ankit").setIssuedAt(new Date()).setExpiration(new Date(expiryTime)).signWith(SignatureAlgorithm.HS512, jwtSecretKey)
				.compact();
	}

	@Override
	public String getAccessTokenFromHeader(String authorization) {

		String[] auth = authorization.split(" ");
		if (!"Bearer".equals(auth[0])) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid authorization type");
		}
		return auth[1];
	}

	@Override
	public String getUserId(String accessToken) {

		Claims tokenClaims = getClaimsFromToken(accessToken);

		return tokenClaims.getSubject();
	}

	@Override
	public Boolean checkUserAccess(String accessToken, String permission) {

		Claims tokenClaims = getClaimsFromToken(accessToken);

		if (!checkTokenTypeAndExpiry(tokenClaims, Constants.TOKEN_TYPE_ACCESS) || !tokenClaims.get(KEY_PERMISSIONS, List.class).contains(permission)) {
			return false;
		}

		return true;
	}

	@Override
	public String getRole(String email) {

		return userRepository.findById(email).get().getUserRole().stream().map(ur -> ur.getUserRoleKey().getRole().getName()).limit(1).collect(Collectors.toList()).get(0);
	}

	@Override
	public Boolean sendPasswordResetMail(String email) {

		Optional<User> user = userRepository.findById(email);
		if (user.isPresent()) {

			User u = user.get();

			Claims claims = Jwts.claims().setSubject(u.getEmail());
			claims.put(KEY_TOKEN_TYPE, Constants.TOKEN_TYPE_PASSWORD_RESET);
			claims.put(KEY_PASSWORD_HASH, DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));

			long now = System.currentTimeMillis();
			long expiryTime = now + resetPasswordTokenExpiry * 1000;

			String token = Jwts.builder().setClaims(claims).setIssuer("ankit").setIssuedAt(new Date()).setExpiration(new Date(expiryTime))
					.signWith(SignatureAlgorithm.HS512, jwtSecretKey).compact();

			StringBuilder sb = new StringBuilder();
			sb.append("Click on the following link to reset your password");
			sb.append("\n\n");
			sb.append(resetPasswordUrl + "token=" + token);

			ExecutorService executorService = Executors.newFixedThreadPool(1);

			executorService.execute(new Runnable() {

				@Override
				public void run() {

					SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(email);
					message.setSubject("LOBE Tool password reset");
					message.setText(sb.toString());
					emailSender.send(message);
				}
			});

			return true;
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email doesn't exists");
		}
	}

	@Override
	public User checkResetTokenValidity(String token) {

		Claims tokenClaims = getClaimsFromToken(token);

		if (!checkTokenTypeAndExpiry(tokenClaims, Constants.TOKEN_TYPE_PASSWORD_RESET)) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token type");
		}

		String email = tokenClaims.getSubject();

		Optional<User> user = userRepository.findById(email);
		if (user.isPresent()) {

			User u = user.get();

			String tokenPasswordHash = tokenClaims.get(KEY_PASSWORD_HASH, String.class);

			String passwordHash = DigestUtils.md5DigestAsHex(u.getPassword().getBytes());

			if (passwordHash.equals(tokenPasswordHash)) {

				return u;
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password already changed");
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email doesn't exists");
		}
	}

	@Override
	public Boolean resetPassword(User user) {

		userRepository.save(user);

		return true;
	}

	private List<String> getPermissions(String userId) {

		List<String> permissions = new ArrayList<>();

		Optional<User> optionalUser = userRepository.findById(userId);

		optionalUser.ifPresent(user -> user.getUserRole()
				.forEach(userRole -> userRole.getUserRoleKey().getRole().getRolePermission().forEach(rolePermission -> permissions.add(rolePermission.getPermission().getName()))));

		return permissions;
	}

	private Claims getClaimsFromToken(String token) {

		try {
			return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	private Boolean checkTokenTypeAndExpiry(Claims tokenClaims, String tokenType) {

		if (!tokenType.equals(tokenClaims.get(KEY_TOKEN_TYPE, String.class))) {
			return false;
		}
		return true;
	}

}
