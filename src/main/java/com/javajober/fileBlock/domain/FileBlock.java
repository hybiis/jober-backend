package com.javajober.fileBlock.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "file_block")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class FileBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_title", length = 100, nullable = false)
    private String fileTitle;

    @Column(name = "file_description",length = 1000, nullable = false)
    private String fileDescription;

    @Column(name = "file_name", columnDefinition = "text")
    private String fileName;

    @Column(name = "file", columnDefinition = "text")
    private String file;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    protected FileBlock() {

    }

    @Builder
    public FileBlock(final String fileTitle, final String fileDescription, final String fileName, final String file) {
        this.fileTitle = fileTitle;
        this.fileDescription = fileDescription;
        this.fileName = fileName;
        this.file = file;
    }

    @Builder
    public FileBlock(final String fileTitle, final String fileDescription, final String fileName) {
        this.fileTitle = fileTitle;
        this.fileDescription = fileDescription;
        this.fileName = fileName;
    }

    public void update(final String fileTitle, final String fileDescription, final String fileName, final String file) {
        this.fileTitle = fileTitle;
        this.fileDescription = fileDescription;
        this.fileName = fileName;
        this.file = file;
    }

    public void fileUpdate(final String fileTitle, final String fileDescription, final String fileName) {
        this.fileTitle = fileTitle;
        this.fileDescription = fileDescription;
        this.fileName = fileName;
    }

    public void setDeletedAt() {
        this.deletedAt = LocalDateTime.now();
    }
}