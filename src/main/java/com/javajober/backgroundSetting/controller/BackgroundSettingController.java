package com.javajober.backgroundSetting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.backgroundSetting.filedto.BackgroundSettingSaveRequest;
import com.javajober.backgroundSetting.service.BackgroundSettingService;

@RequestMapping("/api/setting/background")
@RestController
public class BackgroundSettingController {

	private final BackgroundSettingService backgroundSettingService;

	public BackgroundSettingController(final BackgroundSettingService backgroundSettingService) {
		this.backgroundSettingService = backgroundSettingService;
	}

	@PostMapping
	public ResponseEntity<ApiUtils.ApiResponse<Object>> save(
		@RequestPart(value = "backgroundRequest") final BackgroundSettingSaveRequest styleSetting, final String styleImgName){

		backgroundSettingService.save(styleSetting, styleImgName);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.CREATED, SuccessMessage.CREATE_SUCCESS, null));
	}
}