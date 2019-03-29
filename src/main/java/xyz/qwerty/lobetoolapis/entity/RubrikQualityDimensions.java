// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "rubrik_quality_dimensions")
@Getter
@Setter
public class RubrikQualityDimensions implements Serializable {

	private static final long		serialVersionUID	= 1L;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "rubrik_id", nullable = false)
	private Rubrik					rubrik;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "quality_dimension_id", nullable = false)
	private QualityDimensionMaster	qualityDimensionMaster;
}
