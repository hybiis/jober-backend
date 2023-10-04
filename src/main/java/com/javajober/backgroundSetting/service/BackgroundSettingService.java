package com.javajober.backgroundSetting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.backgroundSetting.domain.BackgroundSetting;
import com.javajober.backgroundSetting.filedto.BackgroundSettingSaveRequest;
import com.javajober.backgroundSetting.repository.BackgroundSettingRepository;

@Service
public class BackgroundSettingService {

	private final BackgroundSettingRepository backgroundSettingRepository;


	public BackgroundSettingService(final BackgroundSettingRepository backgroundSettingRepository) {
		this.backgroundSettingRepository = backgroundSettingRepository;
	}

	@Transactional
	public BackgroundSetting save(final BackgroundSettingSaveRequest saveRequest, String styleImgName){

		BackgroundSetting backgroundSetting = saveRequest.toEntity(styleImgName);
		return backgroundSettingRepository.save(backgroundSetting);
	}
}
