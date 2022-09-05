package com.example.mimir.user.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import com.example.mimir.user.domain.entity.User;

public interface UserRepository extends CrudRepository<User, UUID> {
	User findByEmail(String email);
}
