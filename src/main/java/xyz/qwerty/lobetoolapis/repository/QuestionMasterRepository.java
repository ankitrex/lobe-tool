package xyz.qwerty.lobetoolapis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.QuestionMaster;

public interface QuestionMasterRepository extends JpaRepository<QuestionMaster, Integer> {

	List<QuestionMaster> findByQualityDimensionMasterIdAndRubrikTypeMasterId(Integer dimensionId, Integer rubrikTypeId);
}
