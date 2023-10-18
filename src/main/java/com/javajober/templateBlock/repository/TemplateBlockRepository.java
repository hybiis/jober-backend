package com.javajober.templateBlock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.templateBlock.domain.TemplateBlock;

public interface TemplateBlockRepository extends Repository<TemplateBlock, Long> {

	TemplateBlock save(final TemplateBlock templateBlock);

	List<TemplateBlock> saveAll(final Iterable<TemplateBlock> templateBlock);

	List<TemplateBlock> findAllById(final Iterable<Long> id);

	Optional<TemplateBlock> findById(final Long id);

	default TemplateBlock findTemplateBlock(final Long id){
		return findById(id)
			.orElseThrow(() -> new Exception404(ErrorMessage.TEMPLATE_BLOCK_NOT_FOUND));
	}
}