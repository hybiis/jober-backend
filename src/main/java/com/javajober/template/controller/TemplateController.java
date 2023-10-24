package com.javajober.template.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.core.util.response.ApiResponse;
import com.javajober.spaceWall.spaceWallCategory.domain.SpaceWallCategoryType;
import com.javajober.template.dto.response.MemberAuthResponse;
import com.javajober.space.domain.SpaceType;
import com.javajober.template.dto.response.TemplateResponse;
import com.javajober.template.service.TemplateService;

@RestController
@RequestMapping("/api/wall/templates")
public class TemplateController {

	private final TemplateService templateService;

	public TemplateController(TemplateService templateService) {
		this.templateService = templateService;
	}

	@GetMapping("/auth")
	public ResponseEntity<ApiResponse.Response<MemberAuthResponse>> findTemplateAuthList(@RequestParam final SpaceType spaceType, @RequestParam final Long memberId, @RequestParam final Long templateBlockId) {

		MemberAuthResponse data = templateService.findTemplateAuthList(spaceType, memberId, templateBlockId);

		return ApiResponse.response(ApiStatus.OK, "권한설정을 위한 유저정보 조회를 성공했습니다.", data);
	}

	@GetMapping
	public ResponseEntity<ApiResponse.Response<TemplateResponse>> findTemplateRecommend(@RequestParam final SpaceWallCategoryType category){

		TemplateResponse data = templateService.findTemplateRecommend(category);

		return ApiResponse.response(ApiStatus.OK, "추천템플릿 조회를 성공했습니다", data);
	}

	@GetMapping("/lists")
	public ResponseEntity<ApiResponse.Response<TemplateResponse>> findTemplateCategoryList(@RequestParam final SpaceWallCategoryType category){

		TemplateResponse data = templateService.findTemplateRecommend(category);

		return ApiResponse.response(ApiStatus.OK, "카테고리별 템플릿 리스트 조회를 성공했습니다.", data);
	}

	@GetMapping(params = "search")
	public ResponseEntity<ApiResponse.Response<TemplateResponse>> findSearchTemplatesByTitle(@RequestParam final String search){

		TemplateResponse data = templateService.findSearchTemplatesByTitle(search);

		return ApiResponse.response(ApiStatus.OK, "템플릿 검색을 성공했습니다.", data);
	}
}