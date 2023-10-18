package com.javajober.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	private final AuthenticationManagerConfig authenticationManagerConfig;
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	public SecurityConfig(AuthenticationManagerConfig authenticationManagerConfig,
		CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
		this.authenticationManagerConfig = authenticationManagerConfig;
		this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용 x 설정
			.and()
			.formLogin().disable()
			.csrf().disable()
			.cors()
			.and()
			.apply(authenticationManagerConfig)
			.and()
			.httpBasic().disable()
			.authorizeRequests()
			.antMatchers("/members/login").permitAll()
			.antMatchers("/members/signup").permitAll()
			.antMatchers("/healthCheck").permitAll()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(customAuthenticationEntryPoint)
			.and()
			.build();
	}

	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedMethod("*");
		config.setAllowedMethods(List.of("GET","POST","DELETE","PATCH","OPTION","PUT"));
		source.registerCorsConfiguration("/**", config);

		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
