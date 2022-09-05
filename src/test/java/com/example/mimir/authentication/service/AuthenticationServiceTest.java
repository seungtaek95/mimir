package com.example.mimir.authentication.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.mimir.authentication.domain.exception.SignupException;
import com.example.mimir.authentication.domain.exception.SignupExceptionEnum;
import com.example.mimir.authentication.service.dto.SignupDto;
import com.example.mimir.user.domain.entity.User;
import com.example.mimir.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
	@Mock
	UserRepository userRepository;

	@InjectMocks
	AuthenticationService sut = new AuthenticationServiceImpl();

	@Test
	@DisplayName("회원가입으로 유저 생성")
	void signup() {
		// given
		SignupDto signupDto = new SignupDto("seungtaek@mimir.com", "seungtaek", "seungtaek");
		given(userRepository.findByEmail(signupDto.email())).willReturn(null); // email로 조회된 사용자가 없음
		given(userRepository.save(any())).willReturn(null); // 사용자 저장 성공

		// when
		User user = sut.signup(signupDto);

		// then
		assertThat(user.getEmail()).isEqualTo(signupDto.email());
		assertThat(user.getNickname()).isEqualTo(signupDto.nickname());
		assertThat(user.getRegisteredAt()).isNotNull(); // registeredAt 이 설정됨
		assertThat(user.getUpdatedAt()).isNotNull(); // updatedAt 이 설정됨
	}

	@Test
	@DisplayName("사용중인 이메일인 경우 실패")
	void signupFailed() {
		// given
		SignupDto signupDto = new SignupDto("seungtaek@mimir.com", "seungtaek", "seungtaek");
		given(userRepository.findByEmail(signupDto.email())).willReturn(mock(User.class)); // email로 조회된 사용자가 있음

		// when, then
		assertThatThrownBy(() -> sut.signup(signupDto))
			.isInstanceOf(SignupException.class)
			.hasMessage(SignupExceptionEnum.DUPLICATE_EMAIL.getMessage());
	}
}
