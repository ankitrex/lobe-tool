package xyz.qwerty.lobetoolapis.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.service.AuthUserService;
import xyz.qwerty.lobetoolapis.service.LobeService;
import xyz.qwerty.lobetoolapis.util.Constants;
import xyz.qwerty.lobetoolapis.util.ResponseBuilder;
import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;

@RestController
@RequestMapping("/api/lobe")
@Validated
public class LobeController {

	@Autowired
	AuthUserService authUserService;

	@Autowired
	LobeService lobeService;

	@GetMapping("/get-dimensions")
	public ResponseEntity<ResponseBuilder> getAllDimensions(
			@RequestHeader(name = "Authorization") String authorization) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
			responseBuilder.setData(lobeService.getAllDimensions());

		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/get-rubrik-types")
	public ResponseEntity<ResponseBuilder> getAllRubrikTypes(
			@RequestHeader(name = "Authorization") String authorization) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
			responseBuilder.setData(lobeService.getAllRubrikTypes());

		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@PostMapping("/assign")
	public ResponseEntity<ResponseBuilder> assignRubrik(@RequestHeader(name = "Authorization") String authorization,
			@NotNull(message = "Rubrik ID cannot be blank") @RequestParam(name = "rubrikId") Integer rubrikId,
			@NotBlank(message = "Message title cannot be blank") @RequestParam(name = "msgSubject") String msgSubject,
			@NotBlank(message = "Message body be blank") @RequestParam(name = "msgBody") String msgBody,
			@NotBlank(message = "LOBE name cannot be blank") @RequestParam(name = "learningObjectName") String learningObjects,
			@NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email address") @RequestParam(name = "evaluatorEmail") String evaluatorEmail) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "assign_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			LearningObjectVo learningObjectVo = lobeService.assignLearningObject(userId, rubrikId, msgSubject, msgBody,
					learningObjects, evaluatorEmail);

			responseBuilder.setData(learningObjectVo);
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/evaluations/all")
	public ResponseEntity<ResponseBuilder> getAllEvaluations(
			@RequestHeader(name = "Authorization") String authorization, @RequestParam(name = "type") String type) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission;
		if (Constants.TYPE_GENERATOR.equals(type)) {
			permission = "generator_analytics";
		} else if (Constants.TYPE_EVALUATOR.equals(type)) {
			permission = "evaluator_analytics";
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid type");
		}

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			List<LearningObjectVo> learningObjects = lobeService.getAllEvaluations(userId, type);

			responseBuilder.setData(learningObjects);
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@PostMapping("/new-review")
	public ResponseEntity<ResponseBuilder> createNewReview(@RequestHeader(name = "Authorization") String authorization,
			@NotBlank(message = "Code cannot be blank") @RequestParam(name = "code") String code,
			@NotBlank(message = "Message title cannot be blank") @RequestParam(name = "grade") String grade,
			@RequestParam(name = "subject") String subject, @RequestParam(name = "chapter") String chapter,
			@NotBlank(message = "Learning object name cannot be blank") @RequestParam(name = "lobeName") String lobeName,
			@NotBlank(message = "Repository name cannot be blank") @RequestParam(name = "repositoryName") String repositoryName) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "evaluate_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			LearningObjectVo learningObject = lobeService.updateLearningObject(userId, code, grade, subject, chapter,
					lobeName, repositoryName);

			responseBuilder.setData(learningObject);
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/get-lobe-questions")
	public ResponseEntity<ResponseBuilder> getLobeQuestions(@RequestHeader(name = "Authorization") String authorization,
			@RequestParam(name = "code") String code) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "evaluate_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			LearningObjectVo learningObjectVo = lobeService.getLobeRubrik(userId, code);

			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
			responseBuilder.setData(learningObjectVo);

		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@PostMapping("/update-lobe-score")
	public ResponseEntity<ResponseBuilder> createNewReview(@RequestHeader(name = "Authorization") String authorization,
			@RequestParam(name = "code") String code, @RequestParam(name = "json") Map<Integer, Integer> json,
			@RequestParam(name = "submit") Boolean submit) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "evaluate_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			lobeService.updateLobeScores(userId, code, json, submit);

			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}
}
