package xyz.qwerty.lobetoolapis.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.entity.QualityDimensionMaster;
import xyz.qwerty.lobetoolapis.entity.Rubrik;
import xyz.qwerty.lobetoolapis.entity.RubrikQualityDimensions;
import xyz.qwerty.lobetoolapis.entity.RubrikQualityDimensionsKey;
import xyz.qwerty.lobetoolapis.entity.RubrikQuestions;
import xyz.qwerty.lobetoolapis.entity.RubrikQuestionsKey;
import xyz.qwerty.lobetoolapis.entity.RubrikTypeMaster;
import xyz.qwerty.lobetoolapis.entity.User;
import xyz.qwerty.lobetoolapis.repository.QualityDimensionMasterRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikQualityDimensionsRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikQuestionsRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikTypeMasterRepository;
import xyz.qwerty.lobetoolapis.service.RubrikService;
import xyz.qwerty.lobetoolapis.vo.RubrikVo;

@Service
public class RubrikServiceImpl implements RubrikService {

	@Autowired
	RubrikTypeMasterRepository			rubrikTypeMasterRepository;

	@Autowired
	QualityDimensionMasterRepository	qualityDimensionMasterRepository;

	@Autowired
	RubrikRepository					rubrikRepository;

	@Autowired
	RubrikQualityDimensionsRepository	rubrikQualityDimensionsRepository;

	@Autowired
	RubrikQuestionsRepository			rubrikQuestionsRepository;

	@Value("${custom.rubrik.id}")
	private Integer						customRubrikId;

	@Override
	public RubrikVo createRubrik(String userId, Integer rubrikTypeId, String dimensionIds) {

		if (StringUtils.isEmpty(dimensionIds)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No dimension ids found");
		}

		Optional<RubrikTypeMaster> rubrikType = rubrikTypeMasterRepository.findById(rubrikTypeId);

		if (!rubrikType.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Rubrik Type Id");
		}

		List<Integer> allQualityDimensions = qualityDimensionMasterRepository.findAll().stream().map(QualityDimensionMaster::getId).collect(Collectors.toList());

		List<Integer> rubrikDimensions = Arrays.asList(dimensionIds.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());

		rubrikDimensions.forEach(a -> {
			if (!allQualityDimensions.contains(a)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid dimension id found " + a);
			}
		});

		User user = new User();
		user.setEmail(userId);

		RubrikTypeMaster rubrikTypeMaster = rubrikType.get();

		Rubrik rubrik = new Rubrik();
		rubrik.setUser(user);
		rubrik.setRubrikTypeMaster(rubrikTypeMaster);
		rubrik.setStatus("incomplete");
		rubrik.setCreatedTs(LocalDateTime.now());

		rubrik = rubrikRepository.save(rubrik);

		rubrik.setName(rubrikTypeMaster.getName().replace(' ', '_').toUpperCase() + "_" + rubrik.getId());
		rubrik.setUpdatedTs(LocalDateTime.now());

		rubrikRepository.save(rubrik);

		Rubrik r = rubrik;
		List<RubrikQualityDimensions> rubrikQualityDimensions = rubrikDimensions.stream().map(id -> {

			QualityDimensionMaster qualityDimension = new QualityDimensionMaster();
			qualityDimension.setId(id);

			RubrikQualityDimensions rubrikQualityDimension = new RubrikQualityDimensions();
			RubrikQualityDimensionsKey rubrikQualityDimensionKey = new RubrikQualityDimensionsKey();
			rubrikQualityDimensionKey.setRubrik(r);
			rubrikQualityDimensionKey.setQualityDimensionMaster(qualityDimension);
			rubrikQualityDimension.setRubrikQualityDimensionsKey(rubrikQualityDimensionKey);

			return rubrikQualityDimension;
		}).collect(Collectors.toList());

		rubrikQualityDimensionsRepository.saveAll(rubrikQualityDimensions);

		if (!customRubrikId.equals(rubrikTypeMaster.getId())) {

			List<RubrikQuestions> rubrikQuestions = rubrikTypeMaster.getQuestionMaster().stream().map(qm -> {

				RubrikQuestions rubrikQuestion = new RubrikQuestions();
				RubrikQuestionsKey rubrikQuestionKey = new RubrikQuestionsKey();
				rubrikQuestionKey.setQuestionMaster(qm);
				rubrikQuestionKey.setRubrik(r);
				rubrikQuestion.setRubrikQuestionsKey(rubrikQuestionKey);

				return rubrikQuestion;
			}).collect(Collectors.toList());

			rubrikQuestionsRepository.saveAll(rubrikQuestions);
		}

		RubrikVo rubrikVo = new RubrikVo();
		rubrikVo.setId(rubrik.getId());
		rubrikVo.setName(rubrik.getName());
		rubrikVo.setStatus(rubrik.getStatus());
		rubrikVo.setCreatedTs(rubrik.getCreatedTs());
		rubrikVo.setUpdatedTs(rubrik.getUpdatedTs());

		return rubrikVo;
	}

}
