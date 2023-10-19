package com.javajober.member.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.javajober.member.domain.Member;
import com.javajober.member.domain.MemberShipType;

import lombok.Getter;

@Getter
public class MemberSignupRequest {

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
		message = "이메일 형식을 맞춰야합니다")
	private String email;

	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{7,16}$",
		message = "비밀번호는 영문+숫자+특수문자를 포함한 8~20자여야 합니다")
	private String password;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z가-힣\\\\s]{2,15}",
		message = "이름은 영문자, 한글, 공백포함 2글자부터 15글자까지 가능합니다.")
	private String name;

	private String phoneNumber;

	private MemberShipType memberShip;

	private MemberSignupRequest(){

	}

	public static Member toEntity(final MemberSignupRequest memberSignupRequest){
		return Member.builder()
			.memberEmail(memberSignupRequest.getEmail())
			.memberName(memberSignupRequest.getName())
			.phoneNumber(memberSignupRequest.getPhoneNumber())
			.memberShip(memberSignupRequest.getMemberShip())
			.build();
	}
}
