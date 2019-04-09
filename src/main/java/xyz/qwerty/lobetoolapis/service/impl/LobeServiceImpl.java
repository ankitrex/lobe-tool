package xyz.qwerty.lobetoolapis.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.entity.LearningObject;
import xyz.qwerty.lobetoolapis.entity.LobeScores;
import xyz.qwerty.lobetoolapis.entity.LobeScoresKey;
import xyz.qwerty.lobetoolapis.entity.QualityDimensionMaster;
import xyz.qwerty.lobetoolapis.entity.QuestionMaster;
import xyz.qwerty.lobetoolapis.entity.Role;
import xyz.qwerty.lobetoolapis.entity.Rubrik;
import xyz.qwerty.lobetoolapis.entity.RubrikQualityDimensions;
import xyz.qwerty.lobetoolapis.entity.RubrikTypeMaster;
import xyz.qwerty.lobetoolapis.entity.User;
import xyz.qwerty.lobetoolapis.repository.LearningObjectRepository;
import xyz.qwerty.lobetoolapis.repository.LobeScoresRepository;
import xyz.qwerty.lobetoolapis.repository.QualityDimensionMasterRepository;
import xyz.qwerty.lobetoolapis.repository.RoleRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikRepository;
import xyz.qwerty.lobetoolapis.repository.RubrikTypeMasterRepository;
import xyz.qwerty.lobetoolapis.repository.UserRepository;
import xyz.qwerty.lobetoolapis.service.LobeService;
import xyz.qwerty.lobetoolapis.service.RubrikService;
import xyz.qwerty.lobetoolapis.util.Constants;
import xyz.qwerty.lobetoolapis.vo.DimensionVo;
import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;
import xyz.qwerty.lobetoolapis.vo.QuestionVo;
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

	@Autowired
	RubrikRepository					rubrikRepository;

	@Autowired
	LearningObjectRepository			learningObjectRepository;

	@Autowired
	UserRepository						userRepository;

	@Autowired
	JavaMailSender						emailSender;

	@Autowired
	RubrikService						rubrikService;

	@Autowired
	LobeScoresRepository				lobeScoresRepository;

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

	@Override
	public LearningObjectVo assignLearningObject(String userId, Integer rubrikId, String rubrikCode, String msgSubject, String msgBody, String learningObjectName,
			String evaluatorEmail) {

		Optional<Rubrik> result = rubrikRepository.findById(rubrikId);
		if (result.isPresent()) {

			Rubrik rubrik = result.get();
			if (!userId.equals(rubrik.getUser().getEmail())) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Rubrik doesn't belong to this user");
			}
			if (Constants.STATUS_INCOMPLETE.equals(rubrik.getStatus())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete rubrik can't be assigned");
			}
			Optional<User> evaluator = userRepository.findById(evaluatorEmail);
			if (!evaluator.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, evaluatorEmail + " not registered");
			}
			else {
				List<String> userPermissions = new ArrayList<>();
				evaluator.get().getUserRole().forEach(role -> {
					role.getUserRoleKey().getRole().getRolePermission().forEach(rolePerm -> {
						userPermissions.add(rolePerm.getPermission().getName());
					});
				});
				if (!userPermissions.contains("evaluate_rubrik")) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, evaluatorEmail + " is not an evaluator");
				}
			}

			String code = rubrikCode + "-" + UUID.randomUUID().toString();

			LearningObject learningObject = new LearningObject();
			learningObject.setCode(code);
			learningObject.setName(learningObjectName);
			learningObject.setStatus(Constants.STATUS_ASSIGNED);
			learningObject.setCreatedTs(LocalDateTime.now());
			learningObject.setRubrik(rubrik);

			User assignedBy = new User();
			assignedBy.setEmail(userId);
			learningObject.setUser(assignedBy);

			User assignedTo = new User();
			assignedTo.setEmail(evaluatorEmail);
			learningObject.setUser2(assignedTo);

			learningObjectRepository.save(learningObject);

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(evaluatorEmail);
			message.setSubject(msgSubject);
			message.setText(msgBody + "\ncode: " + code);
			emailSender.send(message);

			return getLearningObjectVo(learningObject);
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rubrik id");
	}

	@Override
	public List<LearningObjectVo> getAllEvaluations(String userId, String type) {

		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {

			User u = user.get();

			Set<LearningObject> learningObjects = type.equals(Constants.TYPE_GENERATOR) ? u.getLearningObject() : u.getLearningObject2();

			return learningObjects.stream().filter(l -> type.equals(Constants.TYPE_GENERATOR) || !l.getStatus().equals("assigned")).map(l -> getEvalLearningObject(l, type))
					.collect(Collectors.toList());
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
	}

	@Override
	public LearningObjectVo updateLearningObject(String userId, String code, String grade, String subject, String chapter, String moduleName, String repositoryName) {

		Optional<LearningObject> learningObject = learningObjectRepository.findById(code);
		if (learningObject.isPresent()) {

			LearningObject l = learningObject.get();
			if (!l.getUser2().getEmail().equals(userId)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Learning object not assigned to this user");
			}
			if (!l.getStatus().equals(Constants.STATUS_ASSIGNED)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code already submitted");
			}

			l.setChapter(chapter);
			l.setGrade(grade);
			l.setSubject(subject);
			l.setModuleName(moduleName);
			l.setRepositoryName(repositoryName);
			l.setStatus(Constants.STATUS_INCOMPLETE);
			l.setUpdatedTs(LocalDateTime.now());

			learningObjectRepository.save(l);

			return getLearningObjectVo(l);
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid learning object code");
	}

	@Override
	public LearningObjectVo getLobeRubrik(String userId, String code) {

		Optional<LearningObject> learningObject = learningObjectRepository.findById(code);
		if (learningObject.isPresent()) {

			LearningObject l = learningObject.get();
			if (!l.getUser2().getEmail().equals(userId)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Learning object not assigned to this user");
			}

			return getQuestionsLearningObjectVo(l);
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid learning object code");
	}

	@Override
	public LearningObjectVo updateLobeScores(String userId, String code, Map<Integer, Integer> json, Boolean submit) {

		Optional<LearningObject> learningObject = learningObjectRepository.findById(code);
		if (learningObject.isPresent()) {

			LearningObject l = learningObject.get();
			if (!l.getUser2().getEmail().equals(userId)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Learning object not assigned to this user");
			}
			if (l.getStatus().equals(Constants.STATUS_COMPLETE)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evaluation already submitted");
			}

			List<Integer> questions = l.getRubrik().getRubrikQuestions().stream().map(q -> q.getRubrikQuestionsKey().getQuestionMaster().getId()).collect(Collectors.toList());

			for (Entry<Integer, Integer> entry : json.entrySet()) {

				Integer questionId = entry.getKey();
				Integer score = entry.getValue();

				if (questions.contains(questionId) && score >= 0 && score <= 3) {

					LobeScores lobeScores = new LobeScores();
					lobeScores.setScore(score);

					LobeScoresKey lobeScoresKey = new LobeScoresKey();
					lobeScoresKey.setLearningObject(l);

					QuestionMaster questionMaster = new QuestionMaster();
					questionMaster.setId(questionId);
					lobeScoresKey.setQuestionMaster(questionMaster);

					lobeScores.setLobeScoresKey(lobeScoresKey);

					lobeScoresRepository.save(lobeScores);
				}

			}

			if (submit) {

				Integer totalQuestions = l.getRubrik().getRubrikQuestions().size();
				Integer completedQuestions = l.getLobeScores().size();
				if (totalQuestions == completedQuestions) {
					l.setStatus(Constants.STATUS_COMPLETE);
					learningObjectRepository.save(l);
				}
				else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not all questions have been scored");
				}
			}

			return getLearningObjectVo(l);
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid learning object code");
	}

	private LearningObjectVo getQuestionsLearningObjectVo(LearningObject l) {

		LearningObjectVo learningObjectVo = getLearningObjectVo(l);

		Map<Integer, Integer> scores = new HashMap<>();
		l.getLobeScores().forEach(ls -> {
			Integer questionId = ls.getLobeScoresKey().getQuestionMaster().getId();
			Integer score = ls.getScore();
			scores.put(questionId, score);
		});

		List<QuestionVo> questions = l.getRubrik().getRubrikQuestions().stream().map(rq -> {
			QuestionMaster questionMaster = rq.getRubrikQuestionsKey().getQuestionMaster();
			Integer score = scores.get(questionMaster.getId());
			return getQuestionVo(questionMaster, score);
		}).collect(Collectors.toList());

		Set<RubrikQualityDimensions> dimensions = l.getRubrik().getRubrikQualityDimensions();

		List<DimensionVo> dimensionVoList = new ArrayList<>();

		dimensions.forEach(d -> {
			QualityDimensionMaster dimension = d.getRubrikQualityDimensionsKey().getQualityDimensionMaster();

			DimensionVo dimensionVo = new DimensionVo();
			dimensionVo.setId(dimension.getId());
			dimensionVo.setDimensionName(dimension.getDimensionName());

			List<QuestionVo> rubrikQuestions = questions.stream().filter(q -> q.getDimensionId() == dimensionVo.getId()).collect(Collectors.toList());
			dimensionVo.setQuestions(rubrikQuestions);

			dimensionVoList.add(dimensionVo);
		});

		learningObjectVo.setDimensionVos(dimensionVoList);

		return learningObjectVo;
	}

	private LearningObjectVo getLearningObjectVo(LearningObject learningObject) {

		LearningObjectVo learningObjectVo = new LearningObjectVo();
		learningObjectVo.setAssignedBy(learningObject.getUser().getEmail());
		learningObjectVo.setAssignedTo(learningObject.getUser2().getEmail());
		learningObjectVo.setCode(learningObject.getCode());
		learningObjectVo.setCreatedTs(learningObject.getCreatedTs());
		learningObjectVo.setUpdatedTs(learningObject.getUpdatedTs());
		learningObjectVo.setName(learningObject.getName());
		learningObjectVo.setRubrikId(learningObject.getRubrik().getId());
		learningObjectVo.setStatus(learningObject.getStatus());

		return learningObjectVo;
	}

	private LearningObjectVo getEvalLearningObject(LearningObject learningObject, String type) {

		LearningObjectVo learningObjectVo = getLearningObjectVo(learningObject);
		learningObjectVo.setRubrikName(learningObject.getRubrik().getName());

		Integer totalQuestions = learningObject.getRubrik().getRubrikQuestions().size();
		Integer completedQuestions = learningObject.getLobeScores().size();
		learningObjectVo.setPercentage((double) completedQuestions * 100 / totalQuestions);

		return learningObjectVo;
	}

	private QuestionVo getQuestionVo(QuestionMaster questionMaster, Integer score) {

		QuestionVo questionVo = new QuestionVo();

		questionVo.setId(questionMaster.getId());
		questionVo.setOptional(questionMaster.isOptional());
		questionVo.setQuestion(questionMaster.getQuestion());
		questionVo.setQuestionMeta(questionMaster.getQuestionMeta());
		questionVo.setScore0(questionMaster.getScore0());
		questionVo.setScore0Images(questionMaster.getScore0Images());
		questionVo.setScore1(questionMaster.getScore1());
		questionVo.setScore1Images(questionMaster.getScore1Images());
		questionVo.setScore2(questionMaster.getScore2());
		questionVo.setScore2Images(questionMaster.getScore2Images());
		questionVo.setScore3(questionMaster.getScore3());
		questionVo.setScore3Images(questionMaster.getScore3Images());
		questionVo.setDimensionId(questionMaster.getQualityDimensionMaster().getId());
		questionVo.setScore(score);

		return questionVo;
	}

}
