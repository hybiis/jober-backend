package com.javajober.blocks.templateBlock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import com.javajober.blocks.templateBlock.domain.TemplateBlock;

public interface TemplateBlockRepository extends Repository<TemplateBlock, Long> {

	TemplateBlock save(final TemplateBlock templateBlock);

	List<TemplateBlock> saveAll(final Iterable<TemplateBlock> templateBlock);

	List<TemplateBlock> findAllById(final Iterable<Long> id);

	Optional<TemplateBlock> findById(final Long id);

	default TemplateBlock findTemplateBlock(final Long id){
		return findById(id)
			.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "템플릿 블록 데이터를 찾을 수 없습니다."));
	}
}