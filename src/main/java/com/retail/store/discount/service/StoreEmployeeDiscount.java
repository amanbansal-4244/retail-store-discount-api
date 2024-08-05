package com.retail.store.discount.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.retail.store.discount.constants.Constants;
import com.retail.store.discount.entity.Customer;
import com.retail.store.discount.enums.CustomerType;
import com.retail.store.discount.utils.DiscountUtil;

@Service
public class StoreEmployeeDiscount extends ApplyDiscountHandler {

	public StoreEmployeeDiscount() {
		super(new StoreAffliliateDiscount());
	}

	@Override
	public BigDecimal applyDiscount(Customer customer, BigDecimal totalItemPrice,
			BigDecimal totalPriceForNonGroceryItemType) {

		if (CustomerType.STORE_EMPLOYEE.getName().equalsIgnoreCase(customer.getCustomerType())) {
			return DiscountUtil.getFinalPriceAfterApplyDiscount(totalPriceForNonGroceryItemType,
					Constants.STORE_EMPLOYEE_DISCOUNT_PERCENTAGE);
		} else {
			return applyDiscountHandler.applyDiscount(customer, totalItemPrice, totalPriceForNonGroceryItemType);
		}

	}

}
