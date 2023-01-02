package com.example.mimir.common.spring.argumentResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;
import com.example.mimir.authentication.domain.entity.LoginMember;
import com.example.mimir.authentication.domain.entity.MemberSession;
import com.example.mimir.authentication.domain.exception.AuthException;
import com.example.mimir.authentication.domain.exception.AuthExceptionEnum;
import com.example.mimir.authentication.service.AuthService;
import com.example.mimir.member.domain.entity.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
	private final AuthService authService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(LoginMember.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		Cookie cookie = WebUtils.getCookie(request, MemberSession.COOKIE_NAME);

		if (cookie == null || cookie.getValue() == null) {
			throw new AuthException(AuthExceptionEnum.INVALID_SESSION_ID);
		}

		String cookieValue = cookie.getValue();
		Member member = authService.getMemberByCookieValue(cookieValue);

		if (member.getDisabledAt() != null) {
			throw new AuthException(AuthExceptionEnum.INVALID_SESSION_ID);
		}

		return LoginMember.from(member);
	}
}
