package xyz.qwerty.lobetoolapis.service;

import xyz.qwerty.lobetoolapis.entity.User;

public interface AuthUserService {

	Boolean validateUserCredentials(String email, String password);

	String getRefreshToken(String userId);

	String getAccessToken(String refreshToken);

	String getAccessTokenFromHeader(String authorization);

	String getUserId(String accessToken);

	Boolean checkUserAccess(String accessToken, String permission);

	String getRole(String email);

	Boolean sendPasswordResetMail(String email);

	User checkResetTokenValidity(String token);

	Boolean resetPassword(User user);
}
