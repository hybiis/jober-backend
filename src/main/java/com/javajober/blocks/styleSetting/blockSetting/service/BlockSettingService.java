package com.javajober.blocks.styleSetting.blockSetting.service;

import com.javajober.blocks.styleSetting.blockSetting.dto.request.BlockSettingSaveRequest;
import com.javajober.blocks.styleSetting.blockSetting.repository.BlockSettingRepository;
import com.javajober.blocks.styleSetting.blockSetting.domain.BlockSetting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockSettingService {

	private final BlockSettingRepository blockSettingRepository;

	public BlockSettingService(final BlockSettingRepository blockSettingRepository) {
		this.blockSettingRepository = blockSettingRepository;
	}

	@Transactional
	public void save(final BlockSettingSaveRequest saveRequest){

		BlockSetting blockSetting  = saveRequest.toEntity();

		blockSettingRepository.save(blockSetting);
	}
}