package com.javajober.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class AuthenticationManagerConfig extends AbstractHttpConfigurer<AuthenticationManagerConfig, HttpSecurity> {

	private final JwtAuthenticationProvider jwtAuthenticationProvider;

	public AuthenticationManagerConfig(JwtAuthenticationProvider jwtAuthenticationProvider) {
		this.jwtAuthenticationProvider = jwtAuthenticationProvider;
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

		builder.addFilterBefore(
				new JwtAuthenticationFilter(authenticationManager),
				UsernamePasswordAuthenticationFilter.class)
			.authenticationProvider(jwtAuthenticationProvider);
	}
}
