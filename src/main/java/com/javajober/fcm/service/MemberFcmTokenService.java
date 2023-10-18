package com.javajober.fcm.service;

import com.javajober.member.domain.Member;
import com.javajober.fcm.domain.MemberFcmToken;
import com.javajober.fcm.dto.request.MemberFcmTokenSaveRequest;
import com.javajober.fcm.repository.MemberFcmTokenRepository;
import com.javajober.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberFcmTokenService {

    private final MemberFcmTokenRepository memberFcmTokenRepository;
    private final MemberRepository memberRepository;

    public MemberFcmTokenService(final MemberFcmTokenRepository memberFcmTokenRepository, final MemberRepository memberRepository) {
        this.memberFcmTokenRepository = memberFcmTokenRepository;
        this.memberRepository = memberRepository;
    }

    public void save(final MemberFcmTokenSaveRequest request) {

        Member member = memberRepository.findMember(request.getMemberId());
        Optional<MemberFcmToken> existingTokenOpt = memberFcmTokenRepository.findByMemberAndDeviceId(member, request.getDeviceId());

        if (existingTokenOpt.isPresent()) {
            updateExistingToken(existingTokenOpt.get(), request.getFcmToken());
            return;
        }
        MemberFcmToken token = MemberFcmTokenSaveRequest.toEntity(request, member);
        memberFcmTokenRepository.save(token);
    }

    private void updateExistingToken(final MemberFcmToken existingToken, final String newFcmToken) {

        existingToken.updateFcmToken(newFcmToken);
        memberFcmTokenRepository.save(existingToken);
    }
}