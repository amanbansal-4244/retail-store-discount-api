package com.retail.store.discount.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.retail.store.discount.constants.Constants;
import com.retail.store.discount.entity.Customer;

@Service
public class TotalBillDiscount extends ApplyDiscountHandler {

	@Override
	public BigDecimal applyDiscount(Customer customer, BigDecimal totalItemPrice,
			BigDecimal totalPriceForNonGroceryItemType) {

		BigDecimal minOrderAmount = new BigDecimal(100);

		int value = totalItemPrice.divide(minOrderAmount).intValue();
		if (value > 0) {
			double discount = (Constants.TOTAL_BILL_DISCOUNT_PERCENTAGE * 100) * Double.valueOf(value);

			return totalItemPrice.subtract(BigDecimal.valueOf(discount).setScale(2, RoundingMode.HALF_EVEN));
		}

		return totalItemPrice.setScale(2, RoundingMode.HALF_EVEN);

	}
}
