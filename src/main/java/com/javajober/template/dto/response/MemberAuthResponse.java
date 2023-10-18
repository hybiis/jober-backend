package com.javajober.template.dto.response;

import java.util.List;

import com.javajober.member.domain.Member;
import com.javajober.memberGroup.domain.MemberGroup;
import com.javajober.template.domain.TemplateAuth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberAuthResponse {

	private List<MemberInfo> list;

	public MemberAuthResponse(final List<MemberAuthResponse.MemberInfo> list){
		this.list=list;
	}

	@Getter
	public static class MemberInfo {
		private Long memberId;
		private boolean hasAccess;
		private String memberName;
		private String memberHashtag;
		private String memberType;
		private String phoneNumber;

		@Builder
		private MemberInfo(final Long memberId, final boolean hasAccess, final String memberName, final String memberHashtag, final String memberType, final String phoneNumber) {
			this.memberId = memberId;
			this.hasAccess = hasAccess;
			this.memberName = memberName;
			this.memberHashtag =memberHashtag;
			this.memberType = memberType;
			this.phoneNumber = phoneNumber;
		}

		public static MemberInfo of(final MemberGroup memberGroup, final Member member, final TemplateAuth templateAuth){
			return MemberInfo.builder()
				.memberId(member.getId())
				.hasAccess(templateAuth.getHasAccess())
				.memberName(member.getMemberName())
				.memberHashtag(memberGroup.getMemberHashtagType().getDescription())
				.memberType(memberGroup.getAccountType().getDescription())
				.phoneNumber(member.getPhoneNumber())
				.build();
		}
	}
}