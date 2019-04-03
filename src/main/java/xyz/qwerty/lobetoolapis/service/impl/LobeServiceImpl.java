package xyz.qwerty.lobetoolapis.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.qwerty.lobetoolapis.entity.QualityDimensionMaster;
import xyz.qwerty.lobetoolapis.entity.Role;
import xyz.qwerty.lobetoolapis.entity.RubrikTypeMaster;
import xyz.qwerty.lobetoolapis.repository.QualityDimensionMasterRepository;
import xyz.qwerty.lobetoolapis.repository.RoleRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikTypeMasterRepository;
import xyz.qwerty.lobetoolapis.service.LobeService;
import xyz.qwerty.lobetoolapis.vo.DimensionVo;
import xyz.qwerty.lobetoolapis.vo.RoleVo;
import xyz.qwerty.lobetoolapis.vo.RubrikTypeVo;

@Service
public class LobeServiceImpl implements LobeService {

	@Autowired
	RoleRepository						roleRepository;

	@Autowired
	QualityDimensionMasterRepository	qualityDimensionMasterRepository;

	@Autowired
	RubrikTypeMasterRepository			rubrikTypeMasterRepository;

	@Override
	public List<RoleVo> getAllRoles() {

		List<Role> roles = roleRepository.findAll();

		return roles.stream().map(r -> {
			RoleVo roleVo = new RoleVo();
			roleVo.setId(r.getId());
			roleVo.setName(r.getName());
			roleVo.setDescription(r.getDescription());
			return roleVo;
		}).collect(Collectors.toList());
	}

	@Override
	public List<DimensionVo> getAllDimensions() {

		List<QualityDimensionMaster> dimensions = qualityDimensionMasterRepository.findAll();

		return dimensions.stream().map(d -> {
			DimensionVo dimensionVo = new DimensionVo();
			dimensionVo.setId(d.getId());
			dimensionVo.setDimensionName(d.getDimensionName());
			return dimensionVo;
		}).collect(Collectors.toList());
	}

	@Override
	public List<RubrikTypeVo> getAllRubrikTypes() {

		List<RubrikTypeMaster> rubriks = rubrikTypeMasterRepository.findAll();

		return rubriks.stream().map(r -> {
			RubrikTypeVo rubrikTypeVo = new RubrikTypeVo();
			rubrikTypeVo.setId(r.getId());
			rubrikTypeVo.setName(r.getName());
			return rubrikTypeVo;
		}).collect(Collectors.toList());
	}

}
