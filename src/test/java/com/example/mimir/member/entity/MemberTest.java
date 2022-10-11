package com.example.mimir.member.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.mimir.member.domain.entity.Member;

public class MemberTest {
	@Test
	@DisplayName("회원가입 인스턴스 생성")
	void join() {
		// given
		String email = "mimir@mimir.com";
		String password = "mimir";
		String nickname = "mimir";

		// when
		Member member = Member.signup(email, password, nickname);

		// then
		assertThat(member.getEmail()).isEqualTo(email); // email 이 설정됨
		assertThat(member.getNickname()).isEqualTo(nickname); // nickname 이 설정됨
		assertThat(member.getRegisteredAt()).isNotNull(); // registeredAt 이 설정됨
		assertThat(member.getUpdatedAt()).isNotNull(); // updatedAt 이 설정됨
	}
}
