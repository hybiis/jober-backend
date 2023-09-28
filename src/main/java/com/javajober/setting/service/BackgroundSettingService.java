package com.javajober.setting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.setting.domain.BackgroundSetting;
import com.javajober.setting.dto.BackgroundSettingSaveRequest;
import com.javajober.setting.repository.BackgroundSettingRepository;

@Service
public class BackgroundSettingService {

	private final BackgroundSettingRepository backgroundSettingRepository;

	public BackgroundSettingService(BackgroundSettingRepository backgroundSettingRepository) {
		this.backgroundSettingRepository = backgroundSettingRepository;
	}

	@Transactional
	public BackgroundSetting save(final BackgroundSettingSaveRequest saveRequest){

		BackgroundSetting backgroundSetting = BackgroundSettingSaveRequest.toEntity(saveRequest);

		return backgroundSettingRepository.save(backgroundSetting);
	}
}
