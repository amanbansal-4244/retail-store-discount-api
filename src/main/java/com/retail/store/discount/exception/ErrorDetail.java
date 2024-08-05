package com.retail.store.discount.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class ErrorDetail {

	private HttpStatus status;
	private String message;
	private String requestedURI;
	private List<String> errors;
	private int statusCode;

	public ErrorDetail(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ErrorDetail(HttpStatus status, int statusCode, String message, String error) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public ErrorDetail(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public ErrorDetail(String message, String requestedURI, HttpStatus status, String error) {
		super();
		this.status = status;
		this.message = message;
		this.requestedURI = requestedURI;
		this.errors = Arrays.asList(error);
	}

}
