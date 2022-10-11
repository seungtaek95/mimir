package com.example.mimir.common;

import static org.assertj.core.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import com.example.mimir.common.util.UuidUtils;

public class UuidUtilsTest {
	@Test
	void convert() {
		// given
		UUID uuid = UUID.randomUUID();

		// when
		byte[] uuidBytes = UuidUtils.uuidToBytes(uuid);

		// then
		assertThat(UuidUtils.bytesToUuid(uuidBytes)).isEqualTo(uuid);
	}

	@Test
	void concatAndSplit() {
		// given
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();

		// when
		byte[] uuidBytes = UuidUtils.concatToBytes(uuid1, uuid2);
		UUID[] uuids = UuidUtils.splitTwoUuid(uuidBytes);

		// then
		assertThat(uuids[0]).isEqualTo(uuid1);
		assertThat(uuids[1]).isEqualTo(uuid2);
	}
}
