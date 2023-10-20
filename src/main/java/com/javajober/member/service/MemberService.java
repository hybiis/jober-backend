package com.javajober.member.service;


import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import com.javajober.member.domain.Member;
import com.javajober.member.dto.MemberLoginRequest;
import com.javajober.member.dto.MemberLoginResponse;
import com.javajober.member.dto.MemberSignupRequest;
import com.javajober.member.dto.MemberSignupResponse;
import com.javajober.member.repository.MemberRepository;
import com.javajober.core.refreshToken.repository.RefreshTokenRepository;
import com.javajober.core.security.JwtTokenizer;
import com.javajober.core.refreshToken.domain.RefreshToken;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenizer jwtTokenizer;
	private final RefreshTokenRepository refreshTokenRepository;

	public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtTokenizer jwtTokenizer,
		RefreshTokenRepository refreshTokenRepository) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenizer = jwtTokenizer;
		this.refreshTokenRepository = refreshTokenRepository;
	}

	@Transactional
	public MemberSignupResponse signup(MemberSignupRequest memberSignupRequest) {
		Member member = memberSignupRequest.toEntity(memberSignupRequest);
		member.setPassword(passwordEncoder.encode(memberSignupRequest.getPassword()));
		Member saveMember = memberRepository.save(member);

		return new MemberSignupResponse(saveMember);
	}

	@Transactional
	public MemberLoginResponse login(MemberLoginRequest loginDto) {
		Member member = memberRepository.findMember(loginDto.getEmail());

		if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
			throw new ApplicationException(ApiStatus.NOT_FOUND, "비밀번호가 일치하지 않습니다.");
		}

		String accessToken = jwtTokenizer.createAccessToken(member.getId(), member.getMemberEmail());
		String refreshToken = jwtTokenizer.createRefreshToken(member.getId(), member.getMemberEmail());

		RefreshToken refreshTokenEntity = new RefreshToken(member.getId(),refreshToken);
		refreshTokenRepository.save(refreshTokenEntity);

		return new MemberLoginResponse(member,accessToken,refreshToken);
	}
}