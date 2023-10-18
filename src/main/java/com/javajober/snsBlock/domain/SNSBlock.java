package com.javajober.snsBlock.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "sns_block")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class SNSBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sns_uuid", nullable = false)
    private String snsUUID;

    @Enumerated(EnumType.STRING)
    @Column(name = "sns_type", nullable = false)
    private SNSType snsType;

    @Column(name = "sns_url", nullable = false)
    private String snsURL;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    protected SNSBlock() {}

    @Builder
    public SNSBlock(final String snsUUID, final SNSType snsType, final String snsURL) {
        this.snsUUID = snsUUID;
        this.snsType = snsType;
        this.snsURL = snsURL;
    }

    public void update(final String snsUUID, final SNSType snsType, final String snsURL) {
        this.snsUUID = snsUUID;
        this.snsType = snsType;
        this.snsURL = snsURL;
    }

    public void updateTimeOnDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}