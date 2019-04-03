package xyz.qwerty.lobetoolapis.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RubrikVo {

	private Integer			id;
	private String			name;
	private String			status;
	private LocalDateTime	createdTs;
	private LocalDateTime	updatedTs;
}
