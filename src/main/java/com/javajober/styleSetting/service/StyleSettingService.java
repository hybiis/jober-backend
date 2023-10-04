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


	public StyleSettingService(StyleSettingRepository styleSettingRepository,
							   BackgroundSettingRepository backgroundSettingRepository, BlockSettingRepository blockSettingRepository,
							   ThemeSettingRepository themeSettingRepository) {
		this.styleSettingRepository = styleSettingRepository;
		this.backgroundSettingRepository = backgroundSettingRepository;
		this.blockSettingRepository = blockSettingRepository;
		this.themeSettingRepository = themeSettingRepository;
	}

//	@Transactional
//	public StyleSetting save(StyleSettingSaveRequest styleSettingSaveRequest, String styleImgURL) {
//		StyleSetting styleSetting = styleSettingSaveRequest.toEntity(styleImgURL);
//
//		backgroundSettingRepository.save(styleSettingSaveRequest.getBackgroundSetting().toEntity(styleImgURL));
//		blockSettingRepository.save(styleSetting.getBlockSetting());
//		themeSettingRepository.save(styleSetting.getThemeSetting());
//
//		return styleSettingRepository.save(styleSetting);
//	}
}