package com.example.mimir.authentication.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
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
	@Id
	private byte[] id;

	@Transient
	@Getter(value = AccessLevel.NONE)
	private String _id;

	@Transient
	@Getter(value = AccessLevel.NONE)
	private UUID _memberId;

	/** 생성 년월일시 */
	@CreatedDate
	@Column("created_at")
	private final LocalDateTime createdAt;

	public MemberSession(UUID memberId) {
		this._memberId = memberId;
		this.createdAt = LocalDateTime.now();
	}

	@Component
	static class MemberBeforeSaveCallback implements BeforeSaveCallback<MemberSession> {
		@Override
		public MemberSession onBeforeSave(MemberSession aggregate, MutableAggregateChange<MemberSession> aggregateChange) {
			aggregate.id = UuidUtils.concatToBytes(UUID.randomUUID(), aggregate._memberId);

			return aggregate;
		}
	}

	public String getId() {
		if (_id == null) {
			UUID[] uuids = UuidUtils.splitTwoUuid(id);
			_id = uuids[0].toString() + uuids[1].toString();
		}

		return _id;
	}

	public UUID getMemberId() {
		if (_memberId == null) {
			_memberId = UuidUtils.splitTwoUuid(id)[1];
		}

		return _memberId;
	}
}
