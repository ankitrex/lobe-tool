// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "permission")
@Getter
@Setter
public class Permission implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@Column(unique = true, nullable = false, precision = 10)
	private int					id;

	@Column(nullable = false, length = 20)
	private String				name;

	@OneToMany(mappedBy = "permission")
	private Set<RolePermission>	rolePermission;
}
