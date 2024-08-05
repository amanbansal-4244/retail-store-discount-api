package com.retail.store.discount.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CustomException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message, String description, HttpStatus status) {
		super(message, description, status);
	}

}
