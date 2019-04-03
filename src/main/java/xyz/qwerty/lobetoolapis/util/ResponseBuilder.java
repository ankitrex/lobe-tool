package xyz.qwerty.lobetoolapis.util;

import lombok.Data;

@Data
public class ResponseBuilder {

	private String	status;
	private Integer	code;
	private String	message;
	private String	developerMessage;
	private Object	data;
	private String	accessToken;
	private String	refreshToken;
	private String	userId;
}
