package com.retail.store.discount.exception;

import org.springframework.http.HttpStatus;

public class RetailStoreDiscountException extends CustomException {

	private static final long serialVersionUID = 1L;

	public RetailStoreDiscountException(String message, String description, HttpStatus httpStatus) {
        super(message,description,httpStatus);
    }

}
