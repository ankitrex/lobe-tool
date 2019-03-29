package xyz.qwerty.lobetoolapis.util;

import java.util.List;

import lombok.Data;

@Data
public class ResponseBuilder {

	private String	status;
	private Integer	code;
	private String	message;
	private String	developerMessage;
	private Object	object;
	private List<?>	list;
	private String	accessToken;
	private String	refreshToken;
	private String	userId;
}
