package com.javajober.template.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.javajober.spaceWall.spaceWallCategory.domain.SpaceWallCategory;

@Getter
@Table(name = "template")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_title", nullable = false)
    private String templateTitle;

    @Column(name = "template_description", nullable = false)
    private String templateDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_wall_category_id")
    private SpaceWallCategory spaceWallCategory;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    protected Template() {}

    @Builder
    public Template(final String templateTitle, final String templateDescription, final SpaceWallCategory spaceWallCategory) {
        this.templateTitle = templateTitle;
        this.templateDescription = templateDescription;
        this.spaceWallCategory = spaceWallCategory;
    }
}