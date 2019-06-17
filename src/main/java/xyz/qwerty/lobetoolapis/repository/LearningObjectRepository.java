package xyz.qwerty.lobetoolapis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.LearningObject;

public interface LearningObjectRepository extends JpaRepository<LearningObject, Integer> {

	List<LearningObject> findAllByRubrikIdAndStatus(Integer rubrikId, String status);

	List<LearningObject> findAllByRubrikIdAndUser2EmailAndStatus(Integer rubrikId, String email, String status);
}
