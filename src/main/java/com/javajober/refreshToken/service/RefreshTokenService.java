package com.javajober.refreshToken.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.member.domain.Member;
import com.javajober.member.dto.MemberLoginResponse;
import com.javajober.member.repository.MemberRepository;
import com.javajober.refreshToken.domain.RefreshToken;
import com.javajober.refreshToken.dto.RefreshTokenRequest;
import com.javajober.refreshToken.repository.RefreshTokenRepository;
import com.javajober.security.JwtTokenizer;

import io.jsonwebtoken.Claims;

@Service
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;
	private final JwtTokenizer jwtTokenizer;
	private final MemberRepository memberRepository;

	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, JwtTokenizer jwtTokenizer,
		MemberRepository memberRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
		this.jwtTokenizer = jwtTokenizer;
		this.memberRepository = memberRepository;
	}

	@Transactional
	public void deleteRefreshToken(String refreshToken) {
		refreshTokenRepository.findByValue(refreshToken).ifPresent(refreshTokenRepository::delete);
	}

	@Transactional
	public MemberLoginResponse findRefreshToken(RefreshTokenRequest refreshTokenRequest) {
		RefreshToken refreshToken = refreshTokenRepository.findByValue(refreshTokenRequest.getRefreshToken()).orElseThrow(
			() -> new ApplicationException(ApiStatus.NOT_FOUND, "토큰이 존재하지 않습니다."));
		Claims claims = jwtTokenizer.parseRefreshToken(refreshToken.getValue());

		Long userId = Long.valueOf((Integer)claims.get("userId"));
		Member member = memberRepository.findMember(userId);
		String email = claims.getSubject();
		String accessToken = jwtTokenizer.createAccessToken(userId, email);

		return new MemberLoginResponse(member, accessToken, refreshToken.getValue());
	}
}
