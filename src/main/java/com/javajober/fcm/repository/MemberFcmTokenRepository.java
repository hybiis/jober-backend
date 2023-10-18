package com.javajober.fcm.repository;

import com.javajober.fcm.domain.MemberFcmToken;
import com.javajober.member.domain.Member;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberFcmTokenRepository extends Repository<MemberFcmToken, Long> {

    MemberFcmToken save(final MemberFcmToken token);

    Optional<MemberFcmToken> findByMemberAndDeviceId(final Member member, final String deviceId);
}