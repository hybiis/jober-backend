package com.javajober.member.dto;

import com.javajober.member.domain.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLoginResponse {
	private String accessToken;
	private String refreshToken;
	private Long memberId;
	private String nickname;

	private MemberLoginResponse(){

	}

	@Builder
	public MemberLoginResponse(final String accessToken, final String refreshToken, final Long memberId, final String nickname){
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.memberId = memberId;
		this.nickname = nickname;
	}

	public MemberLoginResponse(final Member member, final String accessToken, final String refreshToken){
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.memberId = member.getId();
		this.nickname = member.getMemberName();
	}
}
