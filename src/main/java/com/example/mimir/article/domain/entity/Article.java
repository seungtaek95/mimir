package com.example.mimir.article.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import com.example.mimir.common.util.UuidUtils;
import com.example.mimir.member.domain.entity.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
	@Id
	private Long id;

	/** 작성자 id */
	@Column("member_id")
	private final byte[] memberId;

	@Transient
	@Getter(value = AccessLevel.NONE)
	private UUID _memberId;

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

	public Article(Member member, String title, String content, boolean isPrivate) {
		this.memberId = UuidUtils.uuidToBytes(member.getId());
		this.title = title;
		this.content = content;
		this.isPrivate = isPrivate;
		this.viewCount = 0;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public UUID getMemberId() {
		if (_memberId == null) {
			_memberId = UuidUtils.bytesToUuid(memberId);
		}

		return _memberId;
	}
}