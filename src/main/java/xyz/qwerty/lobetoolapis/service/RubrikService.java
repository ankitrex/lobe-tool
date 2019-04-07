package xyz.qwerty.lobetoolapis.service;

import java.util.List;

import xyz.qwerty.lobetoolapis.vo.DimensionVo;
import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;
import xyz.qwerty.lobetoolapis.vo.RubrikVo;

public interface RubrikService {

	RubrikVo createRubrik(String userId, Integer rubrikTypeId, String dimensionIds);

	List<RubrikVo> getAllRubriks(String userId);

	RubrikVo getRubrikDetails(String userId, Integer rubrikId);

	DimensionVo getDimensionVo(Integer dimensionId, Integer rubrikTypeId);

	RubrikVo updateQuestionsAndSubmit(Integer rubrikId, String userId, List<Integer> addQuestionIds, List<Integer> removeQuestionIds, Boolean submit);
}
