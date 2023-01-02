package com.example.mimir.article.service.adapter;

import java.util.List;

import org.springframework.stereotype.Component;
import com.example.mimir.article.service.dto.ArticleListView;
import com.example.mimir.article.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArticleViewAdapterImpl implements ArticleViewAdapter{
	private final ArticleRepository articleRepository;

	@Override
	public List<ArticleListView> getArticleListView() {
		return articleRepository.getArticleListViews();
	}
}
