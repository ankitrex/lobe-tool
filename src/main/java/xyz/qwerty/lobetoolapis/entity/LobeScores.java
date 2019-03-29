// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "lobe_scores")
@Getter
@Setter
public class LobeScores implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Column(nullable = false, precision = 10)
	private int					score;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "learning_object_id", nullable = false)
	private LearningObject		learningObject;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id", nullable = false)
	private QuestionMaster		questionMaster;
}
