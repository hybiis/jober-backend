package com.javajober.listBlock.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Table(name = "list_block")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ListBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "list_uuid", nullable = false)
    private String listUUID;

    @Column(name = "list_label" , nullable = false)
    private String listLabel;

    @Column(name = "list_title", nullable = false)
    private String listTitle;

    @Column(name = "list_description" , nullable = false)
    private String listDescription;

    @Column (name = "is_link", nullable = false)
    private boolean isLink;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    protected ListBlock() {}

    @Builder
    public ListBlock(final String listUUID, final String listLabel, final String listTitle, final String listDescription, final boolean isLink) {
        this.listUUID = listUUID;
        this.listLabel = listLabel;
        this.listTitle = listTitle;
        this.listDescription = listDescription;
        this.isLink = isLink;
    }
    public void update(final String listUUID, final String listLabel, final String listTitle, final String listDescription, final boolean isLink) {
        this.listUUID = listUUID;
        this.listLabel = listLabel;
        this.listTitle = listTitle;
        this.listDescription = listDescription;
        this.isLink = isLink;
    }
}