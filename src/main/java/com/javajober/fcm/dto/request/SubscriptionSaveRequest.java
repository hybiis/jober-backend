package com.javajober.fcm.dto.request;

import com.javajober.fcm.domain.Subscription;
import com.javajober.member.domain.Member;
import com.javajober.spaceWall.domain.SpaceWall;
import lombok.Getter;

@Getter
public class SubscriptionSaveRequest {

    private Long memberId;
    private Long subscriberMemberId;
    private Long spaceWallId;

    public SubscriptionSaveRequest() {
    }

    public static Subscription toEntity(final Member subscriber, final SpaceWall spaceWall) {
        return new Subscription(subscriber, spaceWall);
    }
}