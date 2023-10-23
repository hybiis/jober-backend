package com.javajober.member.service;


import java.util.Optional;

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
		Optional<Member> existingMember = memberRepository.findMember(memberSignupRequest.getEmail());

		if (existingMember.isPresent()) {
			throw new ApplicationException(ApiStatus.ALREADY_EXIST, "이미 등록된 이메일입니다.");
		}

		Member member = memberSignupRequest.toEntity(memberSignupRequest);
		member.setPassword(passwordEncoder.encode(memberSignupRequest.getPassword()));
		Member saveMember = memberRepository.save(member);

		return new MemberSignupResponse(saveMember);
	}

	@Transactional
	public MemberLoginResponse login(MemberLoginRequest loginDto) {
		Member member = memberRepository.findMember(loginDto.getEmail()).orElseThrow(()
			-> new ApplicationException(ApiStatus.NOT_FOUND, "존재하지 않는 회원 아이디입니다."));

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