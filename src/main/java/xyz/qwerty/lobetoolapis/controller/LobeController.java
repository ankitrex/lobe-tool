package xyz.qwerty.lobetoolapis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.qwerty.lobetoolapis.service.AuthUserService;
import xyz.qwerty.lobetoolapis.service.LobeService;
import xyz.qwerty.lobetoolapis.util.ResponseBuilder;

@RestController
@RequestMapping("/api")
public class LobeController {

	@Autowired
	AuthUserService	authUserService;

	@Autowired
	LobeService		lobeService;

	@GetMapping("/get-dimensions")
	public ResponseEntity<ResponseBuilder> getAllDimensions(@RequestHeader(name = "Authorization") String authorization) {

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		authUserService.checkUserAccess(accessToken, permission);

		ResponseBuilder responseBuilder = new ResponseBuilder();
		responseBuilder.setCode(HttpStatus.OK.value());
		responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		responseBuilder.setData(lobeService.getAllDimensions());

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/get-rubrik-types")
	public ResponseEntity<ResponseBuilder> getAllRubrikTypes(@RequestHeader(name = "Authorization") String authorization) {

		String accessToken = authUserService.getAccessTokenFromHeader(authorization);
		String permission = "create_rubrik";

		authUserService.checkUserAccess(accessToken, permission);

		ResponseBuilder responseBuilder = new ResponseBuilder();
		responseBuilder.setCode(HttpStatus.OK.value());
		responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		responseBuilder.setData(lobeService.getAllRubrikTypes());

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}
}
