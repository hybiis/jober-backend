package com.javajober.styleSetting.repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import org.springframework.data.repository.Repository;

import com.javajober.styleSetting.domain.StyleSetting;

import java.util.Optional;

public interface StyleSettingRepository extends Repository<StyleSetting, Long> {

	StyleSetting save(StyleSetting styleSetting);

	Optional<StyleSetting> findByIdAndDeletedAtIsNull(Long id);

	default StyleSetting getById(final Long id) {
		return findByIdAndDeletedAtIsNull(id)
				.orElseThrow(() -> new Exception404(ErrorMessage.STYLE_SETTING_BLOCK_NOT_FOUND));
	}
}
