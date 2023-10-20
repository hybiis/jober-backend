package com.javajober.template.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.spaceWall.spaceWallCategory.domain.SpaceWallCategoryType;
import com.javajober.template.dto.response.MemberAuthResponse;
import com.javajober.space.domain.SpaceType;
import com.javajober.template.dto.response.TemplateResponse;
import com.javajober.template.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wall/templates")
public class TemplateController {

	private final TemplateService templateService;

	@GetMapping("/auth")
	public ResponseEntity<ApiUtils.ApiResponse<MemberAuthResponse>> findTemplateAuthList(@RequestParam final SpaceType spaceType, @RequestParam final Long memberId, @RequestParam final Long templateBlockId) {

		MemberAuthResponse data = templateService.findTemplateAuthList(spaceType, memberId, templateBlockId);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.TEMPLATE_AUTH_SUCCESS, data));
	}

	@GetMapping
	public ResponseEntity<ApiUtils.ApiResponse<TemplateResponse>> findTemplateRecommend(@RequestParam final SpaceWallCategoryType category){

		TemplateResponse data = templateService.findTemplateRecommend(category);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.TEMPLATE_RECOMMEND_SUCCESS, data));
	}

	@GetMapping("/lists")
	public ResponseEntity<ApiUtils.ApiResponse<TemplateResponse>> findTemplateCategoryList(@RequestParam final SpaceWallCategoryType category){

		TemplateResponse data = templateService.findTemplateRecommend(category);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.TEMPLATE_CATEGORY_SUCCESS, data));
	}

	@GetMapping(params = "search")
	public ResponseEntity<ApiUtils.ApiResponse<TemplateResponse>> findSearchTemplatesByTitle(@RequestParam final String search){

		TemplateResponse data = templateService.findSearchTemplatesByTitle(search);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.TEMPLATE_SEARCH_SUCCESS, data));
	}
}