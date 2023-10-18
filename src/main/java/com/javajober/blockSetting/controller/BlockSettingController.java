package com.javajober.blockSetting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.blockSetting.dto.request.BlockSettingSaveRequest;
import com.javajober.blockSetting.service.BlockSettingService;

@RequestMapping("/api/setting/block")
@RestController
public class BlockSettingController {

	private final BlockSettingService blockSettingService;

	public BlockSettingController(final BlockSettingService blockSettingService) {
		this.blockSettingService = blockSettingService;
	}

	@PostMapping
	public ResponseEntity<ApiUtils.ApiResponse<Object>> save(@RequestBody final BlockSettingSaveRequest saveRequest){

		blockSettingService.save(saveRequest);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.CREATED, SuccessMessage.CREATE_SUCCESS, null));
	}
}