package com.javajober.freeBlock.repository;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.freeBlock.domain.FreeBlock;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface FreeBlockRepository extends Repository<FreeBlock, Long> {

    FreeBlock save(final FreeBlock freeBlock);

    Optional<FreeBlock> findByIdAndDeletedAtIsNull(final Long freeId);

    default FreeBlock findFreeBlock(final Long freeId) {
        return findByIdAndDeletedAtIsNull(freeId)
            .orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "자유 블록 데이터를 찾을 수 없습니다."));
    }
}