package com.javajober.member.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public class MemberLoginRequest {
	public MemberLoginRequest() {

	}

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
	private String email;

	@NotEmpty
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$") // 영문, 특수문자 8자 이상 20자 이하
	private String password;
}
