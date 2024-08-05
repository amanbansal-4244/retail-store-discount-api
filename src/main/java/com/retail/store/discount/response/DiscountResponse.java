package com.retail.store.discount.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DiscountResponse {

	private String customerName;
	private String phoneNumber;
	private BigDecimal originalItemPrice;
	private BigDecimal finalItemPrice;
	private LocalDateTime customerSince;
	private LocalDateTime lastUpdated;
}
