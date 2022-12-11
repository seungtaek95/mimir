package com.example.mimir.member.fixture;

import com.example.mimir.member.domain.entity.Member;

public class MemberFixture {
	public static final String email = "test@mimir.com";
	public static final String password = "test";
	public static final String nickname = "test";

	public static Member create() {
		return Member.signup(email, password, nickname);
	}
}
