package com.example.mimir.article.controller.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.mimir.article.service.dto.ArticleDetailView;
import com.example.mimir.comment.service.dto.CommentView;

public record ArticleDetailResponse(
	/*
	 * 게시글 ID
	 */
	long articleId,

	/*
	 * 작성자 ID
	 */
	UUID writerId,

	/*
	 * 작성자 닉네임
	 */
	String writerNickname,

	/*
	 * 제목
	 */
	String title,

	/*
	 * 내용
	 */
	String content,

	/*
	 * 조회수
	 */
	long viewCount,

	/*
	 * 댓글 리스트
	 */
	List<CommentView> comments,

	/*
	 * 생성 년월일시
	 */
	LocalDateTime createdAt,

	/*
	 * 수정 년월일시
	 */
	LocalDateTime updatedAt
) {
	public static ArticleDetailResponse from(ArticleDetailView articleDetailView, List<CommentView> commentViews) {
		return new ArticleDetailResponse(
			articleDetailView.articleId(),
			articleDetailView.writerId(),
			articleDetailView.writerNickname(),
			articleDetailView.title(),
			articleDetailView.content(),
			articleDetailView.viewCount(),
			commentViews,
			articleDetailView.createdAt(),
			articleDetailView.updatedAt());
	}
}
