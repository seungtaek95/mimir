package com.example.mimir.authentication.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record SignupDto(
	@Email
	@NotNull
	String email,

	@NotNull
	String password,

	@NotNull
	String nickname
) {
}
