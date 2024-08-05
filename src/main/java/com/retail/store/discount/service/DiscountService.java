package com.retail.store.discount.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.retail.store.discount.entity.Customer;
import com.retail.store.discount.enums.ItemType;
import com.retail.store.discount.exception.RetailStoreDiscountException;
import com.retail.store.discount.repository.CustomerRepository;
import com.retail.store.discount.request.DiscountRequest;
import com.retail.store.discount.response.DiscountResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiscountService {

	private final CustomerRepository customerRepository;

	private final StoreEmployeeDiscount storeEmployeeDiscount;

	public DiscountService(CustomerRepository customerRepository, StoreEmployeeDiscount storeEmployeeDiscount) {
		this.customerRepository = customerRepository;
		this.storeEmployeeDiscount = storeEmployeeDiscount;

	}

	public DiscountResponse applyDiscount(@NotNull @Valid DiscountRequest discountRequest) {

		DiscountResponse discountResponse = new DiscountResponse();
		try {
			Optional<Customer> optionalCustomer = customerRepository
					.findByPhoneNumber(discountRequest.getCustomerDetail().getPhoneNumber());

			if (optionalCustomer.isPresent()) {
				Customer customer = optionalCustomer.get();
				ApplyDiscountHandler applyDiscountHandler = storeEmployeeDiscount;

				BigDecimal totalItemPrice = calculateTotalOriginalItemPrice(discountRequest);

				BigDecimal totalPriceForGroceryItemType = calculateTotalOriginalItemPriceForItemType(discountRequest,
						ItemType.GROCERY);

				BigDecimal totalPriceForNonGroceryItemType = totalItemPrice.subtract(totalPriceForGroceryItemType);

				BigDecimal finalItemPrice = applyDiscountHandler.applyDiscount(customer, totalItemPrice,
						totalPriceForNonGroceryItemType);

				createDiscountResponse(discountResponse, customer, totalPriceForNonGroceryItemType, finalItemPrice);
			}
		} catch (Exception e) {
			throw new RetailStoreDiscountException("Exception occurred while apply discount on item: " + e,
					e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return discountResponse;
	}

	private BigDecimal calculateTotalOriginalItemPrice(DiscountRequest discountRequest) {
		return discountRequest.getItemDetails().stream().map(I -> I.getOriginalPrice()).reduce(BigDecimal.ZERO,
				BigDecimal::add);
	}

	private BigDecimal calculateTotalOriginalItemPriceForItemType(DiscountRequest discountRequest, ItemType itemType) {
		return discountRequest.getItemDetails().stream().filter(i -> itemType.equals(i.getItemType()))
				.map(I -> I.getOriginalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private void createDiscountResponse(DiscountResponse discountResponse, Customer customer, BigDecimal originalPrice,
			BigDecimal finalItemPrice) {
		discountResponse.setCustomerName(customer.getName());
		discountResponse.setPhoneNumber(customer.getPhoneNumber());
		discountResponse.setOriginalItemPrice(originalPrice);
		discountResponse.setFinalItemPrice(finalItemPrice);
	}

}
