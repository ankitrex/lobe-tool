package xyz.qwerty.lobetoolapis.vo;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import xyz.qwerty.lobetoolapis.entity.LobeScores;
import xyz.qwerty.lobetoolapis.entity.Rubrik;
import xyz.qwerty.lobetoolapis.entity.User;

@Data
public class LearningObjectVo {
	
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
	
	private String 				rubrikName;
	
	private Double 				percentage;
}