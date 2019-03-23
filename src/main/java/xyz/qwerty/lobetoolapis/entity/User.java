// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="user")
public class User implements Serializable {

    @Id
    @Column(unique=true, nullable=false, length=100)
    private String email;
    
    @Column(nullable=false, length=100)
    private String name;
    
    @Column(nullable=false, length=100)
    private String password;
    
    @Column(nullable=false, length=100)
    private String afiliation;
    
    @Column(nullable=false, length=20)
    private String status;
    
    @OneToMany(mappedBy="user")
    private Set<LearningObject> learningObject;
    
    @OneToMany(mappedBy="user2")
    private Set<LearningObject> learningObject2;
    
    @OneToMany(mappedBy="user")
    private Set<Rubrik> rubrik;
    
    @OneToMany(mappedBy="user")
    private Set<UserRole> userRole;

    /** Default constructor. */
    public User() {
        super();
    }

    /**
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
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
     * Access method for password.
     *
     * @return the current value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for password.
     *
     * @param aPassword the new value for password
     */
    public void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * Access method for afiliation.
     *
     * @return the current value of afiliation
     */
    public String getAfiliation() {
        return afiliation;
    }

    /**
     * Setter method for afiliation.
     *
     * @param aAfiliation the new value for afiliation
     */
    public void setAfiliation(String aAfiliation) {
        afiliation = aAfiliation;
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
     * Access method for learningObject2.
     *
     * @return the current value of learningObject2
     */
    public Set<LearningObject> getLearningObject2() {
        return learningObject2;
    }

    /**
     * Setter method for learningObject2.
     *
     * @param aLearningObject2 the new value for learningObject2
     */
    public void setLearningObject2(Set<LearningObject> aLearningObject2) {
        learningObject2 = aLearningObject2;
    }

    /**
     * Access method for rubrik.
     *
     * @return the current value of rubrik
     */
    public Set<Rubrik> getRubrik() {
        return rubrik;
    }

    /**
     * Setter method for rubrik.
     *
     * @param aRubrik the new value for rubrik
     */
    public void setRubrik(Set<Rubrik> aRubrik) {
        rubrik = aRubrik;
    }

    /**
     * Access method for userRole.
     *
     * @return the current value of userRole
     */
    public Set<UserRole> getUserRole() {
        return userRole;
    }

    /**
     * Setter method for userRole.
     *
     * @param aUserRole the new value for userRole
     */
    public void setUserRole(Set<UserRole> aUserRole) {
        userRole = aUserRole;
    }

    /**
     * Compares the key for this instance with another User.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class User and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
        Object myEmail = this.getEmail();
        Object yourEmail = that.getEmail();
        if (myEmail==null ? yourEmail!=null : !myEmail.equals(yourEmail)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another User.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User)) return false;
        return this.equalKeys(other) && ((User)other).equalKeys(this);
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
        if (getEmail() == null) {
            i = 0;
        } else {
            i = getEmail().hashCode();
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
        StringBuffer sb = new StringBuffer("[User |");
        sb.append(" email=").append(getEmail());
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
        ret.put("email", getEmail());
        return ret;
    }

}
