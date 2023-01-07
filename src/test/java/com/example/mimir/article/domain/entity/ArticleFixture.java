package com.example.mimir.article.domain.entity;

import com.example.mimir.member.domain.entity.Member;

public class ArticleFixture {
    public static Article noId(Member writer) {
        return new Article(writer, "title", "content", false);
    }
}
