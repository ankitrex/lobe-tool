// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "lobe_scores")
@Getter
@Setter
public class LobeScores implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Column(nullable = false, precision = 10)
	private int					score;

	@EmbeddedId
	private LobeScoresKey		lobeScoresKey;
}
