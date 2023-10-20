package com.javajober.blocks.wallInfoBlock.domain;

import com.javajober.blocks.wallInfoBlock.filedto.WallInfoBlockUpdateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.javajober.blocks.wallInfoBlock.dto.request.WallInfoBlockStringUpdateRequest;

@Getter
@Table(name="wall_info_block")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class WallInfoBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wall_info_background_image_url")
    private String wallInfoBackgroundImageUrl;

    @Column(name = "wall_info_profile_image_url")
    private String wallInfoProfileImageUrl;

    @Column(name = "wall_info_title")
    private String wallInfoTitle;

    @Column(name = "wall_info_description")
    private String wallInfoDescription;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    protected WallInfoBlock() {

    }

    @Builder
    public WallInfoBlock(final String wallInfoBackgroundImageUrl, final String wallInfoProfileImageUrl, final String wallInfoTitle, final String wallInfoDescription){
        this.wallInfoBackgroundImageUrl = wallInfoBackgroundImageUrl;
        this.wallInfoProfileImageUrl = wallInfoProfileImageUrl;
        this.wallInfoTitle = wallInfoTitle;
        this.wallInfoDescription = wallInfoDescription;
    }

    public void update (WallInfoBlockUpdateRequest request, String backgroundImgName, String wallInfoImgName){
        this.wallInfoBackgroundImageUrl = backgroundImgName;
        this.wallInfoProfileImageUrl = wallInfoImgName;
        this.wallInfoTitle = request.getWallInfoTitle();
        this.wallInfoDescription = request.getWallInfoDescription();
    }

    public void update(WallInfoBlockStringUpdateRequest request){
        this.wallInfoBackgroundImageUrl = request.getBackgroundImgURL();
        this.wallInfoProfileImageUrl = request.getWallInfoImgURL();
        this.wallInfoTitle = request.getWallInfoTitle();
        this.wallInfoDescription = request.getWallInfoDescription();
    }
}