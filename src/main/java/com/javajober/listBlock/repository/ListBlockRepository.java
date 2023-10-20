package com.javajober.listBlock.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.listBlock.domain.ListBlock;

public interface ListBlockRepository extends Repository<ListBlock, Long> {

	ListBlock save(final ListBlock listBlock);

	Optional<ListBlock> findByIdAndDeletedAtIsNull(final Long id);

	default ListBlock findListBlock(final Long id) {
		return findByIdAndDeletedAtIsNull(id)
			.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "리스트 블록 데이터를 찾을 수 없습니다."));
	}
}