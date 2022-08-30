package com.example.mimir.user.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
	@Test
	@DisplayName("회원가입 인스턴스 생성")
	void join() {
		// given
		String email = "";
		String password = "";
		String nickname = "";

		// when
		User user = User.join(email, password, nickname);

		// then
		assertThat(user.getEmail()).isEqualTo(email); // email 이 설정됨
		assertThat(user.getNickname()).isEqualTo(nickname); // nickname 이 설정됨
		assertThat(user.getRegisteredAt()).isNotNull(); // registeredAt 이 설정됨
		assertThat(user.getUpdatedAt()).isNotNull(); // updatedAt 이 설정됨
	}
}
