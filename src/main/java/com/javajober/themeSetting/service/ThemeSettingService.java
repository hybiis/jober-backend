package com.javajober.themeSetting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.themeSetting.domain.ThemeSetting;
import com.javajober.themeSetting.dto.request.ThemeSettingSaveRequest;
import com.javajober.themeSetting.repository.ThemeSettingRepository;

@Service
public class ThemeSettingService {

	private final ThemeSettingRepository themeSettingRepository;

	public ThemeSettingService( final ThemeSettingRepository themeSettingRepository) {
		this.themeSettingRepository = themeSettingRepository;
	}

	@Transactional
	public ThemeSetting save(final ThemeSettingSaveRequest saveRequest){
		ThemeSetting themeSetting = saveRequest.toEntity();
		return themeSettingRepository.save(themeSetting);
	}
}