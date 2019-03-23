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

@Entity(name="role_permission")
public class RolePermission implements Serializable {
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="role_id", nullable=false)
    private Role role;
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="permission_id", nullable=false)
    private Permission permission;

    /** Default constructor. */
    public RolePermission() {
        super();
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

    /**
     * Access method for permission.
     *
     * @return the current value of permission
     */
    public Permission getPermission() {
        return permission;
    }

    /**
     * Setter method for permission.
     *
     * @param aPermission the new value for permission
     */
    public void setPermission(Permission aPermission) {
        permission = aPermission;
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

    /** Temporary value holder for group key fragment permissionId */
    private transient int tempPermissionId;

    /**
     * Gets the key fragment id for member permission.
     * If this.permission is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setPermissionId.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see Permission
     */
    public int getPermissionId() {
        return (getPermission() == null ? tempPermissionId : getPermission().getId());
    }

    /**
     * Sets the key fragment id from member permission.
     * If this.permission is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getPermissionId.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @param aId New value for the key fragment
     * @see Permission
     */
    public void setPermissionId(int aId) {
        if (getPermission() == null) {
            tempPermissionId = aId;
        } else {
            getPermission().setId(aId);
        }
    }

    /**
     * Compares the key for this instance with another RolePermission.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class RolePermission and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof RolePermission)) {
            return false;
        }
        RolePermission that = (RolePermission) other;
        if (this.getRoleId() != that.getRoleId()) {
            return false;
        }
        if (this.getPermissionId() != that.getPermissionId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another RolePermission.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RolePermission)) return false;
        return this.equalKeys(other) && ((RolePermission)other).equalKeys(this);
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
        i = getRoleId();
        result = 37*result + i;
        i = getPermissionId();
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
        StringBuffer sb = new StringBuffer("[RolePermission |");
        sb.append(" roleId=").append(getRoleId());
        sb.append(" permissionId=").append(getPermissionId());
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
        ret.put("roleId", Integer.valueOf(getRoleId()));
        ret.put("permissionId", Integer.valueOf(getPermissionId()));
        return ret;
    }

}
