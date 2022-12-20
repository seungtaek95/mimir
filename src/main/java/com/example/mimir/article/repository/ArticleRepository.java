package com.example.mimir.article.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.mimir.article.controller.response.ArticleListView;
import com.example.mimir.article.domain.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	@Query(value =
 	"""
 	SELECT
 		m.id AS _writer_member_id, m.nickname AS writer_nickname,
 		a.title, a.content, a.view_count, a.created_at
 	FROM article a
 	LEFT JOIN member m
 	ON a.member_id = m.id
	""")
	List<ArticleListView> getArticleListViews();
}
