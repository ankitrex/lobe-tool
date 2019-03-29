// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "rubrik")
@Getter
@Setter
public class Rubrik implements Serializable {

	private static final long				serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int								id;

	@Column(nullable = false, length = 100)
	private String							name;

	@Column(nullable = false, length = 20)
	private String							type;

	@Column(nullable = false, length = 20)
	private String							status;

	@Column(name = "created_ts", nullable = false)
	private LocalDateTime					createdTs;

	@Column(name = "updated_ts", nullable = false)
	private LocalDateTime					updatedTs;

	@OneToMany(mappedBy = "rubrik")
	private Set<LearningObject>				learningObject;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_email", nullable = false)
	private User							user;

	@OneToMany(mappedBy = "rubrik")
	private Set<RubrikQualityDimensions>	rubrikQualityDimensions;

	@OneToMany(mappedBy = "rubrik")
	private Set<RubrikQuestions>			rubrikQuestions;
}
