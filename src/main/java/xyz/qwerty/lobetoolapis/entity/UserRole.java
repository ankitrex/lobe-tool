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
import javax.persistence.Version;

@Entity(name="user_role")
public class UserRole implements Serializable {

    /** Primary key. */
    protected static final String PK = "UserRolePrimary";

    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="user_email", nullable=false)
    private User user;
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="role_id", nullable=false)
    private Role role;

    /** Default constructor. */
    public UserRole() {
        super();
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
     * Access method for role.
     *
     * @return the current value of role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setter method for role.
     *
     * @param aRole the new value for role
     */
    public void setRole(Role aRole) {
        role = aRole;
    }

    /** Temporary value holder for group key fragment userEmail */
    private transient String tempUserEmail;

    /**
     * Gets the key fragment email for member user.
     * If this.user is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setUserEmail.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see User
     */
    public String getUserEmail() {
        return (getUser() == null ? tempUserEmail : getUser().getEmail());
    }

    /**
     * Sets the key fragment email from member user.
     * If this.user is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getUserEmail.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @param aEmail New value for the key fragment
     * @see User
     */
    public void setUserEmail(String aEmail) {
        if (getUser() == null) {
            tempUserEmail = aEmail;
        } else {
            getUser().setEmail(aEmail);
        }
    }

    /** Temporary value holder for group key fragment roleId */
    private transient int tempRoleId;

    /**
     * Gets the key fragment id for member role.
     * If this.role is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setRoleId.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see Role
     */
    public int getRoleId() {
        return (getRole() == null ? tempRoleId : getRole().getId());
    }

    /**
     * Sets the key fragment id from member role.
     * If this.role is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getRoleId.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @param aId New value for the key fragment
     * @see Role
     */
    public void setRoleId(int aId) {
        if (getRole() == null) {
            tempRoleId = aId;
        } else {
            getRole().setId(aId);
        }
    }

    /**
     * Compares the key for this instance with another UserRole.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UserRole and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UserRole)) {
            return false;
        }
        UserRole that = (UserRole) other;
        Object myUserEmail = this.getUserEmail();
        Object yourUserEmail = that.getUserEmail();
        if (myUserEmail==null ? yourUserEmail!=null : !myUserEmail.equals(yourUserEmail)) {
            return false;
        }
        if (this.getRoleId() != that.getRoleId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UserRole.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserRole)) return false;
        return this.equalKeys(other) && ((UserRole)other).equalKeys(this);
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
        if (getUserEmail() == null) {
            i = 0;
        } else {
            i = getUserEmail().hashCode();
        }
        result = 37*result + i;
        i = getRoleId();
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
        StringBuffer sb = new StringBuffer("[UserRole |");
        sb.append(" userEmail=").append(getUserEmail());
        sb.append(" roleId=").append(getRoleId());
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
        ret.put("userEmail", getUserEmail());
        ret.put("roleId", Integer.valueOf(getRoleId()));
        return ret;
    }

}
