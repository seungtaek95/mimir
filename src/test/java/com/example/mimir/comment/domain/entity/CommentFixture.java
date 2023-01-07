package com.example.mimir.comment.domain.entity;

import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.member.domain.entity.Member;

public class CommentFixture {
    public static Comment noId(Member writer, Article article) {
        return new Comment(writer, article, "content");
    }
}
