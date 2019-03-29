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

@Entity(name = "quality_dimension_master")
@Getter
@Setter
public class QualityDimensionMaster implements Serializable {

	private static final long				serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int								id;

	@Column(name = "dimension_name", nullable = false, length = 50)
	private String							dimensionName;

	@OneToMany(mappedBy = "qualityDimensionMaster")
	private Set<RubrikQualityDimensions>	rubrikQualityDimensions;
}
