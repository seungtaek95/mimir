package com.example.mimir.authentication.domain.service.dto;

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
