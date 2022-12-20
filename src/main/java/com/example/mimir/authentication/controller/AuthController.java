package com.example.mimir.authentication.controller;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mimir.authentication.controller.response.SignupResponse;
import com.example.mimir.authentication.domain.entity.MemberSession;
import com.example.mimir.authentication.domain.exception.AuthException;
import com.example.mimir.authentication.service.AuthService;
import com.example.mimir.authentication.service.dto.SigninDto;
import com.example.mimir.authentication.service.dto.SignupDto;
import com.example.mimir.common.model.ErrorResponse;
import com.example.mimir.common.util.UuidUtils;
import com.example.mimir.member.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public SignupResponse signup(
			@RequestBody @Valid SignupDto signupDto) {

		Member member = authService.signup(signupDto);

		return new SignupResponse(member.getId());
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public void signin(
			@RequestBody @Valid SigninDto signinDto,
			HttpServletResponse response) {

		MemberSession memberSession = authService.signin(signinDto);

		response.addCookie(this.createSessionCookie(memberSession));
	}

	@ExceptionHandler(AuthException.class)
	private ResponseEntity<ErrorResponse> handleSignupException(AuthException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	private Cookie createSessionCookie(MemberSession memberSession) {
		Cookie sessionCookie = new Cookie(MemberSession.COOKIE_NAME, Arrays.toString(memberSession.getId()));
		sessionCookie.setHttpOnly(true);

		return sessionCookie;
	}
}
