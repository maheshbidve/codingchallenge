package com.smarthost.codingchallenge.controller;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthost.codingchallenge.model.BookingResponse;
import com.smarthost.codingchallenge.service.BookingService;

@RestController
@RequestMapping(path = "/bookings")
@Validated
public class BookingController {

	@Autowired
	BookingService service;

	@GetMapping
	BookingResponse calculate(
			@RequestParam(value = "numberOfEconomyRooms") @Min(value = 1, message = "numberOfEconomyRooms must be greater than or equal to 1") int economyRooms,
			@RequestParam(value = "numberOfPremiumRooms") @Min(value = 1, message = "numberOfPremiumRooms must be greater than or equal to 1") int premiumRooms)
			throws Exception {

		return service.calculate(economyRooms, premiumRooms);
	}

}
