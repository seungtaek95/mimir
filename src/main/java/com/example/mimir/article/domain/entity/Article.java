package com.example.mimir.article.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.example.mimir.common.util.UuidUtils;
import com.example.mimir.member.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = {@PersistenceCreator})
public class Article {
	@Id
	private Long id;

	/** 작성자 id */
	@Column("member_id")
	@Getter(value = AccessLevel.NONE)
	private final byte[] writerMemberId;

	/** 제목 */
	private String title;

	/** 내용 */
	private String content;

	/** 비공개여부 */
	@Column("is_private")
	private boolean isPrivate;

	/** 조회수 */
	@Column("view_count")
	private long viewCount;

	/** 생성 년월일시 */
	@CreatedDate
	@Column("created_at")
	private final LocalDateTime createdAt;

	/** 수정 년월일시 */
	@LastModifiedDate
	@Column("updated_at")
	private LocalDateTime updatedAt;

	/** 삭제 년월일시 */
	@Column("deleted_at")
	private LocalDateTime deletedAt;

	public Article(Member writer, String title, String content, boolean isPrivate) {
		this.writerMemberId = UuidUtils.uuidToBytes(writer.getId());
		this.title = Objects.requireNonNull(title);
		this.content = Objects.requireNonNull(content);
		this.isPrivate = isPrivate;
		this.viewCount = 0;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public UUID getWriterMemberId() {
		return UuidUtils.bytesToUuid(writerMemberId);
	}

	public void increaseViewCount() {
		viewCount += 1;
	}
}
