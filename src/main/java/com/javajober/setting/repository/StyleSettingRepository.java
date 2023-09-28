package com.javajober.setting.repository;

import org.springframework.data.repository.Repository;

import com.javajober.setting.domain.StyleSetting;

public interface StyleSettingRepository extends Repository<StyleSetting, Long> {

	StyleSetting save(StyleSetting styleSetting);
}
