package com.javajober.fcm.controller;

import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.fcm.dto.request.SubscriptionSaveRequest;
import com.javajober.fcm.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wall/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<ApiUtils.ApiResponse<Object>> subscribe(@RequestBody final SubscriptionSaveRequest request) {

        subscriptionService.subscribe(request);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.CREATE_SUCCESS, null));
    }

    @DeleteMapping
    public ResponseEntity<ApiUtils.ApiResponse<Object>> unsubscribe(@RequestBody final SubscriptionSaveRequest request) {

        subscriptionService.unsubscribe(request);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.DELETE_SUCCESS, null));
    }
}