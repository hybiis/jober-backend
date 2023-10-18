package com.javajober.themeSetting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.themeSetting.dto.request.ThemeSettingSaveRequest;
import com.javajober.themeSetting.service.ThemeSettingService;

@RequestMapping("/api/setting/theme")
@RestController
public class ThemeSettingController {

	private final ThemeSettingService themeSettingService;

	public ThemeSettingController(final ThemeSettingService themeSettingService) {
		this.themeSettingService = themeSettingService;
	}

	@PostMapping
	public ResponseEntity<ApiUtils.ApiResponse> save(@RequestBody final ThemeSettingSaveRequest saveRequest){

		themeSettingService.save(saveRequest);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.CREATED, SuccessMessage.CREATE_SUCCESS, null));
	}
}