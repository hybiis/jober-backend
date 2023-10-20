package com.javajober.fileBlock.repository;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.fileBlock.domain.FileBlock;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface FileBlockRepository extends Repository<FileBlock, Long> {

    FileBlock save(final FileBlock fileBlock);

    Optional<FileBlock> findByIdAndDeletedAtIsNull(final Long id);

    default FileBlock findFileBlock(final Long id) {
        return findByIdAndDeletedAtIsNull(id)
            .orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "파일 블록 데이터를 찾을 수 없습니다."));
    }
}