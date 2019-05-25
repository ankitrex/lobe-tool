package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "lobe_temp")
@Getter
@Setter
public class LobeTemp implements Serializable{

	private static final long				serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int								id;

	@Column(name = "code", nullable = false)
	private String					code;

	@Column(name = "assigned_by", nullable = false)
	private String					assignedBy;
	
	@Column(name = "assigned_to", nullable = false)
	private String					assignedTo;
	
	@Column(name = "rubrik_id", nullable = false)
	private Integer					rubrikId;
	
	@Column(name = "learning_object_name", nullable = false)
	private String					learningObjectName;
	
	@Column(name = "created_ts", nullable = false)
	private LocalDateTime					createdTs;
}
