package com.javajober.space.domain;

import com.javajober.member.domain.Member;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name="add_space")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class AddSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "space_title", nullable = false)
    private String spaceTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "space_type", nullable = false)
    private SpaceType spaceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    protected AddSpace() {

    }

    public AddSpace (final String spaceTitle, final SpaceType spaceType, Member member) {
        this.spaceTitle = spaceTitle;
        this.spaceType = spaceType;
        this.member = member;
    }
}