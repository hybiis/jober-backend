package com.javajober.setting.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.setting.domain.BlockSetting;
import com.javajober.setting.domain.ThemeSetting;

public interface ThemeSettingRepository extends Repository<ThemeSetting, Long> {

	ThemeSetting save(ThemeSetting themeSetting);

	Optional<ThemeSetting> findById(Long id);

	default ThemeSetting getById (final Long id) {
		return findById(id)
			.orElseThrow(() -> new Exception404(ErrorMessage.NOT_FOUND));
	}
}