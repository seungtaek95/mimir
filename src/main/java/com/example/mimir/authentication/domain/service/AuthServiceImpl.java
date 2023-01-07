package com.example.mimir.authentication.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import com.example.mimir.authentication.domain.entity.MemberSession;
import com.example.mimir.authentication.domain.exception.AuthException;
import com.example.mimir.authentication.domain.exception.AuthExceptionEnum;
import com.example.mimir.authentication.domain.service.dto.SigninDto;
import com.example.mimir.authentication.domain.service.dto.SignupDto;
import com.example.mimir.authentication.repository.MemberSessionRepository;
import com.example.mimir.common.util.UuidUtils;
import com.example.mimir.member.domain.entity.Member;
import com.example.mimir.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final MemberRepository memberRepository;
	private final MemberSessionRepository memberSessionRepository;

	@Override
	public Member signup(SignupDto signupDto) {
		// email이 사용중인지 확인
		if (this.isEmailUsed(signupDto.email())) {
			throw new AuthException(AuthExceptionEnum.DUPLICATE_EMAIL);
		}

		// 사용자 생성 후 저장
		Member member = Member.signup(
			signupDto.email(),
			signupDto.password(),
			signupDto.nickname());

		memberRepository.save(member);

		return member;
	}

	@Override
	public MemberSession signin(SigninDto signinDto) {
		// email로 사용자 조회
		Member member = memberRepository.findByEmail(signinDto.email());

		if (member == null || !member.isPasswordMatch(signinDto.password())) {
			throw new AuthException(AuthExceptionEnum.SIGN_IN_FAILED);
		}

		// session 저장 후 session id 반환
		MemberSession memberSession = new MemberSession(member.getId());
		memberSessionRepository.save(memberSession);

		return memberSession;
	}

	@Override
	public Member getMemberByCookieValue(String cookieValue) {
		if (cookieValue == null || cookieValue.length() != 72) {
			throw new AuthException(AuthExceptionEnum.INVALID_SESSION_ID);
		}

		UUID uuid = UUID.fromString(cookieValue.substring(0, 36));
		UUID memberId = UUID.fromString(cookieValue.substring(36, 72));
		byte[] memberSessionId = UuidUtils.concatToBytes(uuid, memberId);

		// 세션 조회
		MemberSession memberSession = memberSessionRepository.findById(memberSessionId)
			.orElseThrow(() -> new AuthException(AuthExceptionEnum.INVALID_SESSION_ID));

		if (!memberSession.getMemberId().equals(memberId)) {
			throw new AuthException(AuthExceptionEnum.INVALID_SESSION_ID);
		}

		// 회원 조회
		return memberRepository.findById(UuidUtils.uuidToBytes(memberId))
			.orElseThrow(() -> new AuthException(AuthExceptionEnum.INVALID_SESSION_ID));
	}

	private boolean isEmailUsed(String email) {
		Member member = memberRepository.findByEmail(email);
		return member != null;
	}
}
