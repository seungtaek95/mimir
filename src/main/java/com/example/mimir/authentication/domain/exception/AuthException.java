package com.example.mimir.authentication.domain.exception;

public class AuthException extends RuntimeException {
	public AuthException(AuthExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMessage());
	}
}
