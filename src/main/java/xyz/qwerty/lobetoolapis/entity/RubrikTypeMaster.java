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

@Entity(name = "rubrik_type_master")
@Getter
@Setter
public class RubrikTypeMaster implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int					id;

	@Column(nullable = false, length = 50)
	private String				name;

	@OneToMany(mappedBy = "rubrikTypeMaster")
	private Set<QuestionMaster>	questionMaster;

	@OneToMany(mappedBy = "rubrikTypeMaster")
	private Set<Rubrik>			rubrik;
}
