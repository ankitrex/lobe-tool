package xyz.qwerty.lobetoolapis.controller;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.entity.User;
import xyz.qwerty.lobetoolapis.service.AuthUserService;
import xyz.qwerty.lobetoolapis.service.UserService;
import xyz.qwerty.lobetoolapis.util.PasswordHashing;
import xyz.qwerty.lobetoolapis.util.ResponseBuilder;
import xyz.qwerty.lobetoolapis.vo.UserVo;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService		userService;

	@Autowired
	AuthUserService	authUserService;

	@PostMapping("/register")
	public ResponseEntity<ResponseBuilder> register(@RequestParam(name = "email") String email, @RequestParam(name = "name") String name,
			@RequestParam(name = "password") String password, @RequestParam(name = "affiliation") String affiliation, Integer roleId) {

		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setAffiliation(affiliation);
		user.setStatus("ACTIVE");
		user.setCreatedTs(LocalDateTime.now());
		try {
			user.setPassword(PasswordHashing.getSHA256Hash(password));
		}
		catch (NoSuchAlgorithmException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing request");
		}

		String userId = userService.createUser(user, roleId);

		ResponseBuilder responseBuilder = new ResponseBuilder();
		responseBuilder.setCode(HttpStatus.CREATED.value());
		responseBuilder.setStatus(HttpStatus.CREATED.getReasonPhrase());
		responseBuilder.setUserId(userId);

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<ResponseBuilder> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		if (!authUserService.validateUserCredentials(email, password)) {

			responseBuilder.setCode(HttpStatus.BAD_REQUEST.value());
			responseBuilder.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			responseBuilder.setMessage("Invalid credentials");
		}
		else {
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());

			String refreshToken = authUserService.getRefreshToken(email);
			String accessToken = authUserService.getAccessToken(refreshToken);

			responseBuilder.setRefreshToken(refreshToken);
			responseBuilder.setAccessToken(accessToken);
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/token-refresh")
	public ResponseEntity<ResponseBuilder> tokenRefresh(@RequestHeader(name = "Authorization") String authorization) {

		String accessToken = authUserService.getAccessToken(authUserService.getAccessTokenFromHeader(authorization));

		ResponseBuilder responseBuilder = new ResponseBuilder();
		responseBuilder.setCode(HttpStatus.OK.value());
		responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		responseBuilder.setAccessToken(accessToken);

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/profile")
	ResponseEntity<ResponseBuilder> userProfile(@RequestHeader(name = "Authorization") String authorization) {

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);

		String userId = authUserService.getUserId(accessToken);

		UserVo userVo = userService.getUserProfile(userId);

		ResponseBuilder responseBuilder = new ResponseBuilder();
		responseBuilder.setCode(HttpStatus.OK.value());
		responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		responseBuilder.setData(userVo);

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

}
