package com.retail.store.discount.service;

import java.math.BigDecimal;

import com.retail.store.discount.entity.Customer;

public abstract class ApplyDiscountHandler {

	public ApplyDiscountHandler applyDiscountHandler;

	protected ApplyDiscountHandler() {

	}

	protected ApplyDiscountHandler(ApplyDiscountHandler applyDiscountHandler) {
		this.applyDiscountHandler = applyDiscountHandler;
	}

	public abstract BigDecimal applyDiscount(Customer customer, BigDecimal totalItemPrice,
			BigDecimal totalPriceForNonGroceryItemType);
}
