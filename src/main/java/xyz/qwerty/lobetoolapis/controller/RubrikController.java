package xyz.qwerty.lobetoolapis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.service.AuthUserService;
import xyz.qwerty.lobetoolapis.service.RubrikService;
import xyz.qwerty.lobetoolapis.util.ResponseBuilder;
import xyz.qwerty.lobetoolapis.vo.DimensionVo;
import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;
import xyz.qwerty.lobetoolapis.vo.RubrikVo;

@RestController
@RequestMapping("/api/rubrik")
public class RubrikController {

	@Autowired
	AuthUserService authUserService;

	@Autowired
	RubrikService rubrikService;

	@Value("${lite.rubrik.id}")
	private Integer liteRubrikId;

	@Value("${premium.rubrik.id}")
	private Integer premiumRubrikId;

	@Value("${lite.sample.id}")
	private Integer liteSampleId;

	@Value("${premium.sample.id}")
	private Integer premiumSampleId;

	@PostMapping("/create-rubrik")
	public ResponseEntity<ResponseBuilder> createNewrubrik(@RequestHeader(name = "Authorization") String authorization,
			@RequestParam(name = "rubrikTypeId") Integer rubrikTypeId,
			@RequestParam(name = "dimensionIds") String dimensionIds) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			RubrikVo rubrikVo = rubrikService.createRubrik(userId, rubrikTypeId, dimensionIds);

			responseBuilder.setData(rubrikVo);

			responseBuilder.setCode(HttpStatus.CREATED.value());
			responseBuilder.setStatus(HttpStatus.CREATED.getReasonPhrase());
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseBuilder> getAllRubriks(@RequestHeader(name = "Authorization") String authorization) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			List<RubrikVo> rubriks = rubrikService.getAllRubriks(userId);

			responseBuilder.setData(rubriks);

			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/browse/{rubrikTypeId}")
	public ResponseEntity<ResponseBuilder> browseSampleRubrik(
			@RequestHeader(name = "Authorization") String authorization,
			@PathVariable(name = "rubrikTypeId") Integer rubrikTypeId) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			Integer rubrikId;
			if (rubrikTypeId == premiumRubrikId) {
				rubrikId = premiumSampleId;
			} else if (rubrikTypeId == liteRubrikId) {
				rubrikId = liteSampleId;
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rubrik type id");
			}

			RubrikVo rubrik = rubrikService.getRubrikDetails(rubrikId);

			responseBuilder.setData(rubrik);

			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/{rubrikId}")
	public ResponseEntity<ResponseBuilder> getRubrikDetails(@RequestHeader(name = "Authorization") String authorization,
			@PathVariable(name = "rubrikId") Integer rubrikId) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			RubrikVo rubrik = rubrikService.getRubrikDetails(userId, rubrikId);

			responseBuilder.setData(rubrik);

			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/questions")
	public ResponseEntity<ResponseBuilder> getDimensionQuestions(
			@RequestHeader(name = "Authorization") String authorization,
			@RequestParam("dimensionId") Integer dimensionId, @RequestParam("rubrikTypeId") Integer rubrikTypeId) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			DimensionVo dimensionVo = rubrikService.getDimensionVo(dimensionId, rubrikTypeId);
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
			responseBuilder.setData(dimensionVo);
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@PostMapping("/{rubrikId}/update")
	public ResponseEntity<ResponseBuilder> updateRubrik(@RequestHeader(name = "Authorization") String authorization,
			@PathVariable(name = "rubrikId") Integer rubrikId, @RequestParam(name = "add") List<Integer> addQuestionIds,
			@RequestParam(name = "remove") List<Integer> removeQuestionIds,
			@RequestParam(name = "submit") Boolean submit) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			RubrikVo rubrikVo = rubrikService.updateQuestionsAndSubmit(rubrikId, userId, addQuestionIds,
					removeQuestionIds, submit);

			responseBuilder.setData(rubrikVo);
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		} else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}
}
