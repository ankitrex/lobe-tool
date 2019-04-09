package xyz.qwerty.lobetoolapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.LobeScores;
import xyz.qwerty.lobetoolapis.entity.LobeScoresKey;

public interface LobeScoresRepository extends JpaRepository<LobeScores, LobeScoresKey> {

}
