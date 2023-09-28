package com.javajober.setting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.setting.domain.ThemeSetting;
import com.javajober.setting.dto.ThemeSettingSaveRequest;
import com.javajober.setting.repository.ThemeSettingRepository;

@Service
public class ThemeSettingService {

	private final ThemeSettingRepository themeSettingRepository;

	public ThemeSettingService(ThemeSettingRepository themeSettingRepository) {
		this.themeSettingRepository = themeSettingRepository;
	}

	@Transactional
	public ThemeSetting save(final ThemeSettingSaveRequest saveRequest){

		ThemeSetting themeSetting = ThemeSettingSaveRequest.toEntity(saveRequest);

		return themeSettingRepository.save(themeSetting);
	}
}
