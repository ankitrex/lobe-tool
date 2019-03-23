// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.Data;

@Entity(name="rubrik")
public class Rubrik implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @Column(nullable=false, length=100)
    private String name;
    
    @Column(nullable=false, length=20)
    private String type;
    
    @Column(nullable=false, length=20)
    private String status;
    
    @Column(name="created_ts", nullable=false)
    private LocalDateTime createdTs;
    
    @Column(name="updated_ts", nullable=false)
    private LocalDateTime updatedTs;
    
    @OneToMany(mappedBy="rubrik")
    private Set<LearningObject> learningObject;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="user_email", nullable=false)
    private User user;
    
    @OneToMany(mappedBy="rubrik")
    private Set<RubrikQualityDimensions> rubrikQualityDimensions;
    
    @OneToMany(mappedBy="rubrik")
    private Set<RubrikQuestions> rubrikQuestions;

    /** Default constructor. */
    public Rubrik() {
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
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for type.
     *
     * @return the current value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for type.
     *
     * @param aType the new value for type
     */
    public void setType(String aType) {
        type = aType;
    }

    /**
     * Access method for status.
     *
     * @return the current value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for status.
     *
     * @param aStatus the new value for status
     */
    public void setStatus(String aStatus) {
        status = aStatus;
    }

    /**
     * Access method for createdTs.
     *
     * @return the current value of createdTs
     */
    public LocalDateTime getCreatedTs() {
        return createdTs;
    }

    /**
     * Setter method for createdTs.
     *
     * @param aCreatedTs the new value for createdTs
     */
    public void setCreatedTs(LocalDateTime aCreatedTs) {
        createdTs = aCreatedTs;
    }

    /**
     * Access method for updatedTs.
     *
     * @return the current value of updatedTs
     */
    public LocalDateTime getUpdatedTs() {
        return updatedTs;
    }

    /**
     * Setter method for updatedTs.
     *
     * @param aUpdatedTs the new value for updatedTs
     */
    public void setUpdatedTs(LocalDateTime aUpdatedTs) {
        updatedTs = aUpdatedTs;
    }

    /**
     * Access method for learningObject.
     *
     * @return the current value of learningObject
     */
    public Set<LearningObject> getLearningObject() {
        return learningObject;
    }

    /**
     * Setter method for learningObject.
     *
     * @param aLearningObject the new value for learningObject
     */
    public void setLearningObject(Set<LearningObject> aLearningObject) {
        learningObject = aLearningObject;
    }

    /**
     * Access method for user.
     *
     * @return the current value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter method for user.
     *
     * @param aUser the new value for user
     */
    public void setUser(User aUser) {
        user = aUser;
    }

    /**
     * Access method for rubrikQualityDimensions.
     *
     * @return the current value of rubrikQualityDimensions
     */
    public Set<RubrikQualityDimensions> getRubrikQualityDimensions() {
        return rubrikQualityDimensions;
    }

    /**
     * Setter method for rubrikQualityDimensions.
     *
     * @param aRubrikQualityDimensions the new value for rubrikQualityDimensions
     */
    public void setRubrikQualityDimensions(Set<RubrikQualityDimensions> aRubrikQualityDimensions) {
        rubrikQualityDimensions = aRubrikQualityDimensions;
    }

    /**
     * Access method for rubrikQuestions.
     *
     * @return the current value of rubrikQuestions
     */
    public Set<RubrikQuestions> getRubrikQuestions() {
        return rubrikQuestions;
    }

    /**
     * Setter method for rubrikQuestions.
     *
     * @param aRubrikQuestions the new value for rubrikQuestions
     */
    public void setRubrikQuestions(Set<RubrikQuestions> aRubrikQuestions) {
        rubrikQuestions = aRubrikQuestions;
    }

    /**
     * Compares the key for this instance with another Rubrik.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Rubrik and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Rubrik)) {
            return false;
        }
        Rubrik that = (Rubrik) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Rubrik.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Rubrik)) return false;
        return this.equalKeys(other) && ((Rubrik)other).equalKeys(this);
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
        i = getId();
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
        StringBuffer sb = new StringBuffer("[Rubrik |");
        sb.append(" id=").append(getId());
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
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
