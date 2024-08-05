package com.retail.store.discount.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountUtil {

	private DiscountUtil() {

	}

	public static BigDecimal getFinalPriceAfterApplyDiscount(BigDecimal price, double discountPercentage) {
		BigDecimal discount = price
				.multiply(BigDecimal.valueOf(discountPercentage).setScale(2, RoundingMode.HALF_EVEN));

		return price.subtract(discount).setScale(2, RoundingMode.HALF_EVEN);
	}
}
