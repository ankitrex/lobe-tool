package xyz.qwerty.lobetoolapis.vo;

import java.util.List;

import lombok.Data;

@Data
public class UserVo {

	private String	email;

	private String	name;

	private String	affiliation;

	private String	status;
	
	private List<String> 	roles;
}
