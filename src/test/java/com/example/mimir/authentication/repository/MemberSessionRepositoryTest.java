package com.example.mimir.authentication.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.mimir.authentication.domain.entity.MemberSession;

@SpringBootTest
@Transactional
public class MemberSessionRepositoryTest {
	@Autowired
	private MemberSessionRepository memberSessionRepository;

	@Test
	@DisplayName("사용자 세션 저장 성공")
	void save() {
		// given
		UUID memberId = UUID.randomUUID();
		MemberSession memberSession = new MemberSession(memberId);

		// when
		memberSessionRepository.save(memberSession);

		// then
		assertThat(memberSession.getId()).isNotNull(); // id가 생성됨
	}
}
