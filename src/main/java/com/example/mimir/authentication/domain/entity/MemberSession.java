package com.example.mimir.authentication.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import org.springframework.stereotype.Component;

import com.example.mimir.common.util.UuidUtils;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class MemberSession {
	public static String COOKIE_NAME = "SESSION_ID";

	@Id
	private byte[] id;

	@Column("member_id")
	private byte[] memberId;

	@Transient
	@Getter(value = AccessLevel.NONE)
	private UUID _memberId;

	/** 생성 년월일시 */
	@CreatedDate
	@Column("created_at")
	private final LocalDateTime createdAt;

	public MemberSession(UUID memberId) {
		this.memberId = UuidUtils.uuidToBytes(memberId);
		this.createdAt = LocalDateTime.now();
	}

	@PersistenceCreator
	private MemberSession(byte[] id, byte[] memberId, LocalDateTime createdAt) {
		this.id = id;
		this.memberId = memberId;
		this.createdAt = createdAt;
	}

	@Component
	static class MemberBeforeSaveCallback implements BeforeSaveCallback<MemberSession> {
		@Override
		public MemberSession onBeforeSave(MemberSession aggregate, MutableAggregateChange<MemberSession> aggregateChange) {
			aggregate.id = UuidUtils.concatToBytes(UUID.randomUUID(), aggregate.getMemberId());

			return aggregate;
		}
	}

	public UUID getMemberId() {
		if (_memberId == null) {
			_memberId = UuidUtils.bytesToUuid(memberId);
		}

		return _memberId;
	}
}
