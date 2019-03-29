package xyz.qwerty.lobetoolapis.service;

import xyz.qwerty.lobetoolapis.entity.User;

public interface UserService {

	String createUser(User user, Integer roleId);
}
