package com.example.mimir.article.domain.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.domain.entity.MemberFixture;

public class ArticleTest {

	@Test
	@DisplayName("게시글 생성 성공")
	void newArticle() {
		// given
		Member member = MemberFixture.create();
		String title = "title";
		String content = "content";
		boolean isPrivate = false;

		// when
		Article article = new Article(member, title, content, isPrivate);

		// then
		assertThat(article.getMemberId()).isEqualTo(member.getId());
		assertThat(article.getTitle()).isEqualTo(title);
		assertThat(article.getContent()).isEqualTo(content);
		assertThat(article.isPrivate()).isEqualTo(isPrivate);
		assertThat(article.getViewCount()).isEqualTo(0L);
		assertThat(article.getCreatedAt()).isNotNull();
		assertThat(article.getUpdatedAt()).isNotNull();
	}
}
