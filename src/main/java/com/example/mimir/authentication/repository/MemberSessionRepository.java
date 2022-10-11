package com.example.mimir.authentication.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.mimir.authentication.domain.entity.MemberSession;

public interface MemberSessionRepository extends CrudRepository<MemberSession, String> {
}
