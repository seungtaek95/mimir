package com.example.mimir.authentication.service;

import com.example.mimir.user.domain.entity.User;
import com.example.mimir.authentication.service.dto.SignupDto;

public interface AuthenticationService {
	/**
	 * 회원가입 처리
	 */
	User signup(SignupDto signupDto);
}
