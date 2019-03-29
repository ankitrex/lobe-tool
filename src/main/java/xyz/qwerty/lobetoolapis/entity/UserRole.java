// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "user_role")
@Getter
@Setter
public class UserRole implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@EmbeddedId
	private UserRoleKey			userRoleKey;
}
