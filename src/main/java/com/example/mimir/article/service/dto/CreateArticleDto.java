package com.example.mimir.article.service.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;

import com.example.mimir.article.domain.entity.Article;

public record CreateArticleDto(
	@NotNull
	String title,

	@NotNull
	String content,

	@NotNull
	boolean isPrivate
) {
	public Article toArticle(UUID writerMemberId) {
		return new Article(writerMemberId, title, content, isPrivate);
	}
}
