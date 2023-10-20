package com.javajober.wallInfoBlock.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.wallInfoBlock.domain.WallInfoBlock;

public interface WallInfoBlockRepository extends Repository<WallInfoBlock, Long> {

	WallInfoBlock save(final WallInfoBlock wallInfoBlock);

	Optional<WallInfoBlock> findByIdAndDeletedAtIsNull(final Long id);

	default WallInfoBlock findWallInfoBlock(final Long id) {
		return findByIdAndDeletedAtIsNull(id)
			.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "공유페이지 블록 데이터를 찾을 수 없습니다."));
	}
}