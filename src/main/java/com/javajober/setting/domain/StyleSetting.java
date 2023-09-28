package com.javajober.setting.domain;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "style_setting")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class StyleSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "background_setting_id")
    private BackgroundSetting backgroundSetting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_setting_id")
    private BlockSetting blockSetting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_setting_id")
    private ThemeSetting themeSetting;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    protected StyleSetting() {

    }

    @Builder
    public StyleSetting(final BackgroundSetting backgroundSetting, final BlockSetting blockSetting, final ThemeSetting themeSetting) {
        this.backgroundSetting = backgroundSetting;
        this.blockSetting = blockSetting;
        this.themeSetting = themeSetting;
    }
}
