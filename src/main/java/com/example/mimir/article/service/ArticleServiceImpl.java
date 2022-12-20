package com.example.mimir.article.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.article.repository.ArticleRepository;
import com.example.mimir.article.service.dto.CreateArticleDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;

	@Override
	public Article createArticle(CreateArticleDto createArticleDto, UUID writerMemberId) {
		Article article = createArticleDto.toArticle(writerMemberId);

		articleRepository.save(article);

		return article;
	}
}
