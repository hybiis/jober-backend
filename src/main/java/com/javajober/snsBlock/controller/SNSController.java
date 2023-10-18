package com.javajober.snsBlock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.snsBlock.dto.request.SNSBlockDeleteRequest;
import com.javajober.snsBlock.dto.request.SNSBlockSaveRequest;
import com.javajober.snsBlock.dto.request.SNSBlockSaveRequests;
import com.javajober.snsBlock.dto.request.SNSBlockUpdateRequest;
import com.javajober.snsBlock.dto.response.SNSBlockResponses;
import com.javajober.snsBlock.service.SNSBlockService;

@RequestMapping("/api/wall/sns/blocks")
@RestController
public class SNSController {

	private final SNSBlockService snsBlockService;

	public SNSController(final SNSBlockService snsBlockService) {
		this.snsBlockService = snsBlockService;
	}

	@PostMapping
	public ResponseEntity<ApiUtils.ApiResponse> save(@RequestBody final SNSBlockSaveRequests<SNSBlockSaveRequest> snsBlockSaveRequests) {

		snsBlockService.save(snsBlockSaveRequests);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.CREATED, SuccessMessage.CREATE_SUCCESS, null));
	}

	@GetMapping
	public ResponseEntity<ApiUtils.ApiResponse<SNSBlockResponses>> find(@RequestParam final List<Long> snsBlockIds) {

		SNSBlockResponses data = snsBlockService.find(snsBlockIds);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.READ_SUCCESS, data));
	}

	@PutMapping
	public ResponseEntity<ApiUtils.ApiResponse> update(@RequestBody final SNSBlockSaveRequests<SNSBlockUpdateRequest> snsBlockSaveRequests) {

		snsBlockService.update(snsBlockSaveRequests);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.CREATE_SUCCESS, null));
	}

	@PutMapping("/history")
	public ResponseEntity<ApiUtils.ApiResponse> delete(@RequestBody final SNSBlockDeleteRequest snsBlockDeleteRequest) {

		snsBlockService.delete(snsBlockDeleteRequest);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.DELETE_SUCCESS, null));
	}
}