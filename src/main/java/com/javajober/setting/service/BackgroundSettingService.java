package com.javajober.setting.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javajober.core.config.FileDirectoryConfig;
import com.javajober.core.error.exception.Exception500;
import com.javajober.core.message.ErrorMessage;
import com.javajober.setting.domain.BackgroundSetting;
import com.javajober.setting.dto.BackgroundSettingSaveRequest;
import com.javajober.setting.repository.BackgroundSettingRepository;

@Service
public class BackgroundSettingService {

	private final BackgroundSettingRepository backgroundSettingRepository;

	private final FileDirectoryConfig fileDirectoryConfig;

	public BackgroundSettingService(BackgroundSettingRepository backgroundSettingRepository,
		FileDirectoryConfig fileDirectoryConfig) {
		this.backgroundSettingRepository = backgroundSettingRepository;
		this.fileDirectoryConfig = fileDirectoryConfig;
	}

	@Transactional
	public BackgroundSetting save(final BackgroundSettingSaveRequest saveRequest){

		//String styleImg = uploadImg(file);

		BackgroundSetting backgroundSetting = saveRequest.toEntity();

		return backgroundSettingRepository.save(backgroundSetting);
	}
}
