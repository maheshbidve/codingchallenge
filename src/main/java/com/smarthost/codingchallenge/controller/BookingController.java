package com.smarthost.codingchallenge.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
	
	@Autowired 
	@Qualifier("guestData")
	private List<Integer> guestData;

	@GetMapping
	ResponseEntity<?> calculate(
			@RequestParam(value = "numberOfEconomyRooms") int economyRooms,
			@RequestParam(value = "numberOfPremiumRooms") int premiumRooms) throws Exception {
		
		System.out.println(economyRooms);
		System.out.println(premiumRooms);
		System.out.println(Arrays.toString(guestData.toArray()));
		return null;
	}

}
