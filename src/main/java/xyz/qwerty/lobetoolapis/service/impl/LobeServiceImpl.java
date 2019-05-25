package xyz.qwerty.lobetoolapis.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.entity.LearningObject;
import xyz.qwerty.lobetoolapis.entity.LobeScores;
import xyz.qwerty.lobetoolapis.entity.LobeScoresKey;
import xyz.qwerty.lobetoolapis.entity.LobeTemp;
import xyz.qwerty.lobetoolapis.entity.QualityDimensionMaster;
import xyz.qwerty.lobetoolapis.entity.QuestionMaster;
import xyz.qwerty.lobetoolapis.entity.Role;
import xyz.qwerty.lobetoolapis.entity.Rubrik;
import xyz.qwerty.lobetoolapis.entity.RubrikQualityDimensions;
import xyz.qwerty.lobetoolapis.entity.RubrikTypeMaster;
import xyz.qwerty.lobetoolapis.entity.User;
import xyz.qwerty.lobetoolapis.repository.LearningObjectRepository;
import xyz.qwerty.lobetoolapis.repository.LobeScoresRepository;
import xyz.qwerty.lobetoolapis.repository.LobeTempRepository;
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
	RoleRepository roleRepository;

	@Autowired
	QualityDimensionMasterRepository qualityDimensionMasterRepository;

	@Autowired
	RubrikTypeMasterRepository rubrikTypeMasterRepository;

	@Autowired
	RubrikRepository rubrikRepository;

	@Autowired
	LearningObjectRepository learningObjectRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JavaMailSender emailSender;

	@Autowired
	RubrikService rubrikService;

	@Autowired
	LobeScoresRepository lobeScoresRepository;

	@Autowired
	LobeTempRepository lobeTempRepository;

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
	public LearningObjectVo assignLearningObject(String userId, Integer rubrikId, String msgSubject, String msgBody,
			String learningObjects, String evaluatorEmail) {

		Optional<Rubrik> result = rubrikRepository.findById(rubrikId);
		if (result.isPresent()) {

			Rubrik rubrik = result.get();
			if (!userId.equals(rubrik.getUser().getEmail())) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Rubrik doesn't belong to this user");
			}
			if (Constants.STATUS_INCOMPLETE.equals(rubrik.getStatus())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete rubrik can't be assigned");
			}

			String code = UUID.randomUUID().toString();

			List<String> lobes = Arrays.asList(learningObjects.split(",")).stream().map(String::trim).distinct()
					.collect(Collectors.toList());

			lobes.forEach(lobeName -> {

				LobeTemp lobeTemp = new LobeTemp();
				lobeTemp.setAssignedBy(userId);
				lobeTemp.setAssignedTo(evaluatorEmail);
				lobeTemp.setCode(code);
				lobeTemp.setCreatedTs(LocalDateTime.now());
				lobeTemp.setLearningObjectName(lobeName.trim());
				lobeTemp.setRubrikId(rubrikId);
				lobeTemp.setRubrikName(rubrik.getName());
				lobeTemp.setStatus(Constants.STATUS_ASSIGNED);

				lobeTempRepository.save(lobeTemp);
			});

			ExecutorService executorService = Executors.newFixedThreadPool(1);

			StringBuilder sb = new StringBuilder();
			sb.append(msgSubject);
			sb.append("\n");
			sb.append("code: " + code);
			sb.append("\n");
			sb.append("learning objects: " + lobes);

			System.out.println(sb.toString());

			executorService.execute(new Runnable() {

				@Override
				public void run() {
					SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(evaluatorEmail);
					message.setSubject(msgSubject);
					message.setText(sb.toString());
					emailSender.send(message);
				}
			});

			return null;
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rubrik id");
	}

	@Override
	public List<LearningObjectVo> getAllEvaluations(String userId, String type) {

		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {

			User u = user.get();

			Set<LearningObject> learningObjects = type.equals(Constants.TYPE_GENERATOR) ? u.getLearningObject()
					: u.getLearningObject2();
			
			List<LobeTemp> tempLobes = type.equals(Constants.TYPE_GENERATOR) ? lobeTempRepository.findAllByAssignedBy(u.getEmail()) : lobeTempRepository.findAllByAssignedTo(u.getEmail());
			
			List<LearningObjectVo> lobes = tempLobes.stream().map(tl -> getTempEvalLobe(tl)).filter(tl -> tl.getStatus().equals(Constants.STATUS_ASSIGNED)).collect(Collectors.toList());
			
			List<LearningObjectVo> learningObjs = learningObjects.stream().map(l -> getEvalLearningObject(l, type)).collect(Collectors.toList());
			learningObjs.addAll(lobes);
			
			return learningObjs;
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
	}

	@Override
	public LearningObjectVo updateLearningObject(String userId, String code, String grade, String subject,
			String chapter, String lobeName, String repositoryName) {

		Optional<LobeTemp> lobeTempOpt = lobeTempRepository.findByCodeAndLearningObjectName(code, lobeName);
		if (lobeTempOpt.isPresent()) {

			LobeTemp lobeTemp = lobeTempOpt.get();

			if (!lobeTemp.getAssignedTo().equals(userId)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Learning object not assigned to this user");
			}
			if (!lobeTemp.getStatus().equals(Constants.STATUS_ASSIGNED)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code already submitted for learning object");
			}

			Rubrik r = new Rubrik();
			r.setId(lobeTemp.getRubrikId());

			User u1 = new User();
			u1.setEmail(lobeTemp.getAssignedBy());

			User u2 = new User();
			u2.setEmail(lobeTemp.getAssignedTo());

			LearningObject learningObject = new LearningObject();
			learningObject.setChapter(chapter);
			learningObject.setCode(code);
			learningObject.setCreatedTs(LocalDateTime.now());
			learningObject.setGrade(grade);
			learningObject.setModuleName(lobeName);
			learningObject.setRepositoryName(repositoryName);
			learningObject.setRubrik(r);
			learningObject.setStatus(Constants.STATUS_INCOMPLETE);
			learningObject.setUpdatedTs(LocalDateTime.now());
			learningObject.setSubject(subject);
			learningObject.setUser(u1);
			learningObject.setUser2(u2);

			learningObjectRepository.save(learningObject);

			lobeTemp.setStatus(Constants.STATUS_INITIATED);
			lobeTempRepository.save(lobeTemp);

			return getLearningObjectVo(learningObject);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid learning object");
		}
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

			List<Integer> questions = l.getRubrik().getRubrikQuestions().stream()
					.map(q -> q.getRubrikQuestionsKey().getQuestionMaster().getId()).collect(Collectors.toList());

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
				} else {
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

			List<QuestionVo> rubrikQuestions = questions.stream().filter(q -> q.getDimensionId() == dimensionVo.getId())
					.collect(Collectors.toList());
			dimensionVo.setQuestions(rubrikQuestions);

			dimensionVoList.add(dimensionVo);
		});

		learningObjectVo.setDimensionVos(dimensionVoList);

		return learningObjectVo;
	}

	private LearningObjectVo getLearningObjectVo(LearningObject learningObject) {

		LearningObjectVo learningObjectVo = new LearningObjectVo();
		learningObjectVo.setId(learningObject.getId());
		learningObjectVo.setAssignedBy(learningObject.getUser().getEmail());
		learningObjectVo.setAssignedTo(learningObject.getUser2().getEmail());
		learningObjectVo.setCode(learningObject.getCode());
		learningObjectVo.setCreatedTs(learningObject.getCreatedTs());
		learningObjectVo.setUpdatedTs(learningObject.getUpdatedTs());
		learningObjectVo.setRubrikId(learningObject.getRubrik().getId());
		learningObjectVo.setStatus(learningObject.getStatus());
		learningObjectVo.setName(learningObject.getModuleName());

		return learningObjectVo;
	}
	
	private LearningObjectVo getTempEvalLobe(LobeTemp lobeTemp) {

		LearningObjectVo learningObjectVo = new LearningObjectVo();
		learningObjectVo.setAssignedBy(lobeTemp.getAssignedBy());
		learningObjectVo.setAssignedTo(lobeTemp.getAssignedTo());
		learningObjectVo.setCode(lobeTemp.getCode());
		learningObjectVo.setCreatedTs(lobeTemp.getCreatedTs());
		learningObjectVo.setRubrikId(lobeTemp.getRubrikId());
		learningObjectVo.setStatus(lobeTemp.getStatus());
		learningObjectVo.setName(lobeTemp.getLearningObjectName());
		learningObjectVo.setRubrikName(lobeTemp.getRubrikName());
		
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
		questionVo.setScore0Images(getImageList(questionMaster.getScore0Images()));
		questionVo.setScore1(questionMaster.getScore1());
		questionVo.setScore1Images(getImageList(questionMaster.getScore1Images()));
		questionVo.setScore2(questionMaster.getScore2());
		questionVo.setScore2Images(getImageList(questionMaster.getScore2Images()));
		questionVo.setScore3(questionMaster.getScore3());
		questionVo.setScore3Images(getImageList(questionMaster.getScore3Images()));
		questionVo.setDimensionId(questionMaster.getQualityDimensionMaster().getId());
		questionVo.setScore(score);

		return questionVo;
	}

	private List<String> getImageList(String imagesString) {

		if (StringUtils.isEmpty(imagesString)) {
			return new ArrayList<>();
		}

		return Arrays.asList(imagesString.split(","));
	}

}
