package com.javajober.wallInfoBlock.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.snsBlock.domain.SNSBlock;
import com.javajober.wallInfoBlock.domain.WallInfoBlock;

import java.util.Optional;

public interface WallInfoBlockRepository extends Repository<WallInfoBlock, Long> {

	WallInfoBlock save(WallInfoBlock wallInfoBlock);

	Optional<WallInfoBlock> findById(Long id);

	default WallInfoBlock findWallInfoBlock(final Long id) {
		return findById(id)
			.orElseThrow(() -> new Exception404(ErrorMessage.NOT_FOUND));
	}
}
