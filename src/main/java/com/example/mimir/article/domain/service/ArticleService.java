package com.example.mimir.article.domain.service;

import java.util.UUID;

import com.example.mimir.article.domain.service.dto.CreateArticleDto;

public interface ArticleService {
	long createArticle(CreateArticleDto createArticleDto, UUID writerMemberId);
	void increaseViewCount(long articleId);
}
