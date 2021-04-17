package com.smarthost.codingchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.smarthost.codingchallenge.controller.BookingController;
import com.smarthost.codingchallenge.service.BookingService;

@WebMvcTest(value = BookingController.class)
public class InputValidationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookingService service;

	@Test
	public void test_missing_param_1() throws Exception {

		mockMvc.perform(get("/v1/bookings").param("numberOfPremiumRooms", "3")).andExpect(status().is4xxClientError())
				.andExpect(result -> assertTrue(
						result.getResolvedException() instanceof MissingServletRequestParameterException))
				.andExpect(result -> assertEquals(
						"Required request parameter 'numberOfEconomyRooms' for method parameter type int is not present",
						result.getResolvedException().getMessage()));
		;
	}

	@Test
	public void test_missing_param_2() throws Exception {

		mockMvc.perform(get("/v1/bookings").param("numberOfEconomyRooms", "3")).andExpect(status().is4xxClientError())
				.andExpect(result -> assertTrue(
						result.getResolvedException() instanceof MissingServletRequestParameterException))
				.andExpect(result -> assertEquals(
						"Required request parameter 'numberOfPremiumRooms' for method parameter type int is not present",
						result.getResolvedException().getMessage()));
		;
	}

	@Test
	public void test_all_valid_params() throws Exception {

		mockMvc.perform(get("/v1/bookings").param("numberOfEconomyRooms", "3").param("numberOfPremiumRooms", "3"))
				.andExpect(status().isOk());
	}

}
