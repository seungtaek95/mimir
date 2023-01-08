package com.example.mimir.article.controller;

import javax.validation.Valid;

import com.example.mimir.article.controller.adapter.ArticleResponseAdapter;
import com.example.mimir.article.controller.response.ArticleDetailResponse;
import com.example.mimir.article.controller.response.ArticleListResponse;
import com.example.mimir.article.controller.response.CreateArticleResponse;
import com.example.mimir.article.domain.service.ArticleService;
import com.example.mimir.article.domain.service.dto.CreateArticleDto;
import com.example.mimir.authentication.domain.entity.LoginMember;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/articles")
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;

	private final ArticleResponseAdapter articleResponseAdapter;

	@RequestMapping(method = RequestMethod.POST)
	public CreateArticleResponse createArticle(
			@RequestBody @Valid CreateArticleDto createArticleDto,
			LoginMember loginMember) {
		long articleId = articleService.createArticle(createArticleDto, loginMember.id());

		return new CreateArticleResponse(articleId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ArticleListResponse getArticleList() {
		return articleResponseAdapter.getArticleListResponse();
	}

	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
	public ArticleDetailResponse getArticleDetail(@PathVariable long articleId) {
		// 게시글 조회수 증가
		articleService.increaseViewCount(articleId);

		return articleResponseAdapter.getArticleDetailResponse(articleId);
	}
}
