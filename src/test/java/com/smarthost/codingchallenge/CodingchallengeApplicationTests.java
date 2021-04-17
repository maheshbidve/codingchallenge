package com.smarthost.codingchallenge;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smarthost.codingchallenge.controller.BookingController;

@SpringBootTest
class CodingchallengeApplicationTests {

	@Autowired
	BookingController controller;
	
	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

}
