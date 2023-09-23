package com.javajober.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "template_auth")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class TemplateAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_member_id",  nullable = false)
    private MemberGroup authMember;

    @Column(name = "has_access")
    private Boolean hasAccess;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    protected TemplateAuth() {

    }

    @Builder
    public TemplateAuth(final MemberGroup authMember, final Boolean hasAccess) {
        this.authMember = authMember;
        this.hasAccess = hasAccess;
    }
}
