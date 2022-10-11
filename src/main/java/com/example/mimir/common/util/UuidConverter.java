package com.example.mimir.common.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidConverter {
	public static byte[] uuidToBytes(UUID uuid) {
		byte[] uuidBytes = new byte[16];

		ByteBuffer.wrap(uuidBytes)
			.putLong(uuid.getMostSignificantBits())
			.putLong(uuid.getLeastSignificantBits());

		return uuidBytes;
	}
}
