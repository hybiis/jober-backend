package com.javajober.fileBlock.repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.fileBlock.domain.FileBlock;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface FileBlockRepository extends Repository<FileBlock, Long> {

    FileBlock save(final FileBlock fileBlock);

    Optional<FileBlock> findByIdAndDeletedAtIsNull(final Long id);

    default FileBlock findFileBlock(final Long id) {
        return findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new Exception404(ErrorMessage.FILE_BLOCK_NOT_FOUND));
    }
}