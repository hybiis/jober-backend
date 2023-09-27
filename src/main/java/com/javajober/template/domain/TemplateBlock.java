package com.javajober.template.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Table(name = "template_block")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class TemplateBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "template_uuid",  nullable = false)
    private String templateUUID;

    @Column(name = "template_title",  nullable = false)
    private String templateTitle;

    @Column(name = "template_description",  nullable = false)
    private String templateDescription;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    protected TemplateBlock() {}

    @Builder
    public TemplateBlock(final String templateUUID, final String templateTitle, final String templateDescription) {
        this.templateUUID = templateUUID;
        this.templateTitle = templateTitle;
        this.templateDescription = templateDescription;
    }

    public void setDeletedAt(){
        this.deletedAt = LocalDateTime.now();
    }
}
