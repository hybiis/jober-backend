package com.javajober.wallInfoBlock.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.wallInfoBlock.domain.WallInfoBlock;

public interface WallInfoBlockRepository extends Repository<WallInfoBlock, Long> {

	WallInfoBlock save(final WallInfoBlock wallInfoBlock);

	Optional<WallInfoBlock> findByIdAndDeletedAtIsNull(final Long id);

	default WallInfoBlock findWallInfoBlock(final Long id) {
		return findByIdAndDeletedAtIsNull(id)
				.orElseThrow(() -> new Exception404(ErrorMessage.WALL_INFO_BLOCK_NOT_FOUND));
	}
}