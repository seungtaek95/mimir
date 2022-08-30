package com.example.mimir.user.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.mimir.user.entity.User;

@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("사용자 저장 성공")
	@Transactional
	void save() {
		// given
		User user = User.join(
			"seungtaek95@naver.com",
			"seungtaek",
			"seungtaek95");

		// when
		userRepository.save(user);

		// then
		assertThat(user.getId()).isNotNull(); // id가 생성됨
	}
}
