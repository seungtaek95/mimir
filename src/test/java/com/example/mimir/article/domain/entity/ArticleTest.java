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
		Member writer = MemberFixture.create();
		String title = "title";
		String content = "content";
		boolean isPrivate = false;

		// when
		Article article = new Article(writer, title, content, isPrivate);

		// then
		assertThat(article.getWriterMemberId()).isEqualTo(writer.getId());
		assertThat(article.getTitle()).isEqualTo(title);
		assertThat(article.getContent()).isEqualTo(content);
		assertThat(article.isPrivate()).isEqualTo(isPrivate);
		assertThat(article.getViewCount()).isEqualTo(0L);
		assertThat(article.getCreatedAt()).isNotNull();
		assertThat(article.getUpdatedAt()).isNotNull();
		assertThat(article.getDeletedAt()).isNull();
	}

	@Test
	@DisplayName("게시글 생성 실패, content 없음")
	void newArticleNoTitle() {
		// given
		Member writer = MemberFixture.create();
		String title = null;
		String content = "content";
		boolean isPrivate = false;

		// when, then
		assertThatThrownBy(() -> new Article(writer, title, content, isPrivate))
			.isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("게시글 생성 실패, content 없음")
	void newArticleNoContent() {
		// given
		Member writer = MemberFixture.create();
		String title = "title";
		String content = null;
		boolean isPrivate = false;

		// when, then
		assertThatThrownBy(() -> new Article(writer, title, content, isPrivate))
			.isInstanceOf(NullPointerException.class);
	}
}
