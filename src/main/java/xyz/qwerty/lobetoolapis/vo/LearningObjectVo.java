package xyz.qwerty.lobetoolapis.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class LearningObjectVo {
	
	private Integer id;

	private String				code;

	private String				name;

	private String				grade;

	private String				subject;

	private String				chapter;

	private String				repositoryName;

	private LocalDateTime		createdTs;

	private LocalDateTime		updatedTs;

	private String				status;

	private String				assignedBy;

	private String				assignedTo;

	private Integer				rubrikId;

	private String				rubrikName;

	private Double				percentage;

	private List<DimensionVo>	dimensionVos;
}
