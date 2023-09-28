package com.javajober.setting.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.setting.domain.BackgroundSetting;
import com.javajober.setting.domain.BlockSetting;



public interface BlockSettingRepository extends Repository<BlockSetting, Long> {

	BlockSetting save(BlockSetting blockSetting);

	Optional<BlockSetting> findById(Long id);

	default BlockSetting getById (final Long id) {
		return findById(id)
			.orElseThrow(() -> new Exception404(ErrorMessage.NOT_FOUND));
	}
}