package com.example.mimir.article.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.article.repository.ArticleRepository;
import com.example.mimir.article.service.dto.CreateArticleDto;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.domain.entity.MemberFixture;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
	@Mock
	private ArticleRepository articleRepository;

	@InjectMocks
	private ArticleServiceImpl sut;

	@Test
	@DisplayName("게시글 생성 및 저장 성공")
	void createArticle() {
		// given
		String title = "title";
		String content = "content";
		boolean isPrivate = false;

		CreateArticleDto createArticleDto = new CreateArticleDto(title, content, isPrivate);
		Member writer = MemberFixture.create();

		// when
		Article result = sut.createArticle(createArticleDto, writer.getId());

		// then
		assertThat(result.getWriterMemberId()).isEqualTo(writer.getId());
		assertThat(result.getTitle()).isEqualTo(title);
		assertThat(result.getContent()).isEqualTo(content);
		assertThat(result.isPrivate()).isEqualTo(isPrivate);
		assertThat(result.getViewCount()).isEqualTo(0L);
		assertThat(result.getCreatedAt()).isNotNull();
		assertThat(result.getUpdatedAt()).isNotNull();
		assertThat(result.getDeletedAt()).isNull();
	}
}
