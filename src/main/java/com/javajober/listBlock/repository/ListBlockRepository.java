package com.javajober.listBlock.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.listBlock.domain.ListBlock;

public interface ListBlockRepository extends Repository<ListBlock, Long> {

	ListBlock save(final ListBlock listBlock);

	Optional<ListBlock> findByIdAndDeletedAtIsNull(final Long id);

	default ListBlock findListBlock(final Long id) {
		return findByIdAndDeletedAtIsNull(id)
				.orElseThrow(() -> new Exception404(ErrorMessage.LIST_BLOCK_NOT_FOUND));
	}
}