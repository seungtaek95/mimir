package com.example.mimir.article.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.mimir.article.domain.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
