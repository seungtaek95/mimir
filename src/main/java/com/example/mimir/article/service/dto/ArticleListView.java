package com.example.mimir.article.service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.mimir.common.util.UuidUtils;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record ArticleListView(
	/*
	 * 게시글 ID
	 */
	long articleId,

	/*
	 * 작성자 ID
	 */
	@JsonIgnore
	byte[] _writerId,

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
	@JsonGetter
	public UUID writerId() {
		return UuidUtils.bytesToUuid(_writerId);
	}
}
