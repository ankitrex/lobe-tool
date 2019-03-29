// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "question_master")
@Getter
@Setter
public class QuestionMaster implements Serializable {

	private static final long		serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int						id;

	@Column(nullable = false, length = 100)
	private String					question;

	@Column(name = "question_meta", nullable = false, length = 1000)
	private String					questionMeta;

	@Column(name = "score_0", nullable = false, length = 1000)
	private String					score0;

	@Column(name = "score_0_images", nullable = false, length = 1000)
	private String					score0Images;

	@Column(name = "score_1", nullable = false, length = 1000)
	private String					score1;

	@Column(name = "score_1_images", nullable = false, length = 1000)
	private String					score1Images;

	@Column(name = "score_2", nullable = false, length = 1000)
	private String					score2;

	@Column(name = "score_2_images", nullable = false, length = 1000)
	private String					score2Images;

	@Column(name = "score_3", nullable = false, length = 1000)
	private String					score3;

	@Column(name = "score_3_images", nullable = false, length = 1000)
	private String					score3Images;

	@Column(name = "rubrik_type", nullable = false, length = 20)
	private String					rubrikType;

	@Column(name = "quality_dimension", nullable = false, length = 50)
	private String					qualityDimension;

	@Column(nullable = false, length = 3)
	private boolean					optional;

	@OneToMany(mappedBy = "questionMaster")
	private Set<LobeScores>			lobeScores;

	@OneToMany(mappedBy = "questionMaster")
	private Set<RubrikQuestions>	rubrikQuestions;
}
