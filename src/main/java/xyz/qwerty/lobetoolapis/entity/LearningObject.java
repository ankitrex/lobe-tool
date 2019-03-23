// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="learning_object")
public class LearningObject implements Serializable {

    /** Primary key. */
    protected static final String PK = "code";

    @Id
    @Column(unique=true, nullable=false, length=200)
    private String code;
    
    @Column(nullable=false, length=100)
    private String name;
    
    @Column(nullable=false, length=100)
    private String grade;
    
    @Column(nullable=false, length=100)
    private String subject;
    
    @Column(nullable=false, length=100)
    private String chapter;
    
    @Column(name="repository_name", nullable=false, length=100)
    private String repositoryName;
    
    @Column(name="created_ts", nullable=false)
    private LocalDateTime createdTs;
    
    @Column(name="updated_ts", nullable=false)
    private LocalDateTime updatedTs;
    
    @Column(nullable=false, length=20)
    private String status;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="assigned_by", nullable=false)
    private User user;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="assigned_to", nullable=false)
    private User user2;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="rubrik_id", nullable=false)
    private Rubrik rubrik;
    
    @OneToMany(mappedBy="learningObject")
    private Set<LobeScores> lobeScores;

    /** Default constructor. */
    public LearningObject() {
        super();
    }

    /**
     * Access method for code.
     *
     * @return the current value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for code.
     *
     * @param aCode the new value for code
     */
    public void setCode(String aCode) {
        code = aCode;
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
     * Access method for grade.
     *
     * @return the current value of grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Setter method for grade.
     *
     * @param aGrade the new value for grade
     */
    public void setGrade(String aGrade) {
        grade = aGrade;
    }

    /**
     * Access method for subject.
     *
     * @return the current value of subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setter method for subject.
     *
     * @param aSubject the new value for subject
     */
    public void setSubject(String aSubject) {
        subject = aSubject;
    }

    /**
     * Access method for chapter.
     *
     * @return the current value of chapter
     */
    public String getChapter() {
        return chapter;
    }

    /**
     * Setter method for chapter.
     *
     * @param aChapter the new value for chapter
     */
    public void setChapter(String aChapter) {
        chapter = aChapter;
    }

    /**
     * Access method for repositoryName.
     *
     * @return the current value of repositoryName
     */
    public String getRepositoryName() {
        return repositoryName;
    }

    /**
     * Setter method for repositoryName.
     *
     * @param aRepositoryName the new value for repositoryName
     */
    public void setRepositoryName(String aRepositoryName) {
        repositoryName = aRepositoryName;
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
     * Access method for user2.
     *
     * @return the current value of user2
     */
    public User getUser2() {
        return user2;
    }

    /**
     * Setter method for user2.
     *
     * @param aUser2 the new value for user2
     */
    public void setUser2(User aUser2) {
        user2 = aUser2;
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

    /**
     * Access method for lobeScores.
     *
     * @return the current value of lobeScores
     */
    public Set<LobeScores> getLobeScores() {
        return lobeScores;
    }

    /**
     * Setter method for lobeScores.
     *
     * @param aLobeScores the new value for lobeScores
     */
    public void setLobeScores(Set<LobeScores> aLobeScores) {
        lobeScores = aLobeScores;
    }

    /**
     * Compares the key for this instance with another LearningObject.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class LearningObject and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof LearningObject)) {
            return false;
        }
        LearningObject that = (LearningObject) other;
        Object myCode = this.getCode();
        Object yourCode = that.getCode();
        if (myCode==null ? yourCode!=null : !myCode.equals(yourCode)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another LearningObject.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LearningObject)) return false;
        return this.equalKeys(other) && ((LearningObject)other).equalKeys(this);
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
        if (getCode() == null) {
            i = 0;
        } else {
            i = getCode().hashCode();
        }
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
        StringBuffer sb = new StringBuffer("[LearningObject |");
        sb.append(" code=").append(getCode());
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
        ret.put("code", getCode());
        return ret;
    }

}
