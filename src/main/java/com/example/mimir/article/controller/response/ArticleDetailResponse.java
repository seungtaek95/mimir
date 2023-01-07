package com.example.mimir.article.controller.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.mimir.article.domain.service.dto.view.ArticleDetailView;
import com.example.mimir.comment.domain.service.dto.CommentView;
import com.example.mimir.common.util.UuidUtils;

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
	List<ArticleDetailResponse.Comment> comments,

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
			articleDetailView.id(),
			UuidUtils.bytesToUuid(articleDetailView.writerId()),
			articleDetailView.writerNickname(),
			articleDetailView.title(),
			articleDetailView.content(),
			articleDetailView.viewCount(),
			commentViews.stream().map(Comment::from).toList(),
			articleDetailView.createdAt(),
			articleDetailView.updatedAt());
	}

	protected record Comment(
		long id,
		UUID writerId,
		String writerNickname,
		String content,
		LocalDateTime createdAt
	) {
		public static Comment from(CommentView commentView) {
			return new Comment(
				commentView.id(),
				UuidUtils.bytesToUuid(commentView.writerId()),
				commentView.writerNickname(),
				commentView.content(),
				commentView.createdAt());
		}
	}
}
