package com.example.mimir.member.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import com.example.mimir.member.domain.entity.Member;

public interface MemberRepository extends CrudRepository<Member, UUID> {
	Member findByEmail(String email);
}
