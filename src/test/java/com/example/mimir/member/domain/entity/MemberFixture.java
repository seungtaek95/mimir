package com.example.mimir.member.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.mimir.common.util.PasswordEncoderUtils;
import com.example.mimir.common.util.UuidUtils;

public class MemberFixture {
	public static final UUID id = UUID.randomUUID();
	public static final String email = "test@mimir.com";
	public static final String password = "test";
	public static final String nickname = "test";

	public static Member create() {
		return new Member(
			UuidUtils.uuidToBytes(id),
			id,
			email,
			PasswordEncoderUtils.encode(password),
			nickname,
			LocalDateTime.now(),
			LocalDateTime.now(),
			null);
	}

	public static Member noId() {
		return new Member(
			null,
			null,
			email,
			PasswordEncoderUtils.encode(password),
			nickname,
			LocalDateTime.now(),
			LocalDateTime.now(),
			null);
	}
}
