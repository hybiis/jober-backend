package com.javajober.space.dto.response;

import com.javajober.member.domain.AccountType;
import com.javajober.memberGroup.domain.MemberHashtagType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberGroupResponse {

    private Long memberId;
    private String memberName;
    private String memberHashtag;
    private String accountType;
    private String phoneNumber;

    private MemberGroupResponse() {
    }

    @Builder
    public MemberGroupResponse(final Long memberId, final String memberName, final MemberHashtagType memberHashtag, final AccountType accountType, final String phoneNumber) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberHashtag = memberHashtag.name();
        this.accountType = accountType.name();
        this.phoneNumber = phoneNumber;
    }

}