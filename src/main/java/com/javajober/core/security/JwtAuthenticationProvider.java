package com.javajober.core.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final JwtTokenizer jwtTokenizer;

	public JwtAuthenticationProvider(JwtTokenizer jwtTokenizer) {

		this.jwtTokenizer = jwtTokenizer;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
		Claims claims = jwtTokenizer.parseAccessToken(authenticationToken.getToken());
		String email = claims.getSubject();

		return new JwtAuthenticationToken(email, null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
