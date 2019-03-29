package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class UserRoleKey implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_email", nullable = false)
	private User				user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "role_id", nullable = false)
	private Role				role;
}
