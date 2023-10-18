package com.javajober.template.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.javajober.memberGroup.domain.MemberGroup;
import com.javajober.templateBlock.domain.TemplateBlock;

@Getter
@Table(name = "template_auth")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class TemplateAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_group_id",  nullable = false)
    private MemberGroup authMember;

    @Column(name = "has_access")
    private Boolean hasAccess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_block_id")
    private TemplateBlock templateBlock;

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
    public TemplateAuth(final MemberGroup authMember, final Boolean hasAccess, final TemplateBlock templateBlock) {
        this.authMember = authMember;
        this.hasAccess = hasAccess;
        this.templateBlock = templateBlock;
    }

    public void update(final MemberGroup authMember, final Boolean hasAccess, final TemplateBlock templateBlock) {
        this.authMember = authMember;
        this.hasAccess = hasAccess;
        this.templateBlock = templateBlock;
    }

    public void setDeletedAt() {
        this.deletedAt = LocalDateTime.now();
    }
}