package com.javajober.styleSetting.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javajober.styleSetting.service.StyleSettingService;

@RequestMapping("/api/setting/style")
@RestController
public class StyleSettingController {

	private final StyleSettingService styleSettingService;

	public StyleSettingController(final StyleSettingService styleSettingService) {
		this.styleSettingService = styleSettingService;
	}
}