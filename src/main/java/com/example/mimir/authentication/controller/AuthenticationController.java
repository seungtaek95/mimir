package com.example.mimir.authentication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mimir.authentication.controller.response.SignupResponse;
import com.example.mimir.authentication.domain.exception.SignupException;
import com.example.mimir.authentication.service.AuthenticationService;
import com.example.mimir.authentication.service.dto.SignupDto;
import com.example.mimir.common.model.ErrorResponse;
import com.example.mimir.user.domain.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AuthenticationController {
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public SignupResponse signup(@RequestBody @Valid SignupDto signupDto) {
		User user = authenticationService.signup(signupDto);

		return new SignupResponse(user.getId());
	}

	@ExceptionHandler(SignupException.class)
	private ResponseEntity<ErrorResponse> handleSignupException(SignupException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}
}
