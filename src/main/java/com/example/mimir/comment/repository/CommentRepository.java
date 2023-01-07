package com.example.mimir.comment.repository;

import com.example.mimir.comment.domain.entity.Comment;
import com.example.mimir.comment.domain.service.dto.CommentView;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query(value =
    """
    SELECT
        m.id AS writer_id, m.nickname AS writer_nickname,
        c.article_id AS article_id,
        c.id AS id, c.content AS content, c.created_at AS created_at, c.updated_at AS updated_at
    FROM
        comment c
    LEFT JOIN
        member m ON c.member_id = m.id
    WHERE
        c.article_id = :articleId
    """)
    List<CommentView> getViewsByArticleId(long articleId);
}
