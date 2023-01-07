package com.example.mimir.article.domain.service.dto;

import javax.validation.constraints.NotNull;

import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.member.domain.entity.Member;

public record CreateArticleDto(
	@NotNull
	String title,

	@NotNull
	String content,

	@NotNull
	boolean isPrivate
) {
	public Article toArticle(Member writer) {
		return new Article(writer, title, content, isPrivate);
	}
}
