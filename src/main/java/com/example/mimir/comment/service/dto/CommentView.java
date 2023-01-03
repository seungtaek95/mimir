package com.example.mimir.comment.service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.mimir.common.util.UuidUtils;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record CommentView(
	int id,
	@JsonIgnore
	byte[] _writerId,
	String writerNickname,
	String content,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
	@JsonGetter
	public UUID writerId() {
		return UuidUtils.bytesToUuid(_writerId);
	}
}
