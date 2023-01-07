package com.example.mimir.article.repository;

import java.util.List;

import com.example.mimir.article.domain.service.dto.view.ArticleDetailView;
import com.example.mimir.article.domain.service.dto.view.ArticleListView;
import com.example.mimir.article.domain.entity.Article;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	@Modifying
	@Query(value =
	"""
	UPDATE article a
	SET a.view_count = a.view_count + 1
	WHERE a.id = :id
	""")
	void increaseViewCountById(long id);

	@Query(value =
 	"""
	SELECT
		m.id AS writer_id, m.nickname AS writer_nickname,
		a.id AS id, a.title, a.view_count, a.created_at,
		(SELECT count(*) FROM comment c WHERE c.article_id = a.id) AS comment_count
	FROM
		article a
	LEFT JOIN
		member m ON m.id = a.member_id
	""")
	List<ArticleListView> getListViews();

	@Query(value =
	"""
	SELECT
		m.id AS writer_id, m.nickname AS writer_nickname,
		a.id AS id, a.title, a.content AS content, a.view_count, a.created_at, a.updated_at
	FROM
		article a
	LEFT JOIN
		member m ON m.id = a.member_id
	WHERE
		a.id = :articleId
	""")
	ArticleDetailView getDetailView(long articleId);
}
