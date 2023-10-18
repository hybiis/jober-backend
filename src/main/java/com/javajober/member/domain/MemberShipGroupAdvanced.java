package com.javajober.member.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public enum MemberShipGroupAdvanced {

	PEOPLE_PLAN("개인 플랜", Arrays.asList(MemberShipType.FREE, MemberShipType.BASIC, MemberShipType.STANDARD, MemberShipType.PREMIUM, MemberShipType.ENTERPRISE)),
	DOCS_PLAN("스페이스 플랜", Arrays.asList(MemberShipType.FREE, MemberShipType.LITE, MemberShipType.PLUS, MemberShipType.PRO, MemberShipType.ENTERPRISE)),
	LITE_CRM_PLAN("고객관리 플랜", Collections.emptyList()),
	EMPTY("없음",  Collections.emptyList());

	private final String title;
	private final List<MemberShipType> memberShipTypes;

	MemberShipGroupAdvanced(final String title, final List<MemberShipType> memberShipTypes) {
		this.title = title;
		this.memberShipTypes = memberShipTypes;
	}

	public static MemberShipGroupAdvanced findByMemberShipType(final MemberShipType memberShipType) {
		return Arrays.stream(MemberShipGroupAdvanced.values())
			.filter(memberShip -> memberShip.hasPayCode(memberShipType))
			.findAny()
			.orElse(EMPTY);
	}

	public boolean hasPayCode(final MemberShipType memberShipType) {
		return memberShipTypes.stream()
			.anyMatch(memberShip -> memberShip == memberShipType);
	}
}