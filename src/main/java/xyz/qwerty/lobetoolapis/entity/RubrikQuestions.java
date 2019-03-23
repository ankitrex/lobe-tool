// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Entity
@Table(name="rubrik_questions", indexes={@Index(name="rubrik_questions_id_IX", columnList="id", unique=true)})
public class RubrikQuestions implements Serializable {

    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="question_id", nullable=false)
    private QuestionMaster questionMaster;
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="rubrik_id", nullable=false)
    private Rubrik rubrik;

    /** Default constructor. */
    public RubrikQuestions() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
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

    /**
     * Access method for rubrik.
     *
     * @return the current value of rubrik
     */
    public Rubrik getRubrik() {
        return rubrik;
    }

    /**
     * Setter method for rubrik.
     *
     * @param aRubrik the new value for rubrik
     */
    public void setRubrik(Rubrik aRubrik) {
        rubrik = aRubrik;
    }

    /** Temporary value holder for group key fragment rubrikId */
    private transient int tempRubrikId;

    /**
     * Gets the key fragment id for member rubrik.
     * If this.rubrik is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setRubrikId.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see Rubrik
     */
    public int getRubrikId() {
        return (getRubrik() == null ? tempRubrikId : getRubrik().getId());
    }

    /**
     * Sets the key fragment id from member rubrik.
     * If this.rubrik is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getRubrikId.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @param aId New value for the key fragment
     * @see Rubrik
     */
    public void setRubrikId(int aId) {
        if (getRubrik() == null) {
            tempRubrikId = aId;
        } else {
            getRubrik().setId(aId);
        }
    }

    /** Temporary value holder for group key fragment questionMasterId */
    private transient int tempQuestionMasterId;

    /**
     * Gets the key fragment id for member questionMaster.
     * If this.questionMaster is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setQuestionMasterId.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see QuestionMaster
     */
    public int getQuestionMasterId() {
        return (getQuestionMaster() == null ? tempQuestionMasterId : getQuestionMaster().getId());
    }

    /**
     * Sets the key fragment id from member questionMaster.
     * If this.questionMaster is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getQuestionMasterId.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
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
     * Compares the key for this instance with another RubrikQuestions.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class RubrikQuestions and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof RubrikQuestions)) {
            return false;
        }
        RubrikQuestions that = (RubrikQuestions) other;
        if (this.getRubrikId() != that.getRubrikId()) {
            return false;
        }
        if (this.getQuestionMasterId() != that.getQuestionMasterId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another RubrikQuestions.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RubrikQuestions)) return false;
        return this.equalKeys(other) && ((RubrikQuestions)other).equalKeys(this);
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
        i = getRubrikId();
        result = 37*result + i;
        i = getQuestionMasterId();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[RubrikQuestions |");
        sb.append(" rubrikId=").append(getRubrikId());
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
        ret.put("rubrikId", Integer.valueOf(getRubrikId()));
        ret.put("questionMasterId", Integer.valueOf(getQuestionMasterId()));
        return ret;
    }

}
