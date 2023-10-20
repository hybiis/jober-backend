package com.javajober.styleSetting.repository;

import org.springframework.data.repository.Repository;

import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.styleSetting.domain.StyleSetting;

import java.util.Optional;

public interface StyleSettingRepository extends Repository<StyleSetting, Long> {

	StyleSetting save(final StyleSetting styleSetting);

	Optional<StyleSetting> findByIdAndDeletedAtIsNull(Long id);

	default StyleSetting findStyleBlock(final Long id) {
		return findByIdAndDeletedAtIsNull(id)
				.orElseThrow(() -> new ApplicationException(ApiStatus.NOT_FOUND, "스타일 설정 데이터를 찾을 수 없습니다."));
	}
}