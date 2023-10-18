package com.javajober.memberGroup.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "member_hashtag")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MemberHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_hashtag", nullable = false)
    private MemberHashtagType memberHashtag;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    protected MemberHashtag() {};

    public MemberHashtag(final MemberHashtagType memberHashtag) {
        this.memberHashtag = memberHashtag;
    }
}