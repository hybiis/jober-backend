package com.javajober.listBlock.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.listBlock.domain.ListBlock;
import com.javajober.snsBlock.domain.SNSBlock;

import java.util.Optional;

public interface ListBlockRepository extends Repository<ListBlock, Long> {

	ListBlock save(ListBlock listBlock);

	Optional<ListBlock> findById(Long id);

	default ListBlock findListBlock(final Long id) {
		return findById(id)
			.orElseThrow(() -> new Exception404(ErrorMessage.NOT_FOUND));
	}

}
