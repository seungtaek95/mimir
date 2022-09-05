package com.example.mimir.authentication.domain.exception;

public class SignupException extends RuntimeException {
	public SignupException(SignupExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMessage());
	}
}
