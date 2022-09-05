package com.example.mimir.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mimir.authentication.domain.exception.SignupException;
import com.example.mimir.authentication.domain.exception.SignupExceptionEnum;
import com.example.mimir.user.domain.entity.User;
import com.example.mimir.user.repository.UserRepository;
import com.example.mimir.authentication.service.dto.SignupDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	private UserRepository userRepository;

	@Override
	public User signup(SignupDto signupDto) {
		// email이 사용중인지 확인
		if (this.isEmailUsed(signupDto.email())) {
			throw new SignupException(SignupExceptionEnum.DUPLICATE_EMAIL);
		}

		// 사용자 생성 후 저장
		User user = User.signup(
			signupDto.email(),
			signupDto.password(),
			signupDto.nickname());

		userRepository.save(user);

		return user;
	}

	private boolean isEmailUsed(String email) {
		User user = userRepository.findByEmail(email);
		return user != null;
	}
}
