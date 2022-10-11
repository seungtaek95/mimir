package com.example.mimir.member.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import org.springframework.stereotype.Component;

import com.example.mimir.common.util.PasswordEncoderUtil;
import com.example.mimir.common.util.UuidConverter;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class Member {
	@Id
	private byte[] id;

	@Transient
	@Getter(value = AccessLevel.NONE)
	private UUID _id;

	private String email;

	@Getter(value = AccessLevel.NONE)
	private String password;

	private String nickname;

	@CreatedDate
	@Column("registered_at")
	private LocalDateTime registeredAt;

	@LastModifiedDate
	@Column("updated_at")
	private LocalDateTime updatedAt;

	@Column("disabled_at")
	private LocalDateTime disabledAt;

	protected Member() {}

	@Component
	static class MemberBeforeSaveCallback implements BeforeSaveCallback<Member> {
		@Override
		public Member onBeforeSave(Member aggregate, MutableAggregateChange<Member> aggregateChange) {
			UUID uuid = UUID.randomUUID();

			aggregate.id = UuidConverter.uuidToBytes(uuid);

			return aggregate;
		}
	}

	public static Member signup(String email, String password, String nickname) {
		Member member = new Member();
		member.email = email;
		member.password = PasswordEncoderUtil.encode(password);
		member.nickname = nickname;
		member.registeredAt = LocalDateTime.now();
		member.updatedAt = LocalDateTime.now();

		return member;
	}

	public boolean isPasswordMatch(String password) {
		return PasswordEncoderUtil.matches(password, this.password);
	}

	public UUID getId() {
		if (_id == null) {
			_id = UUID.nameUUIDFromBytes(id);
		}

		return _id;
	}
}
