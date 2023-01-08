package com.example.mimir.article.controller.adapter;

import com.example.mimir.article.controller.response.ArticleDetailResponse;
import com.example.mimir.article.controller.response.ArticleListResponse;
import com.example.mimir.article.repository.ArticleRepository;
import com.example.mimir.comment.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleResponseAdapter {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ArticleListResponse getArticleListResponse() {
        return ArticleListResponse.from(articleRepository.getListViews());
    }

    public ArticleDetailResponse getArticleDetailResponse(long articleId) {
        return ArticleDetailResponse.from(
            articleRepository.getDetailViewById(articleId),
            commentRepository.getViewsByArticleId(articleId));
    }
}
