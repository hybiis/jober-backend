package com.javajober.core.refreshToken.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RefreshTokenRequest {

	@NotEmpty
	String refreshToken;
}
