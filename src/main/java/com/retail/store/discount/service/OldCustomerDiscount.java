package com.retail.store.discount.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.retail.store.discount.constants.Constants;
import com.retail.store.discount.entity.Customer;
import com.retail.store.discount.utils.DiscountUtil;

@Service
public class OldCustomerDiscount extends ApplyDiscountHandler {

	public OldCustomerDiscount() {
		super(new TotalBillDiscount());
	}

	@Override
	public BigDecimal applyDiscount(Customer customer, BigDecimal totalItemPrice,
			BigDecimal totalPriceForNonGroceryItemType) {

		Period period = Period.between(customer.getCreatedAt(), LocalDate.now());
		if (period.getYears() >= 2) {
			return DiscountUtil.getFinalPriceAfterApplyDiscount(totalPriceForNonGroceryItemType,
					Constants.OLD_CUSTOMER_PERCENTAGE);

		} else {
			return applyDiscountHandler.applyDiscount(customer, totalItemPrice, totalPriceForNonGroceryItemType);
		}

	}

}
