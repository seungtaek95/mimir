package com.example.mimir.article.service;

import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.article.service.dto.CreateArticleDto;
import com.example.mimir.member.domain.entity.Member;

public interface ArticleService {
	Article createArticle(CreateArticleDto createArticleDto, Member writer);
}
