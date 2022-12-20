package com.example.mimir.article.service;

import java.util.UUID;

import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.article.service.dto.CreateArticleDto;

public interface ArticleService {
	Article createArticle(CreateArticleDto createArticleDto, UUID writerMemberId);
}
