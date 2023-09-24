package com.javajober.dto;


import java.util.List;

import com.javajober.entity.Member;
import com.javajober.entity.MemberGroup;
import com.javajober.entity.TemplateAuth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberAuthResponse {
	private List<MemberInfo> list;

	public MemberAuthResponse(List<MemberAuthResponse.MemberInfo> list){
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
		private MemberInfo(Long memberId, boolean hasAccess,String memberName,String memberHashtag, String memberType, String phoneNumber) {
			this.memberId = memberId;
			this.hasAccess = hasAccess;
			this.memberName = memberName;
			this.memberHashtag =memberHashtag;
			this.memberType = memberType;
			this.phoneNumber = phoneNumber;
		}

		public static MemberInfo from(MemberGroup memberGroup,Member member, TemplateAuth templateAuth){
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

