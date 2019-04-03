package xyz.qwerty.lobetoolapis.service;

import xyz.qwerty.lobetoolapis.vo.RubrikVo;

public interface RubrikService {

	RubrikVo createRubrik(String userId, Integer rubrikTypeId, String dimensionIds);
}
