package com.javajober.blocks.styleSetting.themeSetting.repository;

import java.util.Optional;

import com.javajober.blocks.styleSetting.themeSetting.domain.ThemeSetting;
import org.springframework.data.repository.Repository;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;

public interface ThemeSettingRepository extends Repository<ThemeSetting, Long> {

	ThemeSetting save(final ThemeSetting themeSetting);

	Optional<ThemeSetting> findById(final Long id);

	default ThemeSetting getById (final Long id) {
		return findById(id)
			.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "테마 설정 데이터를 찾을 수 없습니다."));
	}
}