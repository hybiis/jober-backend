package com.javajober.setting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.javajober.setting.domain.StyleSetting;
import com.javajober.setting.dto.StyleSettingSaveRequest;
import com.javajober.setting.repository.BackgroundSettingRepository;
import com.javajober.setting.repository.BlockSettingRepository;
import com.javajober.setting.repository.StyleSettingRepository;
import com.javajober.setting.repository.ThemeSettingRepository;

@Service
public class StyleSettingService {

	private final StyleSettingRepository styleSettingRepository;
	private final BackgroundSettingRepository backgroundSettingRepository;
	private final BlockSettingRepository blockSettingRepository;
	private final ThemeSettingRepository themeSettingRepository;


	public StyleSettingService(StyleSettingRepository styleSettingRepository,
		BackgroundSettingRepository backgroundSettingRepository, BlockSettingRepository blockSettingRepository,
		ThemeSettingRepository themeSettingRepository) {
		this.styleSettingRepository = styleSettingRepository;
		this.backgroundSettingRepository = backgroundSettingRepository;
		this.blockSettingRepository = blockSettingRepository;
		this.themeSettingRepository = themeSettingRepository;
	}

	@Transactional
	public StyleSetting save(StyleSettingSaveRequest styleSettingSaveRequest) {
		StyleSetting styleSetting = styleSettingSaveRequest.toEntity();

		backgroundSettingRepository.save(styleSetting.getBackgroundSetting());
		blockSettingRepository.save(styleSetting.getBlockSetting());
		themeSettingRepository.save(styleSetting.getThemeSetting());

		return styleSettingRepository.save(styleSetting);
	}
}