package xyz.qwerty.lobetoolapis.service;

public interface AuthUserService {

	Boolean validateUserCredentials(String email, String password);

	String getRefreshToken(String userId);

	String getAccessToken(String refreshToken);

	String getAccessTokenFromHeader(String authorization);

	String getUserId(String accessToken);

	Boolean checkUserAccess(String accessToken, String permission);
}
