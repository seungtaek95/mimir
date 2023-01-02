package com.example.mimir.authentication.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthExceptionEnum {
	DUPLICATE_EMAIL("이메일이 이미 사용중입니다."),
	SIGN_IN_FAILED("이메일 또는 비밀번호를 잘못 입력했습니다."),
	INVALID_SESSION_ID("세션이 유효하지 않습니다.");

	private final String message;
}
