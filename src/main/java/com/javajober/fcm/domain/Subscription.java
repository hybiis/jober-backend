package com.javajober.fcm.domain;

import com.javajober.member.domain.Member;
import com.javajober.spaceWall.domain.SpaceWall;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "subscription")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", nullable = false)
    private Member subscriber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_wall_id", nullable = false)
    private SpaceWall spaceWall;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected Subscription() {

    }

    public Subscription(final Member subscriber, final SpaceWall spaceWall) {
        this.subscriber = subscriber;
        this.spaceWall = spaceWall;
    }
}