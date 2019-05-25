package xyz.qwerty.lobetoolapis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.LobeTemp;

public interface LobeTempRepository extends JpaRepository<LobeTemp, Integer>{
	
	Optional<LobeTemp> findByCodeAndLearningObjectName(String code, String lobeName);
	
	List<LobeTemp> findAllByAssignedTo(String email);
	
	List<LobeTemp> findAllByAssignedBy(String email);
}
