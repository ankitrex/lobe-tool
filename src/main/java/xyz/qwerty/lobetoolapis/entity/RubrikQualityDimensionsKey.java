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
public class RubrikQualityDimensionsKey implements Serializable {

	private static final long		serialVersionUID	= 1L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "rubrik_id", nullable = false)
	private Rubrik					rubrik;

	@ManyToOne(optional = false)
	@JoinColumn(name = "quality_dimension_id", nullable = false)
	private QualityDimensionMaster	qualityDimensionMaster;
}
