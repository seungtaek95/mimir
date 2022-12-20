package com.example.mimir.member.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import org.springframework.stereotype.Component;

import com.example.mimir.common.util.PasswordEncoderUtils;
import com.example.mimir.common.util.UuidUtils;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class Member {
	@Id
	private byte[] id;

	@Transient
	@Getter(value = AccessLevel.NONE)
	private UUID _id;

	/** 이메일 */
	private String email;

	/** 비밀번호 */
	@Getter(value = AccessLevel.NONE)
	private String password;

	/** 닉네임 */
	private String nickname;

	/** 가입 년월일시 */
	@CreatedDate
	@Column("registered_at")
	private LocalDateTime registeredAt;

	/** 수정 년월일시 */
	@LastModifiedDate
	@Column("updated_at")
	private LocalDateTime updatedAt;

	/** 탈퇴 년월일시 */
	@Column("disabled_at")
	private LocalDateTime disabledAt;

	@Component
	static class MemberBeforeSaveCallback implements BeforeSaveCallback<Member> {
		@Override
		public Member onBeforeSave(Member aggregate, MutableAggregateChange<Member> aggregateChange) {
			aggregate.id = UuidUtils.uuidToBytes(UUID.randomUUID());

			return aggregate;
		}
	}

	@PersistenceCreator
	public Member(byte[] id, String email, String password, String nickname, LocalDateTime registeredAt, LocalDateTime updatedAt, LocalDateTime disabledAt) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.registeredAt = registeredAt;
		this.updatedAt = updatedAt;
		this.disabledAt = disabledAt;
	}

	/**
	 * 회원가입으로 사용자 생성
	 */
	public static Member signup(String email, String password, String nickname) {
		Member member = new Member(
			null,
			email,
			PasswordEncoderUtils.encode(password),
			nickname,
			LocalDateTime.now(),
			LocalDateTime.now(),
			null);

		return member;
	}

	/**
	 * 비밀번호 일치 여부 확인
	 */
	public boolean isPasswordMatch(String password) {
		return PasswordEncoderUtils.matches(password, this.password);
	}

	public UUID getId() {
		if (_id == null) {
			_id = UuidUtils.bytesToUuid(id);
		}

		return _id;
	}
}
