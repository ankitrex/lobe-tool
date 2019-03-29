// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "role_permission")
@Getter
@Setter
public class RolePermission implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "role_id", nullable = false)
	private Role				role;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "permission_id", nullable = false)
	private Permission			permission;
}
