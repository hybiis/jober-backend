package com.javajober.snsBlock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.snsBlock.domain.SNSBlock;

public interface SNSBlockRepository extends Repository<SNSBlock, Long> {

	SNSBlock save(final SNSBlock snsBlock);

	List<SNSBlock> saveAll(final Iterable<SNSBlock> snsBlock);

	List<SNSBlock> findAllById(final Iterable<Long> id);

	Optional<SNSBlock> findById(final Long id);

	default SNSBlock findSNSBlock(final Long id) {
		return findById(id)
			.orElseThrow(() -> new Exception404(ErrorMessage.NOT_FOUND));
	}
}