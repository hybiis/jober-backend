package com.javajober.spaceWall.spaceWallCategory.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Table(name = "space_wall_category")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class SpaceWallCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "space_wall_category", nullable = false)
    private SpaceWallCategoryType spaceWallCategory;

    protected SpaceWallCategory() {

    }

    public SpaceWallCategory(final SpaceWallCategoryType spaceWallCategory) {
        this.spaceWallCategory = spaceWallCategory;
    }
}