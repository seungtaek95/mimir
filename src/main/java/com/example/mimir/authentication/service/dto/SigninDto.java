package com.example.mimir.authentication.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record SigninDto(
	@Email
	@NotNull
	String email,

	@NotNull
	String password
) {
}
