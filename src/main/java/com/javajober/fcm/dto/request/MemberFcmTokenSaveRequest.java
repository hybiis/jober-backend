package com.javajober.fcm.dto.request;

import com.javajober.member.domain.Member;
import com.javajober.fcm.domain.MemberFcmToken;
import lombok.Getter;

@Getter
public class MemberFcmTokenSaveRequest {

    private Long memberId;
    private String fcmToken;
    private String deviceId;

    public MemberFcmTokenSaveRequest() {
    }

    public static MemberFcmToken toEntity(final MemberFcmTokenSaveRequest request, final Member member) {
        return MemberFcmToken.builder()
                .member(member)
                .fcmToken(request.getFcmToken())
                .deviceId(request.getDeviceId())
                .build();
    }
}