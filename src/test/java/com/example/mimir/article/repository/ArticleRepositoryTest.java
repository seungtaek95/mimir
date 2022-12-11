package com.example.mimir.article.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.domain.entity.MemberFixture;
import com.example.mimir.member.repository.MemberRepository;

@Transactional
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ArticleRepository sut;

	private Member testMember;

	@BeforeEach
	void saveMember() {
		testMember = MemberFixture.create();
		memberRepository.save(testMember);
	}

	@Test
	@DisplayName("게시글 저장 성공")
	void save() {
		// given
		Member member = this.testMember;
		String title = "title";
		String content = "content";
		boolean isPrivate = false;
		Article article = new Article(member, title, content, isPrivate);

		// when
		Article result = sut.save(article);

		// then
		assertThat(result.getId()).isNotNull();
	}
}
