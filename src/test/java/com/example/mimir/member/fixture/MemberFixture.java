package com.example.mimir.member.fixture;

import static org.mockito.BDDMockito.*;

import java.util.UUID;

import com.example.mimir.member.domain.entity.Member;

public class MemberFixture {
	private static final UUID id = UUID.randomUUID();
	private static final String email = "test@mimir.com";

	public static Member create() {
		Member member = mock(Member.class);
		lenient().when(member.getId()).thenReturn(id);
		lenient().when(member.getEmail()).thenReturn(email);

		return member;
	}
}
