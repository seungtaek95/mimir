package com.example.mimir.article.service;

import org.springframework.stereotype.Service;
import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.article.repository.ArticleRepository;
import com.example.mimir.article.service.dto.CreateArticleDto;
import com.example.mimir.member.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;

	@Override
	public Article createArticle(CreateArticleDto createArticleDto, Member writer) {
		Article article = createArticleDto.toArticle(writer);

		articleRepository.save(article);

		return article;
	}
}
