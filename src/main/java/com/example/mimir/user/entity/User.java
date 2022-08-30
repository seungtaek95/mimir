package com.example.mimir.user.entity;

import java.nio.ByteBuffer;
import java.util.Date;
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

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class User {
	@Id
	private byte[] id;

	@Transient
	private UUID _id;

	private String email;

	@Getter(value = AccessLevel.NONE)
	private String password;

	private String nickname;

	@CreatedDate
	@Column("registered_at")
	private Date registeredAt;

	@LastModifiedDate
	@Column("updated_at")
	private Date updatedAt;

	@Column("disabled_at")
	private Date disabledAt;

	protected User() {}

	@Component
	static class UserBeforeSaveCallback implements BeforeSaveCallback<User> {
		@Override
		public User onBeforeSave(User aggregate, MutableAggregateChange<User> aggregateChange) {
			UUID uuid = UUID.randomUUID();
			byte[] uuidBytes = new byte[16];

			ByteBuffer.wrap(uuidBytes)
				.putLong(uuid.getMostSignificantBits())
				.putLong(uuid.getLeastSignificantBits());

			aggregate.id = uuidBytes;

			return aggregate;
		}
	}

	public static User join(String email, String password, String nickname) {
		User user = new User();
		user.email = email;
		user.password = PasswordEncoderUtil.encode(password);
		user.nickname = nickname;
		user.registeredAt = new Date();
		user.updatedAt = new Date();

		return user;
	}

	public UUID getId() {
		if (_id == null) {
			_id = UUID.nameUUIDFromBytes(id);
		}

		return _id;
	}
}
