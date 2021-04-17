package com.smarthost.codingchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smarthost.codingchallenge.model.BookingResponse;
import com.smarthost.codingchallenge.service.BookingService;

@SpringBootTest
public class BookingTests {
	
	@Autowired
	BookingService service;
	
	@Test
	public void should_return_valid_data_case1() {
		BookingResponse response = service.calculate(3, 3);
		
		assertEquals(response.getOccupiedPremiumRooms(), 3);
		assertEquals(response.getOccupiedEconomyRooms(), 3);
		assertEquals(response.getPremiumRoomsAmount(), 738);
		assertEquals(response.getEconomyRoomsAmount(), 167);
	}
	
	@Test
	public void should_return_valid_data_case2() {
		BookingResponse response = service.calculate(5, 7);
		
		assertEquals(response.getOccupiedPremiumRooms(), 6);
		assertEquals(response.getOccupiedEconomyRooms(), 4);
		assertEquals(response.getPremiumRoomsAmount(), 1054);
		assertEquals(response.getEconomyRoomsAmount(), 189);
	}
	
	@Test
	public void should_return_valid_data_case3() {
		BookingResponse response = service.calculate(7, 2);
		
		assertEquals(response.getOccupiedPremiumRooms(), 2);
		assertEquals(response.getOccupiedEconomyRooms(), 4);
		assertEquals(response.getPremiumRoomsAmount(), 583);
		assertEquals(response.getEconomyRoomsAmount(), 189);
	}

}
