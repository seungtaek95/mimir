package com.example.mimir.article.controller.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.mimir.common.util.UuidUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record ArticleListView(
	@JsonIgnore
	byte[] _writerId,
	String writerNickname,
	String title,
	String content,
	long viewCount,
	LocalDateTime createdAt
) {
	public UUID writerId() {
		return UuidUtils.bytesToUuid(_writerId);
	}
}
