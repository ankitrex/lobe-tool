package xyz.qwerty.lobetoolapis.service;

import java.util.List;

import xyz.qwerty.lobetoolapis.vo.DimensionVo;
import xyz.qwerty.lobetoolapis.vo.RoleVo;
import xyz.qwerty.lobetoolapis.vo.RubrikTypeVo;

public interface LobeService {

	List<RoleVo> getAllRoles();

	List<DimensionVo> getAllDimensions();

	List<RubrikTypeVo> getAllRubrikTypes();
}
