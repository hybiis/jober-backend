package com.javajober.member.domain;

import lombok.Getter;

@Getter
public enum MemberShipType {

	FREE("프리", "0", "0"),
	BASIC("베이직", "4,000", "3,000"),
	STANDARD("스탠다드", "6,000", "5,000"),
	PREMIUM("프리미엄", "9,000", "8,000"),
	LITE("라이트", "9,900", "7,900"),
	PLUS("플러스", "28,900", "22,900"),
	PRO("프로", "52,900", "38,900"),
	ENTERPRISE("엔터프라이즈", "별도협의", "별도협의");

	private final String title;
	private final String monthlyPay;
	private final String yearlyPay;

	MemberShipType(final String title, final String monthlyPay, final String yearlyPay) {
		this.title = title;
		this.monthlyPay = monthlyPay;
		this.yearlyPay = yearlyPay;
	}
}