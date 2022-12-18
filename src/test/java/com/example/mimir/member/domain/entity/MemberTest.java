package com.example.mimir.member.domain.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {
	@Test
	@DisplayName("회원가입 인스턴스 생성")
	void signup() {
		// given
		String email = "mimir@mimir.com";
		String password = "mimir";
		String nickname = "mimir";

		// when
		Member member = Member.signup(email, password, nickname);

		// then
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getRegisteredAt()).isNotNull();
		assertThat(member.getUpdatedAt()).isNotNull();
		assertThat(member.getDisabledAt()).isNull();
	}
}
