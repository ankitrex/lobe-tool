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

@Entity(name = "role")
@Getter
@Setter
public class Role implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int					id;

	@Column(nullable = false, length = 100)
	private String				description;

	@Column(nullable = false, length = 20)
	private String				name;

	@OneToMany(mappedBy = "role")
	private Set<RolePermission>	rolePermission;

	@OneToMany(mappedBy = "userRoleKey.role")
	private Set<UserRole>		userRole;
}
