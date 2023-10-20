package com.javajober.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javajober.core.util.response.ApiResponse;
import com.javajober.core.exception.ApiStatus;
import com.javajober.home.dto.response.HomeResponse;
import com.javajober.home.service.HomeService;

@RestController
@RequestMapping("/api/home")
public class HomeController {

	private final HomeService homeService;

	private HomeController (final HomeService homeService) {
		this.homeService = homeService;
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<ApiResponse.Response<HomeResponse>> find(@PathVariable final Long memberId) {

		HomeResponse data = homeService.find(memberId);

		String message = "홈 조회를 성공했습니다.";

		return ApiResponse.response(ApiStatus.OK, message, data);
	}
}