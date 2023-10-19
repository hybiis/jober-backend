package com.javajober.member.dto;

import java.time.LocalDateTime;

import com.javajober.member.domain.Member;
import com.javajober.member.domain.MemberShipType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignupResponse {
	private Long memberId;
	private String email;
	private String name;
	private String phoneNumber;
	private MemberShipType memberShip;
	private LocalDateTime regDate;

	private MemberSignupResponse(){

	}
	@Builder
	public MemberSignupResponse(final Long memberId, final String email, final String name, final String phoneNumber, final MemberShipType memberShip, final LocalDateTime regDate){
		this.memberId = memberId;
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.memberShip = memberShip;
		this.regDate = regDate;
	}

	public MemberSignupResponse(Member member) {
		this.memberId = member.getId();
		this.email = member.getMemberEmail();
		this.name = member.getMemberName();
		this.phoneNumber = member.getPhoneNumber();
		this.memberShip = member.getMemberShip();
		this.regDate = member.getCreatedAt();
	}
}
