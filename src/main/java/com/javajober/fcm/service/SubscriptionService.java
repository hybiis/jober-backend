package com.javajober.fcm.service;

import com.javajober.fcm.domain.Subscription;
import com.javajober.fcm.dto.request.SubscriptionSaveRequest;
import com.javajober.fcm.repository.SubscriptionRepository;
import com.javajober.member.domain.Member;
import com.javajober.member.repository.MemberRepository;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final MemberRepository memberRepository;
    private final SpaceWallRepository spaceWallRepository;

    public SubscriptionService(final SubscriptionRepository subscriptionRepository, final MemberRepository memberRepository, final SpaceWallRepository spaceWallRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.memberRepository = memberRepository;
        this.spaceWallRepository = spaceWallRepository;
    }

    public void subscribe(final SubscriptionSaveRequest request) {

        Member subscriber = memberRepository.findMember(request.getSubscriberMemberId());
        SpaceWall spaceWall = spaceWallRepository.getById(request.getMemberId(), request.getSpaceWallId());

        Subscription subscription = SubscriptionSaveRequest.toEntity(subscriber, spaceWall);
        subscriptionRepository.save(subscription);
    }

    public void unsubscribe(final SubscriptionSaveRequest request) {

        Member subscriber = memberRepository.findMember(request.getSubscriberMemberId());
        SpaceWall spaceWall = spaceWallRepository.getById(request.getMemberId(), request.getSpaceWallId());

        Optional<Subscription> existingSubscription = subscriptionRepository.findBySubscriberAndSpaceWall(subscriber, spaceWall);
        existingSubscription.ifPresent(subscriptionRepository::delete);
    }
}