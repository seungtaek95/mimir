package com.example.mimir.article.service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.mimir.common.util.UuidUtils;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record ArticleDetailView(
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
	 * 내용
	 */
	String content,

	/*
	 * 조회수
	 */
	long viewCount,

	/*
	 * 생성 년월일시
	 */
	LocalDateTime createdAt,

	/*
	 * 수정 년월일시
	 */
	LocalDateTime updatedAt
) {
	@JsonGetter
	public UUID writerId() {
		return UuidUtils.bytesToUuid(_writerId);
	}
}
