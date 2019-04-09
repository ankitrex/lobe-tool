// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "rubrik_quality_dimensions")
@Getter
@Setter
public class RubrikQualityDimensions implements Serializable {

	private static final long			serialVersionUID	= 1L;

	@EmbeddedId
	private RubrikQualityDimensionsKey	rubrikQualityDimensionsKey;
}
