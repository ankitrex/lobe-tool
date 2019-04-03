package xyz.qwerty.lobetoolapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.RubrikQuestions;
import xyz.qwerty.lobetoolapis.entity.RubrikQuestionsKey;

public interface RubrikQuestionsRepository extends JpaRepository<RubrikQuestions, RubrikQuestionsKey> {

}
