package com.example.mimir.authentication.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignupExceptionEnum {
	DUPLICATE_EMAIL("이메일이 이미 사용중입니다.");

	private final String message;
}
