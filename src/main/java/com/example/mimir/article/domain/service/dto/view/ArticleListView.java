package com.example.mimir.article.domain.service.dto.view;

import java.time.LocalDateTime;

public record ArticleListView(
	/*
	 * 게시글 ID
	 */
	long id,

	/*
	 * 작성자 ID
	 */
	byte[] writerId,

	/*
	 * 작성자 닉네임
	 */
	String writerNickname,

	/*
	 * 제목
	 */
	String title,

	/*
	 * 조회수
	 */
	long viewCount,

	/*
	 * 댓글 수
	 */
	int commentCount,

	/*
	 * 생성날짜
	 */
	LocalDateTime createdAt
) {
}
