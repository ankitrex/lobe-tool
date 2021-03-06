package xyz.qwerty.lobetoolapis.service;

import java.util.List;
import java.util.Map;

import xyz.qwerty.lobetoolapis.vo.DimensionVo;
import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;
import xyz.qwerty.lobetoolapis.vo.RoleVo;
import xyz.qwerty.lobetoolapis.vo.RubrikTypeVo;

public interface LobeService {

	List<RoleVo> getAllRoles();

	List<DimensionVo> getAllDimensions();

	List<RubrikTypeVo> getAllRubrikTypes();

	LearningObjectVo assignLearningObject(String userId, Integer rubrikId, String msgSubject, String msgBody, String learningObjectName, String evaluatorEmail);

	List<LearningObjectVo> getAllEvaluations(String userId, String type);

	LearningObjectVo updateLearningObject(String userId, String code, String grade, String subject, String chapter, String lobeName, String repositoryName);

	LearningObjectVo getLobeRubrik(String userId, Integer lobeId, Boolean generator);

	LearningObjectVo updateLobeScores(String userId, Integer lobeId, Map<Integer, Integer> json, Boolean submit);
}
