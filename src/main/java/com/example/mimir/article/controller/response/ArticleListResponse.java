package com.example.mimir.article.controller.response;

import java.util.List;

import com.example.mimir.article.domain.service.dto.view.ArticleListView;
import com.example.mimir.common.util.UuidUtils;

public record ArticleListResponse(
	List<ArticleListItem> articles
) {
	public static ArticleListResponse from(List<ArticleListView> articleListViews) {
		List<ArticleListItem> articleListItems = articleListViews.stream().map(articleListView -> new ArticleListItem(
			articleListView.id(),
			UuidUtils.bytesToUuid(articleListView.writerId()),
			articleListView.writerNickname(),
			articleListView.title(),
			articleListView.viewCount(),
			articleListView.commentCount(),
			articleListView.createdAt())).toList();

		return new ArticleListResponse(articleListItems);
	}
}
