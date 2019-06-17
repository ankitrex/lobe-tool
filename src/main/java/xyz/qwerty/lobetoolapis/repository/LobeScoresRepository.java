package xyz.qwerty.lobetoolapis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.LobeScores;
import xyz.qwerty.lobetoolapis.entity.LobeScoresKey;

public interface LobeScoresRepository extends JpaRepository<LobeScores, LobeScoresKey> {

	List<LobeScores> findAllByLobeScoresKeyLearningObjectId(Integer lobeId);
}
