package com.example.mimir.authentication.domain.entity;

import java.util.UUID;

import com.example.mimir.member.domain.entity.Member;

public record LoginMember(
	UUID id,
	String email,
	String nickName
){
	public static LoginMember from(Member member) {
		return new LoginMember(
			member.getId(),
			member.getEmail(),
			member.getNickname());
	}
}
