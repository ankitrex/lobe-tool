// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
public class User implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@Column(unique = true, nullable = false, length = 100)
	private String				email;

	@Column(nullable = false, length = 100)
	private String				name;

	@Column(nullable = false, length = 100)
	private String				password;

	@Column(nullable = false, length = 100)
	private String				affiliation;

	@Column(nullable = false, length = 20)
	private String				status;

	@Column(name = "created_ts", nullable = false)
	private LocalDateTime		createdTs;

	@OneToMany(mappedBy = "user")
	private Set<LearningObject>	learningObject;

	@OneToMany(mappedBy = "user2")
	private Set<LearningObject>	learningObject2;

	@OneToMany(mappedBy = "user")
	private Set<Rubrik>			rubrik;

	@OneToMany(mappedBy = "userRoleKey.user")
	private Set<UserRole>		userRole;
}
