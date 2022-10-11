package com.example.mimir.authentication.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;

import com.example.mimir.common.util.UuidConverter;

import lombok.Getter;

@Getter
public class MemberSession {
	@Id
	private final String id;

	private final byte[] memberId;

	@Transient
	private UUID _memberId;

	@CreatedDate
	@Column("created_at")
	private final LocalDateTime createdAt;

	public MemberSession(UUID memberId) {
		this.id = UUID.randomUUID() + memberId.toString();
		this.memberId = UuidConverter.uuidToBytes(memberId);
		this.createdAt = LocalDateTime.now();
	}

	public UUID getMemberId() {
		if (_memberId == null) {
			_memberId = UUID.nameUUIDFromBytes(memberId);
		}

		return _memberId;
	}
}
