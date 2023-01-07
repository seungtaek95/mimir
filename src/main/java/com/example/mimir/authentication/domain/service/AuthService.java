package com.example.mimir.authentication.domain.service;

import com.example.mimir.authentication.domain.entity.MemberSession;
import com.example.mimir.authentication.domain.service.dto.SigninDto;
import com.example.mimir.authentication.domain.service.dto.SignupDto;
import com.example.mimir.member.domain.entity.Member;

public interface AuthService {
	/**
	 * 회원가입 처리
	 */
	Member signup(SignupDto signupDto);

	/**
	 * 로그인 처리
	 */
	MemberSession signin(SigninDto signinDto);

	Member getMemberByCookieValue(String cookieValue);
}
