package com.example.mimir.article.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.mimir.article.domain.service.dto.view.ArticleDetailView;
import com.example.mimir.article.domain.service.dto.view.ArticleListView;
import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.common.util.UuidUtils;
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
		Article article = new Article(member, title, content, isPrivate);

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
		Article article = new Article(member, title, content, isPrivate);
		sut.save(article);

		// when
		List<ArticleListView> articleListViews = sut.getListViews();

		// then
		articleListViews.forEach(articleListView -> {
			assertThat(UuidUtils.bytesToUuid(articleListView.writerId())).isNotNull();
			assertThat(articleListView.writerNickname()).isNotNull();
			assertThat(articleListView.title()).isNotNull();
			assertThat(articleListView.createdAt()).isNotNull();
		});
	}

	@Test
	@DisplayName("게시글 조회수 증가")
	void increaseViewCount() {
		// given
		Member member = MemberFixture.noId();
		memberRepository.save(member);

		String title = "title";
		String content = "content";
		boolean isPrivate = false;
		Article target = new Article(member, title, content, isPrivate);
		sut.save(target);

		// when
		sut.increaseViewCountById(target.getId());

		// then
		Article result = sut.findById(target.getId()).orElseThrow();
		assertThat(result.getViewCount()).isEqualTo(target.getViewCount() + 1);
	}

	@Test
	@DisplayName("게시글 상세 뷰 조회")
	void getArticleDetailView() {
		// given
		Member member = MemberFixture.noId();
		memberRepository.save(member);

		String title = "title";
		String content = "content";
		boolean isPrivate = false;
		Article article = new Article(member, title, content, isPrivate);
		sut.save(article);

		// when
		ArticleDetailView articleDetailView = sut.getDetailViewById(article.getId());

		// then
		assertThat(UuidUtils.bytesToUuid(articleDetailView.writerId())).isNotNull();
		assertThat(articleDetailView.writerNickname()).isNotNull();
		assertThat(articleDetailView.title()).isNotNull();
		assertThat(articleDetailView.content()).isNotNull();
		assertThat(articleDetailView.createdAt()).isNotNull();
	}
}
