// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rubrik_questions")
@Getter
@Setter
public class RubrikQuestions implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@EmbeddedId
	private RubrikQuestionsKey	rubrikQuestionsKey;
}
