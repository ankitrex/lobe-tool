// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "lobe_scores")
public class LobeScores implements Serializable {

	/** Primary key. */
	protected static final String PK = "LobeScoresPrimary";

	@Column(nullable = false, precision = 10)
	private int score;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "learning_object_id", nullable = false)
	private LearningObject learningObject;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id", nullable = false)
	private QuestionMaster questionMaster;

	/** Default constructor. */
	public LobeScores() {
		super();
	}

	/**
	 * Access method for score.
	 *
	 * @return the current value of score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter method for score.
	 *
	 * @param aScore the new value for score
	 */
	public void setScore(int aScore) {
		score = aScore;
	}

	/**
	 * Access method for learningObject.
	 *
	 * @return the current value of learningObject
	 */
	public LearningObject getLearningObject() {
		return learningObject;
	}

	/**
	 * Setter method for learningObject.
	 *
	 * @param aLearningObject the new value for learningObject
	 */
	public void setLearningObject(LearningObject aLearningObject) {
		learningObject = aLearningObject;
	}

	/**
	 * Access method for questionMaster.
	 *
	 * @return the current value of questionMaster
	 */
	public QuestionMaster getQuestionMaster() {
		return questionMaster;
	}

	/**
	 * Setter method for questionMaster.
	 *
	 * @param aQuestionMaster the new value for questionMaster
	 */
	public void setQuestionMaster(QuestionMaster aQuestionMaster) {
		questionMaster = aQuestionMaster;
	}

	/** Temporary value holder for group key fragment learningObjectCode */
	private transient String tempLearningObjectCode;

	/**
	 * Gets the key fragment code for member learningObject. If this.learningObject
	 * is null, a temporary stored value for the key fragment will be returned. The
	 * temporary value is set by setLearningObjectCode. This behavior is required by
	 * some persistence libraries to allow fetching of objects in arbitrary order.
	 *
	 * @return Current (or temporary) value of the key fragment
	 * @see LearningObject
	 */
	public String getLearningObjectCode() {
		return (getLearningObject() == null ? tempLearningObjectCode : getLearningObject().getCode());
	}

	/**
	 * Sets the key fragment code from member learningObject. If this.learningObject
	 * is null, the passed value will be temporary stored, and returned by
	 * subsequent calls to getLearningObjectCode. This behaviour is required by some
	 * persistence libraries to allow fetching of objects in arbitrary order.
	 *
	 * @param aCode New value for the key fragment
	 * @see LearningObject
	 */
	public void setLearningObjectCode(String aCode) {
		if (getLearningObject() == null) {
			tempLearningObjectCode = aCode;
		} else {
			getLearningObject().setCode(aCode);
		}
	}

	/** Temporary value holder for group key fragment questionMasterId */
	private transient int tempQuestionMasterId;

	/**
	 * Gets the key fragment id for member questionMaster. If this.questionMaster is
	 * null, a temporary stored value for the key fragment will be returned. The
	 * temporary value is set by setQuestionMasterId. This behavior is required by
	 * some persistence libraries to allow fetching of objects in arbitrary order.
	 *
	 * @return Current (or temporary) value of the key fragment
	 * @see QuestionMaster
	 */
	public int getQuestionMasterId() {
		return (getQuestionMaster() == null ? tempQuestionMasterId : getQuestionMaster().getId());
	}

	/**
	 * Sets the key fragment id from member questionMaster. If this.questionMaster
	 * is null, the passed value will be temporary stored, and returned by
	 * subsequent calls to getQuestionMasterId. This behaviour is required by some
	 * persistence libraries to allow fetching of objects in arbitrary order.
	 *
	 * @param aId New value for the key fragment
	 * @see QuestionMaster
	 */
	public void setQuestionMasterId(int aId) {
		if (getQuestionMaster() == null) {
			tempQuestionMasterId = aId;
		} else {
			getQuestionMaster().setId(aId);
		}
	}

	/**
	 * Compares the key for this instance with another LobeScores.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class LobeScores and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LobeScores)) {
			return false;
		}
		LobeScores that = (LobeScores) other;
		Object myLearningObjectCode = this.getLearningObjectCode();
		Object yourLearningObjectCode = that.getLearningObjectCode();
		if (myLearningObjectCode == null ? yourLearningObjectCode != null
				: !myLearningObjectCode.equals(yourLearningObjectCode)) {
			return false;
		}
		if (this.getQuestionMasterId() != that.getQuestionMasterId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another LobeScores.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof LobeScores))
			return false;
		return this.equalKeys(other) && ((LobeScores) other).equalKeys(this);
	}

	/**
	 * Returns a hash code for this instance.
	 *
	 * @return Hash code
	 */
	@Override
	public int hashCode() {
		int i;
		int result = 17;
		if (getLearningObjectCode() == null) {
			i = 0;
		} else {
			i = getLearningObjectCode().hashCode();
		}
		result = 37 * result + i;
		i = getQuestionMasterId();
		result = 37 * result + i;
		return result;
	}

	/**
	 * Returns a debug-friendly String representation of this instance.
	 *
	 * @return String representation of this instance
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[LobeScores |");
		sb.append(" learningObjectCode=").append(getLearningObjectCode());
		sb.append(" questionMasterId=").append(getQuestionMasterId());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Return all elements of the primary key.
	 *
	 * @return Map of key names to values
	 */
	public Map<String, Object> getPrimaryKey() {
		Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
		ret.put("learningObjectCode", getLearningObjectCode());
		ret.put("questionMasterId", Integer.valueOf(getQuestionMasterId()));
		return ret;
	}

}
