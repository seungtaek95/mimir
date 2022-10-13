package com.example.mimir.article.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import com.example.mimir.member.domain.entity.Member;

public class Article {
	@Id
	private Long id;

	/** 작성자 id */
	private UUID memberId;

	/** 제목 */
	private String title;

	/** 내용 */
	private String content;

	/** 조회수 */
	@Column("is_private")
	private boolean isPrivate;

	/** 조회수 */
	@Column("view_count")
	private long viewCount;

	/** 생성 년월일시 */
	@CreatedDate
	@Column("created_at")
	private LocalDateTime createdAt;

	/** 수정 년월일시 */
	@LastModifiedDate
	@Column("updated_at")
	private LocalDateTime updatedAt;

	/** 삭제 년월일시 */
	@Column("deleted_at")
	private LocalDateTime deletedAt;

	public Article(Member member, String title, String content, boolean isPrivate) {
		this.memberId = Objects.requireNonNull(member).getId();
		this.title = title;
		this.content = content;
		this.isPrivate = isPrivate;
		this.viewCount = 0;
		this.createdAt = LocalDateTime.now();
	}
}
