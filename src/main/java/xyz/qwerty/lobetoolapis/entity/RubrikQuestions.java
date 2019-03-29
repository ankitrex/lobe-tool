// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rubrik_questions", indexes = { @Index(name = "rubrik_questions_id_IX", columnList = "id", unique = true) })
@Getter
@Setter
public class RubrikQuestions implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Column(unique = true, nullable = false, precision = 10)
	private int					id;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id", nullable = false)
	private QuestionMaster		questionMaster;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "rubrik_id", nullable = false)
	private Rubrik				rubrik;
}
