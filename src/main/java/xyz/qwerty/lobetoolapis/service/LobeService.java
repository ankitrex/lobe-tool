package xyz.qwerty.lobetoolapis.service;

import java.util.List;

import xyz.qwerty.lobetoolapis.vo.DimensionVo;
import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;
import xyz.qwerty.lobetoolapis.vo.RoleVo;
import xyz.qwerty.lobetoolapis.vo.RubrikTypeVo;
import xyz.qwerty.lobetoolapis.vo.RubrikVo;

public interface LobeService {

	List<RoleVo> getAllRoles();

	List<DimensionVo> getAllDimensions();

	List<RubrikTypeVo> getAllRubrikTypes();

	LearningObjectVo assignLearningObject(String userId, Integer rubrikId, String rubrikCode, String msgSubject,
			String msgBody, String learningObjectName, String evaluatorEmail);

	List<LearningObjectVo> getAllEvaluations(String userId, String type);

	LearningObjectVo updateLearningObject(String userId, String code, String grade, String subject, String chapter, String moduleName, String repositoryName);

	RubrikVo getLobeRubrik(String userId, String code);

	LearningObjectVo updateLobeScores(String userId, String code, String json, Boolean submit);
}
