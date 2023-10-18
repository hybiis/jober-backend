package com.javajober.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ApiStatus {

	OK(HttpStatus.OK,  "succeeded request"),
	FILE_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "file upload fail"),
	FILE_DELETE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "file delete fail"),
	FILE_DOWNLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "file download fail"),
	INVALID_DATA(HttpStatus.BAD_REQUEST, "invalid data"),
	EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "exception"),
	IO_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "io exception"),
	NO_PERMISSION(HttpStatus.UNAUTHORIZED, "no permission"),
	ALREADY_EXIST(HttpStatus.CONFLICT, "already data exists"),
	FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "failed request"),
	OBJECT_EMPTY(HttpStatus.BAD_REQUEST, "object is empty"),
	INVALID_DATE(HttpStatus.BAD_REQUEST, "invalid date"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "data not found"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "login token expire");

	private final HttpStatus httpStatus;
	private final String message;

	ApiStatus(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
