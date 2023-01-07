package com.example.mimir.article.controller.response;

import java.time.LocalDateTime;
import java.util.UUID;

record ArticleListItem (
    long articleId,
    UUID writerId,
    String writerNickname,
    String title,
    long viewCount,
    int commentCount,
    LocalDateTime createdAt
) {
}
