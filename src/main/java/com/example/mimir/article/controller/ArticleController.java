package com.example.mimir.article.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mimir.article.controller.response.CreateArticleResponse;
import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.article.service.ArticleService;
import com.example.mimir.article.service.dto.CreateArticleDto;
import com.example.mimir.authentication.domain.entity.LoginMember;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/articles")
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;

	@RequestMapping(method = RequestMethod.POST)
	public CreateArticleResponse createArticle(
			@RequestBody @Valid CreateArticleDto createArticleDto,
			LoginMember loginMember) {
		Article article = articleService.createArticle(createArticleDto, loginMember.id());

		return new CreateArticleResponse(article.getId());
	}
}
