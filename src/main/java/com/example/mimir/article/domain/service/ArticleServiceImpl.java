package com.example.mimir.article.domain.service;

import java.util.UUID;

import com.example.mimir.article.domain.entity.Article;
import com.example.mimir.article.domain.exception.ArticleNotFoundException;
import com.example.mimir.article.domain.service.dto.CreateArticleDto;
import com.example.mimir.article.repository.ArticleRepository;
import com.example.mimir.common.util.UuidUtils;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;
	private final MemberRepository memberRepository;

	@Override
	public long createArticle(CreateArticleDto createArticleDto, UUID writerMemberId) {
		Member writer = memberRepository.findById(UuidUtils.uuidToBytes(writerMemberId)).orElseThrow();
		Article article = createArticleDto.toArticle(writer);

		articleRepository.save(article);

		return article.getId();
	}

	@Override
	public void increaseViewCount(long articleId) {
		articleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);

		// 조회수 증가
		articleRepository.increaseViewCountById(articleId);
	}
}
