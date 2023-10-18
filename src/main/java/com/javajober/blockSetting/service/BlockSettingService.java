package com.javajober.blockSetting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javajober.blockSetting.domain.BlockSetting;
import com.javajober.blockSetting.dto.request.BlockSettingSaveRequest;
import com.javajober.blockSetting.repository.BlockSettingRepository;

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