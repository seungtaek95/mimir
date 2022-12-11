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

import com.example.mimir.common.util.PasswordEncoderUtils;
import com.example.mimir.common.util.UuidUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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

	/**
	 * 회원가입으로 사용자 생성
	 */
	public static Member signup(String email, String password, String nickname) {
		Member member = new Member();
		member.email = email;
		member.password = PasswordEncoderUtils.encode(password);
		member.nickname = nickname;
		member.registeredAt = LocalDateTime.now();

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
