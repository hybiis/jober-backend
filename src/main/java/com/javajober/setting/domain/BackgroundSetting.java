package com.javajober.setting.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Table(name = "background_setting")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class BackgroundSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "solid_color")
    private String solidColor;

    @Column(name = "gradation")
    private Boolean gradation;

    @Column(name = "style_image_url")
    private String styleImageURL;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    protected BackgroundSetting() {};

    @Builder
    public BackgroundSetting(final String solidColor, final Boolean gradation, final String styleImageURL) {
        this.solidColor = solidColor;
        this.gradation = gradation;
        this.styleImageURL = styleImageURL;
    }
}
