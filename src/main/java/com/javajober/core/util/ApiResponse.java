package com.javajober.core.util;

import org.springframework.http.ResponseEntity;

import com.javajober.exception.ApiStatus;

import lombok.Getter;

@Getter
public class ApiResponse {

	private ApiResponse() {

	}

	public static ResponseEntity<InvalidResponse> invalidResponse (final ApiStatus status, final String message) {
		return ResponseEntity.status(status.getHttpStatus()).body(InvalidResponse.of(status, message));
	}

	public static <T> ResponseEntity<Response<T>> response(final ApiStatus status, final String message, final T data) {
		return ResponseEntity.ok().body(Response.of(status, message, data));
	}

	@Getter
	public static class InvalidResponse {
		private final ApiStatus status;
		private final String message;

		private InvalidResponse(final ApiStatus status, final String message) {
			this.status = status;
			this.message = message;
		}

		public static InvalidResponse of(final ApiStatus status, final String message) {
			return new InvalidResponse(status, message);
		}
	}

	@Getter
	public static class Response<T> {

		private final ApiStatus status;
		private final String message;
		private final T data;

		private Response (final ApiStatus status, final String message, final T data) {
			this.status = status;
			this.message = message;
			this.data = data;
		}

		public static <T> Response<T> of(final ApiStatus status, final String message, final T data) {
			return new Response<>(status, message, data);
		}
	}
}

