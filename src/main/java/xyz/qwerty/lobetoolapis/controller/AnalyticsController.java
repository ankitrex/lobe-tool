package xyz.qwerty.lobetoolapis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import xyz.qwerty.lobetoolapis.service.AnalyticsService;
import xyz.qwerty.lobetoolapis.service.AuthUserService;
import xyz.qwerty.lobetoolapis.util.Constants;
import xyz.qwerty.lobetoolapis.util.ResponseBuilder;
import xyz.qwerty.lobetoolapis.vo.ComparativeAnalysisVo;
import xyz.qwerty.lobetoolapis.vo.LearningObjectVo;
import xyz.qwerty.lobetoolapis.vo.LobeSummaryVo;
import xyz.qwerty.lobetoolapis.vo.StrengthWeaknessAnalysisVo;

@RestController
@RequestMapping("/api/analytics")
@Validated
public class AnalyticsController {

	@Autowired
	AuthUserService		authUserService;

	@Autowired
	AnalyticsService	analyticsService;

	@GetMapping("/all-lobes")
	public ResponseEntity<ResponseBuilder> getAllEvaluations(@RequestHeader(name = "Authorization") String authorization, @RequestParam(name = "rubrikId") Integer rubrikId,
			@RequestParam(name = "type") String type) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission;
		if (Constants.TYPE_GENERATOR.equals(type)) {
			permission = "generator_analytics";
		}
		else if (Constants.TYPE_EVALUATOR.equals(type)) {
			permission = "evaluator_analytics";
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid type");
		}

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, permission);

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			List<LearningObjectVo> learningObjects = analyticsService.getAllLobes(userId, type, rubrikId);

			responseBuilder.setData(learningObjects);
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		}
		else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/lobe-summary")
	public ResponseEntity<ResponseBuilder> getLobeSummary(@RequestHeader(name = "Authorization") String authorization, @RequestParam(name = "lobeIds") List<Integer> lobeIds) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, "generator_analytics") || authUserService.checkUserAccess(accessToken, "evaluator_analytics");

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			List<LobeSummaryVo> lobeSummary = analyticsService.getLobeSummary(lobeIds, userId);

			responseBuilder.setData(lobeSummary);
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		}
		else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/strength-weakness")
	public ResponseEntity<ResponseBuilder> getStrengthWeaknessAnalysis(@RequestHeader(name = "Authorization") String authorization,
			@RequestParam(name = "lobeIds") List<Integer> lobeIds) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, "generator_analytics") || authUserService.checkUserAccess(accessToken, "evaluator_analytics");

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			List<StrengthWeaknessAnalysisVo> lobeSummary = analyticsService.getStrengthWeaknessAnalysis(lobeIds, userId);

			responseBuilder.setData(lobeSummary);
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		}
		else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/comparative-analysis")
	public ResponseEntity<ResponseBuilder> getComparativeAnalysis(@RequestHeader(name = "Authorization") String authorization,
			@RequestParam(name = "lobeIds") List<Integer> lobeIds) {

		ResponseBuilder responseBuilder = new ResponseBuilder();

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);

		Boolean hasPermission = authUserService.checkUserAccess(accessToken, "generator_analytics") || authUserService.checkUserAccess(accessToken, "evaluator_analytics");

		if (hasPermission) {

			String userId = authUserService.getUserId(accessToken);

			List<ComparativeAnalysisVo> cmpAnalysis = analyticsService.getComparativeAnalysis(lobeIds, userId);

			responseBuilder.setData(cmpAnalysis);
			responseBuilder.setCode(HttpStatus.OK.value());
			responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		}
		else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}
}
