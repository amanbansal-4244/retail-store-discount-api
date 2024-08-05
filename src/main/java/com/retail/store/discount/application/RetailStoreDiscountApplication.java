package com.retail.store.discount.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.*"})
public class RetailStoreDiscountApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailStoreDiscountApplication.class, args);
	}

}


