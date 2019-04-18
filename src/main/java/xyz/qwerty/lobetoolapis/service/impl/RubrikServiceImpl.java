package xyz.qwerty.lobetoolapis.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.entity.QualityDimensionMaster;
import xyz.qwerty.lobetoolapis.entity.QuestionMaster;
import xyz.qwerty.lobetoolapis.entity.Rubrik;
import xyz.qwerty.lobetoolapis.entity.RubrikQualityDimensions;
import xyz.qwerty.lobetoolapis.entity.RubrikQualityDimensionsKey;
import xyz.qwerty.lobetoolapis.entity.RubrikQuestions;
import xyz.qwerty.lobetoolapis.entity.RubrikQuestionsKey;
import xyz.qwerty.lobetoolapis.entity.RubrikTypeMaster;
import xyz.qwerty.lobetoolapis.entity.User;
import xyz.qwerty.lobetoolapis.repository.QualityDimensionMasterRepository;
import xyz.qwerty.lobetoolapis.repository.QuestionMasterRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikQualityDimensionsRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikQuestionsRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikTypeMasterRepository;
import xyz.qwerty.lobetoolapis.repository.UserRepository;
import xyz.qwerty.lobetoolapis.service.RubrikService;
import xyz.qwerty.lobetoolapis.util.Constants;
import xyz.qwerty.lobetoolapis.vo.DimensionVo;
import xyz.qwerty.lobetoolapis.vo.QuestionVo;
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

	@Autowired
	UserRepository						userRepository;

	@Autowired
	QuestionMasterRepository			questionMasterRepository;

	@Value("${custom.rubrik.id}")
	private Integer						customRubrikId;

	@Value("${premium.rubrik.id}")
	private Integer						premiumRubrikId;

	@Value("${optional.question.id}")
	private Integer						optionalQuestionId;

	@Override
	public RubrikVo createRubrik(String userId, Integer rubrikTypeId, String dimensionIds) {

		// check if rubrik type is valid
		Optional<RubrikTypeMaster> rubrikType = rubrikTypeMasterRepository.findById(rubrikTypeId);
		if (!rubrikType.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Rubrik Type Id");
		}

		// fetch all quality dimensions
		List<Integer> allQualityDimensions = qualityDimensionMasterRepository.findAll().stream().map(QualityDimensionMaster::getId).collect(Collectors.toList());

		List<Integer> rubrikDimensions;

		// if custom lobe then change dimensions else consider all dimensions
		if (customRubrikId.equals(rubrikTypeId)) {

			if (StringUtils.isEmpty(dimensionIds)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No dimension ids found");
			}

			rubrikDimensions = Arrays.asList(dimensionIds.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());

			rubrikDimensions.forEach(a -> {
				if (!allQualityDimensions.contains(a)) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid dimension id found " + a);
				}
			});

		}
		else {

			rubrikDimensions = allQualityDimensions;
		}

		User user = new User();
		user.setEmail(userId);

		RubrikTypeMaster rubrikTypeMaster = rubrikType.get();

		Rubrik rubrik = new Rubrik();
		rubrik.setUser(user);
		rubrik.setRubrikTypeMaster(rubrikTypeMaster);
		rubrik.setStatus(Constants.STATUS_INCOMPLETE);
		rubrik.setCreatedTs(LocalDateTime.now());

		rubrik = rubrikRepository.save(rubrik);

		rubrik.setName(rubrikTypeMaster.getName().replace(' ', '_').toUpperCase() + "_" + rubrik.getId());
		rubrik.setUpdatedTs(LocalDateTime.now());

		rubrikRepository.save(rubrik);

		// insert values into rubrik quality dimensions
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

		// add questions to the rubrik-questions mapping table
		if (!customRubrikId.equals(rubrikTypeMaster.getId())) {

			List<RubrikQuestions> rubrikQuestions = rubrikTypeMaster.getQuestionMaster().stream().filter(qm -> qm.getId() != optionalQuestionId).map(qm -> {

				RubrikQuestions rubrikQuestion = new RubrikQuestions();

				RubrikQuestionsKey rubrikQuestionKey = new RubrikQuestionsKey();
				rubrikQuestionKey.setQuestionMaster(qm);
				rubrikQuestionKey.setRubrik(r);
				rubrikQuestion.setRubrikQuestionsKey(rubrikQuestionKey);

				return rubrikQuestion;
			}).collect(Collectors.toList());

			rubrikQuestionsRepository.saveAll(rubrikQuestions);
		}

		return getRubrikVo(rubrik);
	}

	@Override
	public List<RubrikVo> getAllRubriks(String userId) {

		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {

			Set<Rubrik> rubriks = user.get().getRubrik();

			return rubriks.stream().map(r -> getRubrikVo(r)).sorted((r1, r2) -> r1.getCreatedTs().compareTo(r2.getCreatedTs())).collect(Collectors.toList());
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
	}

	@Override
	public RubrikVo getRubrikDetails(String userId, Integer rubrikId) {

		Optional<Rubrik> result = rubrikRepository.findById(rubrikId);
		if (result.isPresent()) {

			Rubrik rubrik = result.get();
			if (!userId.equals(rubrik.getUser().getEmail())) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Rubrik doesn't belong to this user");
			}

			return getCompleteRubrikVo(rubrik);
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rubrik id");
	}

	@Override
	public DimensionVo getDimensionVo(Integer dimensionId, Integer rubrikTypeId) {

		DimensionVo dimensionVo = new DimensionVo();
		dimensionVo.setId(dimensionId);

		if (customRubrikId.equals(rubrikTypeId)) {
			rubrikTypeId = premiumRubrikId;
		}

		List<QuestionMaster> questions = questionMasterRepository.findByQualityDimensionMasterIdAndRubrikTypeMasterId(dimensionId, rubrikTypeId);

		List<QuestionVo> questionVoList = questions.stream().map(q -> getQuestionVo(q)).collect(Collectors.toList());
		dimensionVo.setQuestions(questionVoList);

		return dimensionVo;
	}

	@Override
	public RubrikVo updateQuestionsAndSubmit(Integer rubrikId, String userId, List<Integer> addQuestionIds, List<Integer> removeQuestionIds, Boolean submit) {

		Optional<Rubrik> result = rubrikRepository.findById(rubrikId);
		if (result.isPresent()) {

			Rubrik rubrik = result.get();
			if (!userId.equals(rubrik.getUser().getEmail())) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Rubrik doesn't belong to this user");
			}
			if (Constants.STATUS_COMPLETE.equals(rubrik.getStatus())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rubrik already completed");
			}

			if (!premiumRubrikId.equals(rubrik.getRubrikTypeMaster().getId())) {

				if (customRubrikId.equals(rubrik.getRubrikTypeMaster().getId())) {

					List<RubrikQuestions> addQuestions = getRubrikQuestionsMapping(rubrik, addQuestionIds);
					rubrikQuestionsRepository.saveAll(addQuestions);

					removeQuestionIds.forEach(id -> {
						rubrikQuestionsRepository.deleteByRubrikQuestionsKeyQuestionMasterIdAndRubrikQuestionsKeyRubrikId(id, rubrik.getId());
					});
				}
				else {
					if (addQuestionIds.contains(optionalQuestionId)) {

						List<RubrikQuestions> addQuestions = getRubrikQuestionsMapping(rubrik, Arrays.asList(optionalQuestionId));

						rubrikQuestionsRepository.saveAll(addQuestions);
					}
					else if (removeQuestionIds.contains(optionalQuestionId)) {

						rubrikQuestionsRepository.deleteByRubrikQuestionsKeyQuestionMasterIdAndRubrikQuestionsKeyRubrikId(optionalQuestionId, rubrik.getId());
					}
				}
			}

			if (submit) {
				rubrik.setStatus(Constants.STATUS_COMPLETE);
				rubrikRepository.save(rubrik);
			}

			rubrik.setUpdatedTs(LocalDateTime.now());
			rubrikRepository.save(rubrik);

			return getRubrikVo(rubrik);
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rubrik id");
	}

	private List<RubrikQuestions> getRubrikQuestionsMapping(Rubrik rubrik, List<Integer> questionIds) {

		List<RubrikQuestions> rubrikQuestions = new ArrayList<>();

		questionIds.forEach(id -> {

			QuestionMaster questionMaster = new QuestionMaster();
			questionMaster.setId(id);

			RubrikQuestions rubrikQuestion = new RubrikQuestions();

			RubrikQuestionsKey rubrikQuestionKey = new RubrikQuestionsKey();
			rubrikQuestionKey.setQuestionMaster(questionMaster);
			rubrikQuestionKey.setRubrik(rubrik);

			rubrikQuestion.setRubrikQuestionsKey(rubrikQuestionKey);

			rubrikQuestions.add(rubrikQuestion);
		});

		return rubrikQuestions;
	}

	private RubrikVo getRubrikVo(Rubrik rubrik) {

		RubrikVo rubrikVo = new RubrikVo();
		rubrikVo.setId(rubrik.getId());
		rubrikVo.setName(rubrik.getName());
		rubrikVo.setStatus(rubrik.getStatus());
		rubrikVo.setRubrikType(rubrik.getRubrikTypeMaster().getName());
		rubrikVo.setRubrikTypeId(rubrik.getRubrikTypeMaster().getId());
		rubrikVo.setCreatedTs(rubrik.getCreatedTs());
		rubrikVo.setUpdatedTs(rubrik.getUpdatedTs());

		return rubrikVo;
	}

	private RubrikVo getCompleteRubrikVo(Rubrik rubrik) {

		RubrikVo rubrikVo = getRubrikVo(rubrik);

		Set<RubrikQualityDimensions> dimensions = rubrik.getRubrikQualityDimensions();
		Set<RubrikQuestions> questions = rubrik.getRubrikQuestions();

		rubrikVo.setDimensions(getDimensionMapping(dimensions, questions));

		return rubrikVo;
	}

	private List<DimensionVo> getDimensionMapping(Set<RubrikQualityDimensions> dimensions, Set<RubrikQuestions> questions) {

		List<DimensionVo> dimensionVoList = new ArrayList<>();

		List<QuestionVo> questionVoList = questions.stream().map(q -> {
			QuestionMaster question = q.getRubrikQuestionsKey().getQuestionMaster();
			return getQuestionVo(question);

		}).sorted((q1, q2) -> q1.getId().compareTo(q2.getId())).collect(Collectors.toList());

		dimensions.forEach(d -> {
			QualityDimensionMaster dimension = d.getRubrikQualityDimensionsKey().getQualityDimensionMaster();

			DimensionVo dimensionVo = new DimensionVo();
			dimensionVo.setId(dimension.getId());
			dimensionVo.setDimensionName(dimension.getDimensionName());

			List<QuestionVo> rubrikQuestions = questionVoList.stream().filter(q -> q.getDimensionId() == dimensionVo.getId()).collect(Collectors.toList());
			dimensionVo.setQuestions(rubrikQuestions);

			dimensionVoList.add(dimensionVo);
		});

		dimensionVoList.sort((d1, d2) -> d1.getId().compareTo(d2.getId()));

		return dimensionVoList;
	}

	private QuestionVo getQuestionVo(QuestionMaster questionMaster) {

		QuestionVo questionVo = new QuestionVo();

		questionVo.setId(questionMaster.getId());
		questionVo.setOptional(questionMaster.isOptional());
		questionVo.setQuestion(questionMaster.getQuestion());
		questionVo.setQuestionMeta(questionMaster.getQuestionMeta());
		questionVo.setScore0(questionMaster.getScore0());
		questionVo.setScore0Images(getImageList(questionMaster.getScore0Images()));
		questionVo.setScore1(questionMaster.getScore1());
		questionVo.setScore1Images(getImageList(questionMaster.getScore1Images()));
		questionVo.setScore2(questionMaster.getScore2());
		questionVo.setScore2Images(getImageList(questionMaster.getScore2Images()));
		questionVo.setScore3(questionMaster.getScore3());
		questionVo.setScore3Images(getImageList(questionMaster.getScore3Images()));
		questionVo.setDimensionId(questionMaster.getQualityDimensionMaster().getId());

		return questionVo;
	}

	private List<String> getImageList(String imagesString) {

		if (StringUtils.isEmpty(imagesString)) {
			return new ArrayList<>();
		}

		return Arrays.asList(imagesString.split(","));
	}

}
