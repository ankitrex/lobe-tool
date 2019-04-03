package xyz.qwerty.lobetoolapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.RubrikQualityDimensions;
import xyz.qwerty.lobetoolapis.entity.RubrikQualityDimensionsKey;

public interface RubrikQualityDimensionsRepository extends JpaRepository<RubrikQualityDimensions, RubrikQualityDimensionsKey> {

}
