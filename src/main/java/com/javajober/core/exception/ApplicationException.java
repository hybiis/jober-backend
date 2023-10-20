package com.javajober.core.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
	ApiStatus status;
	String info;

	public ApplicationException(final ApiStatus status, final String info) {
		super(info);
		this.status = status;
		this.info = info;
	}
}