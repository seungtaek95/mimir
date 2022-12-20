package com.example.mimir.article.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.mimir.article.controller.response.ArticleListView;
import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.domain.entity.MemberFixture;
import com.example.mimir.member.repository.MemberRepository;

@Transactional
@SpringBootTest
public class ArticleRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ArticleRepository sut;

	@Test
	@DisplayName("게시글 저장 성공")
	void save() {
		// given
		Member member = Member.signup("test@test.com", "test", "test");
		memberRepository.save(member);

		String title = "title";
		String content = "content";
		boolean isPrivate = false;
		Article article = new Article(member.getId(), title, content, isPrivate);

		// when
		Article result = sut.save(article);

		// then
		assertThat(result.getId()).isNotNull();
	}

	@Test
	@DisplayName("게시글 리스트 뷰 조회")
	void getArticleListView() {
		// given
		Member member = MemberFixture.noId();
		memberRepository.save(member);

		String title = "title";
		String content = "content";
		boolean isPrivate = false;
		Article article = new Article(member.getId(), title, content, isPrivate);
		sut.save(article);

		// when
		List<ArticleListView> articleListViews = sut.getArticleListViews();

		// then
		ArticleListView articleListView = articleListViews.get(0);
		assertThat(articleListView.writerId()).isEqualTo(member.getId());
		assertThat(articleListView.writerNickname()).isEqualTo(member.getNickname());
		assertThat(articleListView.title()).isEqualTo(article.getTitle());
		assertThat(articleListView.content()).isEqualTo(article.getContent());
		assertThat(articleListView.viewCount()).isEqualTo(article.getViewCount());
		assertThat(articleListView.createdAt().truncatedTo(ChronoUnit.MINUTES)).isEqualTo(article.getCreatedAt().truncatedTo(ChronoUnit.MINUTES));
	}
}
