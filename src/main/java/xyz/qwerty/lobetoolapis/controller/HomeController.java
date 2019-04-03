package xyz.qwerty.lobetoolapis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.qwerty.lobetoolapis.service.LobeService;
import xyz.qwerty.lobetoolapis.util.ResponseBuilder;

@RestController
@RequestMapping("/open")
public class HomeController {

	@Autowired
	LobeService lobeService;

	@GetMapping("/get-roles")
	public ResponseEntity<ResponseBuilder> getAllRoles() {

		ResponseBuilder responseBuilder = new ResponseBuilder();
		responseBuilder.setCode(HttpStatus.OK.value());
		responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		responseBuilder.setData(lobeService.getAllRoles());

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/get-dimensions")
	public ResponseEntity<ResponseBuilder> getAllDimensions() {

		ResponseBuilder responseBuilder = new ResponseBuilder();
		responseBuilder.setCode(HttpStatus.OK.value());
		responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		responseBuilder.setData(lobeService.getAllDimensions());

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}

	@GetMapping("/get-rubrik-types")
	public ResponseEntity<ResponseBuilder> getAllRubrikTypes() {

		ResponseBuilder responseBuilder = new ResponseBuilder();
		responseBuilder.setCode(HttpStatus.OK.value());
		responseBuilder.setStatus(HttpStatus.OK.getReasonPhrase());
		responseBuilder.setData(lobeService.getAllRubrikTypes());

		return new ResponseEntity<>(responseBuilder, HttpStatus.OK);
	}
}
