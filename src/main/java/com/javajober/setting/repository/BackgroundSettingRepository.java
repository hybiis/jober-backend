package com.javajober.setting.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.setting.domain.BackgroundSetting;
import com.javajober.snsBlock.domain.SNSBlock;

public interface BackgroundSettingRepository extends Repository<BackgroundSetting, Long> {


	BackgroundSetting save(BackgroundSetting backgroundSetting);

	Optional<BackgroundSetting> findById(Long id);

	default BackgroundSetting getById (final Long id) {
		return findById(id)
			.orElseThrow(() -> new Exception404(ErrorMessage.NOT_FOUND));
	}
}
