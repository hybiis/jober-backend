package com.javajober.fcm.domain;

import com.javajober.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "member_fcm_token")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MemberFcmToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "fcm_token", nullable = false)
    private String fcmToken;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected MemberFcmToken() {

    }

    @Builder
    public MemberFcmToken(final Member member, final String fcmToken, final String deviceId) {
        this.member = member;
        this.fcmToken = fcmToken;
        this.deviceId = deviceId;
    }

    public void updateFcmToken(final String newFcmToken) {
        this.fcmToken = newFcmToken;
    }
}