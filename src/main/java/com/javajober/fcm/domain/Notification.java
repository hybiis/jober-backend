package com.javajober.fcm.domain;

import com.javajober.member.domain.Member;
import com.javajober.spaceWall.domain.SpaceWall;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "notification")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_wall_id")
    private SpaceWall spaceWall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id")
    private Member subscriber;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected Notification(){

    }

    public Notification(final SpaceWall spaceWall, final Member subscriber){
        this.spaceWall = spaceWall;
        this.subscriber = subscriber;
    }
}