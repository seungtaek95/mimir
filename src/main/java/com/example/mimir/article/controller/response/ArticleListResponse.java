package com.example.mimir.article.controller.response;

import java.util.List;

import com.example.mimir.article.service.dto.ArticleListView;

public record ArticleListResponse(
	List<ArticleListView> articles
) {
}
