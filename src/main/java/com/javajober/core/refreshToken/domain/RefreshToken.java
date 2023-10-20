package com.javajober.core.refreshToken.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Builder;
import lombok.Getter;

@Getter
@Table(name="refresh_token")
@Entity
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long memberId;

	private String tokenValue;

	public RefreshToken(){

	}

	@Builder
	public RefreshToken(final Long memberId, final String tokenValue){
		this.memberId = memberId;
		this.tokenValue = tokenValue;
	}
}
