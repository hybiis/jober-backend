package com.javajober.templateBlock.controller;

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
import com.javajober.templateBlock.dto.request.TemplateBlockDeleteRequest;
import com.javajober.templateBlock.dto.request.TemplateBlockSaveRequest;
import com.javajober.templateBlock.dto.request.TemplateBlockSaveRequests;
import com.javajober.templateBlock.dto.response.TemplateBlockResponses;
import com.javajober.templateBlock.dto.request.TemplateBlockUpdateRequest;
import com.javajober.templateBlock.service.TemplateBlockService;

@RequestMapping("/api/wall/templateBlocks")
@RestController
public class TemplateBlockController {

	private final TemplateBlockService templateBlockService;

	public TemplateBlockController(final TemplateBlockService templateBlockService) {
		this.templateBlockService = templateBlockService;
	}

	@PostMapping
	public ResponseEntity<ApiUtils.ApiResponse> save(@RequestBody final TemplateBlockSaveRequests<TemplateBlockSaveRequest> templateBlockSaveRequests) {

		templateBlockService.save(templateBlockSaveRequests);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.CREATED, SuccessMessage.TEMPLATE_BLOCK_SAVE_SUCCESS, null));
	}

	@GetMapping
	public ResponseEntity<ApiUtils.ApiResponse<TemplateBlockResponses>> find(@RequestParam final List<Long> templateBlockIds) {

		TemplateBlockResponses data = templateBlockService.find(templateBlockIds);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.TEMPLATE_BLOCK_READ_SUCCESS, data));
	}

	@PutMapping
	public ResponseEntity<ApiUtils.ApiResponse> update(@RequestBody final TemplateBlockSaveRequests<TemplateBlockUpdateRequest> templateBlockSaveRequests){

		templateBlockService.update(templateBlockSaveRequests);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.TEMPLATE_BLOCK_UPDATE_SUCCESS, null));
	}

	@PutMapping("/history")
	public ResponseEntity<ApiUtils.ApiResponse> delete(@RequestBody final TemplateBlockDeleteRequest templateBlockDeleteRequest) {

		templateBlockService.delete(templateBlockDeleteRequest);

		return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.TEMPLATE_BLOCK_DELETE_SUCCESS, null));
	}
}