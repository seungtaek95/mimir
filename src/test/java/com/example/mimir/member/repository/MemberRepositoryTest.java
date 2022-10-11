package com.example.mimir.member.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.mimir.member.domain.entity.Member;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName("사용자 저장 성공")
	void save() {
		// given
		Member member = Member.signup(
			"seungtaek95@naver.com",
			"seungtaek",
			"seungtaek95");

		// when
		memberRepository.save(member);

		// then
		assertThat(member.getId()).isNotNull(); // id가 생성됨
	}
}
