package com.example.mimir.article.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.mimir.article.service.dto.ArticleListView;
import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.article.service.dto.ArticleDetailView;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	@Query(value =
 	"""
		SELECT
			m.id AS _writer_id, m.nickname AS writer_nickname,
			a.id AS article_id, a.title, a.view_count, a.created_at,
			(SELECT count(*) FROM comment c WHERE c.article_id = a.id) AS comment_count
		FROM article a
		LEFT JOIN member m ON a.member_id = m.id
	""")
	List<ArticleListView> getArticleListViews();

	@Query(value =
	"""
		SELECT
			m.id AS _writer_id, m.nickname AS writer_nickname,
			a.id AS article_id, a.title, a.view_count, a.created_at, a.updated_at,
		FROM article a
		LEFT JOIN member m ON a.member_id = m.id
		WHERE a.id = :articleId
	""")
	ArticleDetailView getArticleView(long articleId);
}
