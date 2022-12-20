package com.example.mimir.authentication.domain.entity;

import java.util.UUID;

public record LoginMember(
	UUID id,
	String email,
	String nickName
){
}
