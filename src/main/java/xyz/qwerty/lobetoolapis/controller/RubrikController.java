package xyz.qwerty.lobetoolapis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.qwerty.lobetoolapis.service.AuthUserService;
import xyz.qwerty.lobetoolapis.service.RubrikService;
import xyz.qwerty.lobetoolapis.util.ResponseBuilder;
import xyz.qwerty.lobetoolapis.vo.RubrikVo;

@RestController
@RequestMapping("/api/rubrik")
public class RubrikController {

	@Autowired
	AuthUserService	authUserService;

	@Autowired
	RubrikService	rubrikService;

	@PostMapping("/create-rubrik")
	public ResponseEntity<ResponseBuilder> createNewrubrik(@RequestHeader(name = "Authorization") String authorization, @RequestParam(name = "rubrikTypeId") Integer rubrikTypeId,
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
		}
		else {
			responseBuilder.setCode(HttpStatus.UNAUTHORIZED.value());
			responseBuilder.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}
}
