package xyz.qwerty.lobetoolapis.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.entity.LearningObject;
import xyz.qwerty.lobetoolapis.entity.QualityDimensionMaster;
import xyz.qwerty.lobetoolapis.entity.Role;
import xyz.qwerty.lobetoolapis.entity.Rubrik;
import xyz.qwerty.lobetoolapis.entity.RubrikTypeMaster;
import xyz.qwerty.lobetoolapis.entity.User;
import xyz.qwerty.lobetoolapis.repository.LearningObjectRepository;
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
import xyz.qwerty.lobetoolapis.vo.RoleVo;
import xyz.qwerty.lobetoolapis.vo.RubrikTypeVo;
import xyz.qwerty.lobetoolapis.vo.RubrikVo;

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
	public LearningObjectVo assignLearningObject(String userId, Integer rubrikId, String rubrikCode, String msgSubject,
			String msgBody, String learningObjectName, String evaluatorEmail) {

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
			} else {
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

			LearningObject learningObject = new LearningObject();
			learningObject.setCode(rubrikCode + "-" + UUID.randomUUID().toString());
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
			message.setText(msgBody);
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

			Set<LearningObject> learningObjects = type.equals(Constants.TYPE_GENERATOR) ? u.getLearningObject()
					: u.getLearningObject2();

			return learningObjects.stream()
					.filter(l -> type.equals(Constants.TYPE_GENERATOR) || !l.getStatus().equals("assigned"))
					.map(l -> getEvalLearningObject(l, type)).collect(Collectors.toList());
		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
	}

	@Override
	public LearningObjectVo updateLearningObject(String userId, String code, String grade, String subject,
			String chapter, String moduleName, String repositoryName) {

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

	private LearningObjectVo getEvalLearningObject(LearningObject learningObject, String type) {

		LearningObjectVo learningObjectVo = new LearningObjectVo();
		learningObjectVo.setCode(learningObject.getCode());
		learningObjectVo.setRubrikName(learningObject.getRubrik().getName());
		learningObjectVo.setName(learningObject.getName());
		learningObjectVo.setCreatedTs(learningObject.getCreatedTs());
		learningObjectVo.setStatus(learningObject.getStatus());

		Integer totalQuestions = learningObject.getRubrik().getRubrikQuestions().size();
		Integer completedQuestions = learningObject.getLobeScores().size();
		learningObjectVo.setPercentage((double) completedQuestions * 100 / totalQuestions);

		return learningObjectVo;
	}

	private LearningObjectVo getLearningObjectVo(LearningObject learningObject) {

		LearningObjectVo learningObjectVo = new LearningObjectVo();
		learningObjectVo.setAssignedBy(learningObject.getUser().getEmail());
		learningObjectVo.setAssignedTo(learningObject.getUser2().getEmail());
		learningObjectVo.setCode(learningObject.getCode());
		learningObjectVo.setCreatedTs(learningObject.getCreatedTs());
		learningObjectVo.setName(learningObject.getName());
		learningObjectVo.setRubrikId(learningObject.getRubrik().getId());
		learningObjectVo.setStatus(learningObject.getStatus());

		return learningObjectVo;
	}

	@Override
	public RubrikVo getLobeRubrik(String userId, String code) {
		
		Optional<LearningObject> learningObject = learningObjectRepository.findById(code);
		if (learningObject.isPresent()) {

			LearningObject l = learningObject.get();
			if (!l.getUser2().getEmail().equals(userId)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Learning object not assigned to this user");
			}
			
			return rubrikService.getRubrikDetails(l.getUser().getEmail(), l.getRubrik().getId());
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid learning object code");
	}

	@Override
	public LearningObjectVo updateLobeScores(String userId, String json, Boolean submit) {
		
		Optional<LearningObject> learningObject = learningObjectRepository.findById(code);
		if (learningObject.isPresent()) {

			LearningObject l = learningObject.get();
			if (!l.getUser2().getEmail().equals(userId)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Learning object not assigned to this user");
			}
			
			//TODO: lobe scores update
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid learning object code");
	}

}
