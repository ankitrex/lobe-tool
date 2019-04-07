// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "learning_object")
@Getter
@Setter
public class LearningObject implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@Column(unique = true, nullable = false, length = 200)
	private String				code;

	@Column(nullable = false, length = 100)
	private String				name;

	@Column(nullable = true, length = 100)
	private String				grade;

	@Column(nullable = true, length = 100)
	private String				subject;

	@Column(nullable = true, length = 100)
	private String				chapter;

	@Column(name = "repository_name", nullable = true, length = 100)
	private String				repositoryName;
	
	@Column(name = "module_name", nullable = true, length = 100)
	private String				moduleName;

	@Column(name = "created_ts", nullable = false)
	private LocalDateTime		createdTs;

	@Column(name = "updated_ts", nullable = true)
	private LocalDateTime		updatedTs;

	@Column(nullable = false, length = 20)
	private String				status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "assigned_by", nullable = false)
	private User				user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "assigned_to", nullable = false)
	private User				user2;

	@ManyToOne(optional = false)
	@JoinColumn(name = "rubrik_id", nullable = false)
	private Rubrik				rubrik;

	@OneToMany(mappedBy = "learningObject")
	private Set<LobeScores>		lobeScores;
}
