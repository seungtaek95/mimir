package com.example.mimir.authentication.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.mimir.authentication.domain.entity.MemberSession;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.domain.entity.MemberFixture;
import com.example.mimir.member.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberSessionRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberSessionRepository memberSessionRepository;

	@Test
	@DisplayName("사용자 세션 저장 성공")
	void save() {
		// given
		Member member = MemberFixture.noId();
		memberRepository.save(member);

		MemberSession memberSession = new MemberSession(member.getId());

		// when
		memberSessionRepository.save(memberSession);

		// then
		assertThat(memberSession.getId()).isNotNull(); // id가 생성됨
	}
}
