package com.example.mimir.member.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.mimir.member.domain.entity.Member;

public interface MemberRepository extends CrudRepository<Member, byte[]> {
	Member findByEmail(String email);
}
