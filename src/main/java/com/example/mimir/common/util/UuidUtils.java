package com.example.mimir.common.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidUtils {
	private static final int UUID_BYTES = 16;

	public static byte[] uuidToBytes(UUID uuid) {
		return ByteBuffer.wrap(new byte[UUID_BYTES])
			.putLong(uuid.getMostSignificantBits())
			.putLong(uuid.getLeastSignificantBits())
			.array();
	}

	public static UUID bytesToUuid(byte[] uuidBytes) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(uuidBytes);
		return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
	}

	public static byte[] concatToBytes(UUID uuid1, UUID uuid2) {
		return ByteBuffer.allocate(UUID_BYTES * 2)
			.put(uuidToBytes(uuid1))
			.put(uuidToBytes(uuid2))
			.array();
	}

	public static UUID[] splitTwoUuid(byte[] uuidBytes) {
		byte[] uuid1 = new byte[UUID_BYTES];
		byte[] uuid2 = new byte[UUID_BYTES];

		ByteBuffer.wrap(uuidBytes)
			.get(uuid1, 0, UUID_BYTES)
			.get(uuid2, 0, UUID_BYTES);

		return new UUID[]{bytesToUuid(uuid1), bytesToUuid(uuid2)};
	}
}
