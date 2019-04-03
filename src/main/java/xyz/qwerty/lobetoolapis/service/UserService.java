package xyz.qwerty.lobetoolapis.service;

import xyz.qwerty.lobetoolapis.entity.User;
import xyz.qwerty.lobetoolapis.vo.UserVo;

public interface UserService {

	String createUser(User user, Integer roleId);

	UserVo getUserProfile(String userId);
}
