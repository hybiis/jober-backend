package com.javajober.styleSetting.service;

import org.springframework.stereotype.Service;

import com.javajober.backgroundSetting.repository.BackgroundSettingRepository;
import com.javajober.blockSetting.repository.BlockSettingRepository;
import com.javajober.styleSetting.repository.StyleSettingRepository;
import com.javajober.themeSetting.repository.ThemeSettingRepository;

@Service
public class StyleSettingService {

	private final StyleSettingRepository styleSettingRepository;
	private final BackgroundSettingRepository backgroundSettingRepository;
	private final BlockSettingRepository blockSettingRepository;
	private final ThemeSettingRepository themeSettingRepository;


	public StyleSettingService(final StyleSettingRepository styleSettingRepository,
							   final BackgroundSettingRepository backgroundSettingRepository, final BlockSettingRepository blockSettingRepository,
							   final ThemeSettingRepository themeSettingRepository) {
		this.styleSettingRepository = styleSettingRepository;
		this.backgroundSettingRepository = backgroundSettingRepository;
		this.blockSettingRepository = blockSettingRepository;
		this.themeSettingRepository = themeSettingRepository;
	}
}