package com.example.mimir.article.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.mimir.article.domain.service.ArticleService;
import com.example.mimir.article.domain.service.dto.CreateArticleDto;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.domain.entity.MemberFixture;
import com.example.mimir.member.repository.MemberRepository;

@Transactional
@SpringBootTest
public class ArticleServiceTest {
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	private ArticleService sut;

	@Test
	@DisplayName("게시글 생성 및 저장 성공")
	void createArticle() {
		// given
		String title = "title";
		String content = "content";
		boolean isPrivate = false;

		CreateArticleDto createArticleDto = new CreateArticleDto(title, content, isPrivate);
		Member writer = MemberFixture.noId();
		memberRepository.save(writer);

		// when
		long articleId = sut.createArticle(createArticleDto, writer.getId());

		// then
		assertThat(articleId).isGreaterThanOrEqualTo(1L);
	}
}
