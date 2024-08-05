package com.retail.store.discount.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;
	private final String description;
	private final HttpStatus httpStatus;

	public CustomException(String message, String description, HttpStatus httpStatus) {
		this.message = message;
		this.description = description;
		this.httpStatus = httpStatus;
	}

	public CustomException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.description = "";
		this.httpStatus = httpStatus;
	}
}
