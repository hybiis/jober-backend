package com.javajober.blockSetting.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.blockSetting.domain.BlockSetting;

public interface BlockSettingRepository extends Repository<BlockSetting, Long> {

	BlockSetting save(final BlockSetting blockSetting);

	Optional<BlockSetting> findById(final Long id);

	default BlockSetting getById (final Long id) {
		return findById(id)
			.orElseThrow(() -> new Exception404(ErrorMessage.NOT_FOUND));
	}
}