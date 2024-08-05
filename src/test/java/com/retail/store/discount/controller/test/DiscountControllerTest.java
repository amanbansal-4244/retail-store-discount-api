package com.retail.store.discount.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.store.discount.controller.DiscountController;
import com.retail.store.discount.request.CustomerDetail;
import com.retail.store.discount.request.DiscountRequest;
import com.retail.store.discount.request.ItemDetail;
import com.retail.store.discount.response.DiscountResponse;
import com.retail.store.discount.service.DiscountService;
import com.retail.store.discount.test.config.SpringTestConfiguration;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(DiscountController.class)
@ContextConfiguration(classes = { SpringTestConfiguration.class })
class DiscountControllerTest {

	private DiscountController discountController;

	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private DiscountService discountService;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		discountController = new DiscountController(discountService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(discountController).build();
	}

	@AfterEach
	public void tearDown() {
		discountController = null;
		objectMapper = null;
	}

	@Test
	void applyDiscountTest() throws Exception {

		DiscountRequest discountRequest = createDiscountRequest();
		DiscountResponse discountResponse = createDiscountResponse();

		when(discountService.applyDiscount(any())).thenReturn(discountResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/discount")
				.content(objectMapper.writeValueAsString(discountRequest))
				.contentType(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	private DiscountResponse createDiscountResponse() {
		DiscountResponse discountResponse = new DiscountResponse();
		discountResponse.setCustomerName("test");
		discountResponse.setFinalItemPrice(new BigDecimal(300));
		discountResponse.setOriginalItemPrice(new BigDecimal(950));
		discountResponse.setPhoneNumber("11234455");
		return discountResponse;
	}

	private DiscountRequest createDiscountRequest() {
		DiscountRequest discountRequest = new DiscountRequest();
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setName("test");
		customerDetail.setPhoneNumber("11234455");

		List<ItemDetail> itemDetails = new ArrayList<>();

		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setName("apple");
		itemDetail.setOriginalPrice(new BigDecimal(950));
		itemDetail.setQuantity(2);
		itemDetails.add(itemDetail);

		discountRequest.setCustomerDetail(customerDetail);
		discountRequest.setItemDetails(itemDetails);

		return discountRequest;
	}

}
