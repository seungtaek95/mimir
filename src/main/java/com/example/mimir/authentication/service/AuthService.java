package com.example.mimir.authentication.service;

import com.example.mimir.authentication.domain.entity.MemberSession;
import com.example.mimir.authentication.service.dto.SigninDto;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.authentication.service.dto.SignupDto;

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
