package com.retail.store.discount.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import com.retail.store.discount.controller.DiscountController;
import com.retail.store.discount.entity.Customer;
import com.retail.store.discount.enums.CustomerType;
import com.retail.store.discount.enums.ItemType;
import com.retail.store.discount.exception.CustomException;
import com.retail.store.discount.repository.CustomerRepository;
import com.retail.store.discount.request.CustomerDetail;
import com.retail.store.discount.request.DiscountRequest;
import com.retail.store.discount.request.ItemDetail;
import com.retail.store.discount.response.DiscountResponse;
import com.retail.store.discount.service.DiscountService;
import com.retail.store.discount.service.StoreEmployeeDiscount;
import com.retail.store.discount.test.config.SpringTestConfiguration;
import com.retail.store.discount.utils.DiscountUtil;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(DiscountController.class)
@ContextConfiguration(classes = { SpringTestConfiguration.class })
class DiscountServiceTest {

	private DiscountService discountService;

	@Mock
	private CustomerRepository customerRepository;

	@Spy
	private StoreEmployeeDiscount storeEmployeeDiscount;

	@Spy
	private DiscountUtil discountUtil;

	@BeforeEach
	public void setUp() {
		discountService = new DiscountService(customerRepository, storeEmployeeDiscount);
	}

	@AfterEach
	public void tearDown() {
		discountService = null;
	}

	@Test
	void applyDiscountStoreCustomerTypeSuccessTest() {

		when(customerRepository.findByPhoneNumber(anyString()))
				.thenReturn(Optional.ofNullable(createCustomer(CustomerType.STORE_EMPLOYEE.getName(), 0)));

		DiscountResponse discountResponse = discountService.applyDiscount(createDiscountRequest());

		assertEquals(new BigDecimal(1050).setScale(2, RoundingMode.HALF_EVEN), discountResponse.getFinalItemPrice());
	}

	@Test
	void applyDiscountAffiliateCustomerTypeSuccessTest() {

		when(customerRepository.findByPhoneNumber(anyString()))
				.thenReturn(Optional.ofNullable(createCustomer(CustomerType.AFFLILIATE.getName(), 0)));

		DiscountResponse discountResponse = discountService.applyDiscount(createDiscountRequest());

		assertEquals(new BigDecimal(1350).setScale(2, RoundingMode.HALF_EVEN), discountResponse.getFinalItemPrice());
	}

	@Test
	void applyDiscountNormalOldCustomerTypeSuccessTest() {

		when(customerRepository.findByPhoneNumber(anyString()))
				.thenReturn(Optional.ofNullable(createCustomer(CustomerType.CUSTOMER.getName(), 3)));

		DiscountResponse discountResponse = discountService.applyDiscount(createDiscountRequest());

		assertEquals(new BigDecimal(1425).setScale(2, RoundingMode.HALF_EVEN), discountResponse.getFinalItemPrice());
	}

	@Test
	void applyDiscountOnTotalBillSuccessTest() {

		when(customerRepository.findByPhoneNumber(anyString()))
				.thenReturn(Optional.ofNullable(createCustomer(CustomerType.CUSTOMER.getName(), 1)));

		DiscountResponse discountResponse = discountService.applyDiscount(createDiscountRequest());

		assertEquals(new BigDecimal(2330).setScale(2, RoundingMode.HALF_EVEN), discountResponse.getFinalItemPrice());
	}

	@Test
	@DisplayName("Test failure scenario discountService.applyDiscount()")
	void shouldReturnExceptionWhenInvokeApplyDiscount() {
		when(customerRepository.findByPhoneNumber(anyString()))
				.thenReturn(Optional.ofNullable(createCustomer(CustomerType.CUSTOMER.getName(), 1)));

		CustomException exception = assertThrows(CustomException.class, () -> {

			when(discountService.applyDiscount(createDiscountRequest())).thenThrow(new RuntimeException());

			discountService.applyDiscount(createDiscountRequest());
		});

		assertTrue(exception.getMessage().contains("Exception occurred while apply discount on item"));
	}

	private Customer createCustomer(String customerType, int noOfyears) {
		Customer customer = new Customer();
		customer.setName("test");
		customer.setPhoneNumber(null);
		customer.setVersion(1);
		customer.setCustomerType(customerType);
		customer.setPhoneNumber("11234455");
		customer.setCreatedAt(LocalDate.now().minusYears(noOfyears));
		return customer;
	}

	private DiscountRequest createDiscountRequest() {
		DiscountRequest discountRequest = new DiscountRequest();
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setName("test");
		customerDetail.setPhoneNumber("11234455");

		List<ItemDetail> itemDetails = new ArrayList<>();

		ItemDetail itemDetail1 = new ItemDetail();
		itemDetail1.setName("apple");
		itemDetail1.setItemType(ItemType.GROCERY);
		itemDetail1.setOriginalPrice(new BigDecimal(950));
		itemDetail1.setQuantity(2);

		ItemDetail itemDetail2 = new ItemDetail();
		itemDetail2.setName("jeans");
		itemDetail2.setItemType(ItemType.CLOTHES);
		itemDetail2.setOriginalPrice(new BigDecimal(500));
		itemDetail2.setQuantity(2);

		ItemDetail itemDetail3 = new ItemDetail();
		itemDetail3.setName("shirt");
		itemDetail3.setItemType(ItemType.CLOTHES);
		itemDetail3.setOriginalPrice(new BigDecimal(400));
		itemDetail3.setQuantity(2);

		ItemDetail itemDetail4 = new ItemDetail();
		itemDetail4.setName("iphone11");
		itemDetail4.setItemType(ItemType.MOBILE);
		itemDetail4.setOriginalPrice(new BigDecimal(600));
		itemDetail4.setQuantity(1);

		itemDetails.add(itemDetail1);
		itemDetails.add(itemDetail2);
		itemDetails.add(itemDetail3);
		itemDetails.add(itemDetail4);

		discountRequest.setCustomerDetail(customerDetail);
		discountRequest.setItemDetails(itemDetails);

		return discountRequest;
	}

}
