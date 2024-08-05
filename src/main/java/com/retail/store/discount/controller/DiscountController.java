package com.retail.store.discount.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retail.store.discount.request.DiscountRequest;
import com.retail.store.discount.response.DiscountResponse;
import com.retail.store.discount.service.DiscountService;
import com.retail.store.discount.utils.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Discount Rest Controller")
public class DiscountController {

	private final DiscountService discountService;

	/**
	 * Helps to apply discount on item
	 * 
	 * @param headers
	 * @param discountRequest
	 * @return DiscountResponse
	 */
	@PostMapping(value = "/discount")
	@ResponseStatus(HttpStatus.OK)
	public DiscountResponse applyDiscount(
			@Parameter(description = "Discount request which to be created in context.", required = true) @NotNull @Valid @RequestBody DiscountRequest discountRequest) {
		log.info("Call recieved to apply discount with request: {}",
				ObjectMapperUtil.convertObjectToJson(discountRequest));
		DiscountResponse discountResponse = discountService.applyDiscount((discountRequest));

		log.info("Discount has been applied successfully and response is: {}",
				ObjectMapperUtil.convertObjectToJson(discountResponse));
		return discountResponse;
	}

}
