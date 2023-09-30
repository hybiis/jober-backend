package com.javajober.setting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.setting.domain.BlockSetting;
import com.javajober.setting.dto.BlockSettingSaveRequest;
import com.javajober.setting.repository.BlockSettingRepository;

@Service
public class BlockSettingService {

	private final BlockSettingRepository blockSettingRepository;

	public BlockSettingService(BlockSettingRepository blockSettingRepository) {
		this.blockSettingRepository = blockSettingRepository;
	}

	@Transactional
	public void save(final BlockSettingSaveRequest saveRequest){

		BlockSetting blockSetting  = saveRequest.toEntity();

		blockSettingRepository.save(blockSetting);
	}
}
