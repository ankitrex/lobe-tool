package xyz.qwerty.lobetoolapis.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.entity.Role;
import xyz.qwerty.lobetoolapis.entity.User;
import xyz.qwerty.lobetoolapis.entity.UserRole;
import xyz.qwerty.lobetoolapis.entity.UserRoleKey;
import xyz.qwerty.lobetoolapis.repository.RoleRepository;
import xyz.qwerty.lobetoolapis.repository.UserRepository;
import xyz.qwerty.lobetoolapis.repository.UserRoleRepository;
import xyz.qwerty.lobetoolapis.service.UserService;
import xyz.qwerty.lobetoolapis.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	RoleRepository		roleRepository;

	@Autowired
	UserRepository		userRepository;

	@Autowired
	UserRoleRepository	userRoleRepository;

	@Override
	public String createUser(User user, Integer roleId) {

		if (!isRoleIdValid(roleId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Role Id");
		}
		if (userExists(user.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists");
		}

		userRepository.save(user);

		Role role = new Role();
		role.setId(roleId);

		UserRoleKey userRoleKey = new UserRoleKey();
		userRoleKey.setRole(role);
		userRoleKey.setUser(user);

		UserRole userRole = new UserRole();
		userRole.setUserRoleKey(userRoleKey);

		userRoleRepository.save(userRole);

		return user.getEmail();
	}

	private Boolean isRoleIdValid(Integer roleId) {

		return roleRepository.existsById(roleId);
	}

	private Boolean userExists(String emailId) {

		return userRepository.existsById(emailId);
	}

	@Override
	public UserVo getUserProfile(String userId) {

		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {

			User u = user.get();
			UserVo userVo = new UserVo();
			userVo.setEmail(u.getEmail());
			userVo.setName(u.getName());
			userVo.setAffiliation(u.getAffiliation());
			userVo.setStatus(u.getStatus());
			userVo.setRoles(u.getUserRole().stream().map(r -> r.getUserRoleKey().getRole().getName()).collect(Collectors.toList()));
			return userVo;
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user");
		}
	}

}
