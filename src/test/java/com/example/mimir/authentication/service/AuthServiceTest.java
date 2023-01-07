package com.example.mimir.authentication.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.mimir.authentication.domain.entity.MemberSession;
import com.example.mimir.authentication.domain.exception.AuthException;
import com.example.mimir.authentication.domain.exception.AuthExceptionEnum;
import com.example.mimir.authentication.domain.service.AuthServiceImpl;
import com.example.mimir.authentication.repository.MemberSessionRepository;
import com.example.mimir.authentication.domain.service.dto.SigninDto;
import com.example.mimir.authentication.domain.service.dto.SignupDto;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
	@Mock
	MemberRepository memberRepository;

	@Mock
	MemberSessionRepository memberSessionRepository;

	@InjectMocks
    AuthServiceImpl sut;

	@Test
	@DisplayName("회원가입으로 유저 생성")
	void signup() {
		// given
		String email = "seungtaek@mimir.com";
		String password = "seungtaek";
		String nickname = "seungtaek";
		SignupDto signupDto = new SignupDto(email, password, nickname);
		given(memberRepository.findByEmail(signupDto.email())).willReturn(null); // 같은 email로 조회된 사용자가 없음

		// when
		Member member = sut.signup(signupDto);

		// then
		assertThat(member.getEmail()).isEqualTo(signupDto.email());
		assertThat(member.getNickname()).isEqualTo(signupDto.nickname());
		assertThat(member.getRegisteredAt()).isNotNull(); // registeredAt 이 설정됨
		assertThat(member.getUpdatedAt()).isNotNull();
		assertThat(member.getDisabledAt()).isNull();
	}

	@Test
	@DisplayName("회원가입 실패, 사용중인 이메일")
	void signupFailed() {
		// given
		String email = "seungtaek@mimir.com";
		String password = "seungtaek";
		String nickname = "seungtaek";
		SignupDto signupDto = new SignupDto(email, password, nickname);
		given(memberRepository.findByEmail(signupDto.email())).willReturn(mock(Member.class)); // 같은 email로 조회된 사용자가 있음

		// when, then
		assertThatThrownBy(() -> sut.signup(signupDto))
			.isInstanceOf(AuthException.class)
			.hasMessage(AuthExceptionEnum.DUPLICATE_EMAIL.getMessage());
	}

	@Test
	@DisplayName("로그인 후 세션 id 반환")
	void signin() {
		// given
		UUID memberId = UUID.randomUUID();
		String email = "seungtaek@mimir.com";
		String password = "seungtaek";
		Member member = mock(Member.class);
		SigninDto signinDto = new SigninDto(email, password);
		given(memberRepository.findByEmail(signinDto.email())).willReturn(member); // email로 조회
		given(member.getId()).willReturn(memberId);
		given(member.isPasswordMatch(signinDto.password())).willReturn(true); // 비밀번호가 일치

		// when
		MemberSession memberSession = sut.signin(signinDto);

		// then
		assertThat(memberSession).isNotNull();
		verify(memberSessionRepository).save(any()); // 세션 저장 호출
	}

	@Test
	@DisplayName("로그인 실패, email 없음")
	void signinFailedEmail() {
		// given
		String email = "seungtaek@mimir.com";
		String password = "seungtaek";
		SigninDto signinDto = new SigninDto(email, password);
		given(memberRepository.findByEmail(signinDto.email())).willReturn(null); // email로 조회 없음

		// when, then
		assertThatThrownBy(() -> sut.signin(signinDto))
			.isInstanceOf(AuthException.class)
			.hasMessage(AuthExceptionEnum.SIGN_IN_FAILED.getMessage());
	}


	@Test
	@DisplayName("로그인 실패, 잘못된 비밀번호")
	void signinFailedPassword() {
		// given
		String email = "seungtaek@mimir.com";
		String password = "seungtaek";
		Member member = mock(Member.class);
		SigninDto signinDto = new SigninDto(email, password);
		given(memberRepository.findByEmail(signinDto.email())).willReturn(member); // email로 조회
		given(member.isPasswordMatch(signinDto.password())).willReturn(false); // 비밀번호가 틀림

		// when, then
		assertThatThrownBy(() -> sut.signin(signinDto))
			.isInstanceOf(AuthException.class)
			.hasMessage(AuthExceptionEnum.SIGN_IN_FAILED.getMessage());
	}
}
