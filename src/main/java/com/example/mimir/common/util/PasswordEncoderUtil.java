package com.example.mimir.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private PasswordEncoderUtil() {}

	public static String encode(String raw) {
		return passwordEncoder.encode(raw);
	}

	public static boolean matches(String raw, String encoded) {
		return passwordEncoder.matches(raw, encoded);
	}
}
