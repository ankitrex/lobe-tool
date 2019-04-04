package xyz.qwerty.lobetoolapis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.Rubrik;

public interface RubrikRepository extends JpaRepository<Rubrik, Integer> {

	List<Rubrik> findByUserEmail(String email);
}
