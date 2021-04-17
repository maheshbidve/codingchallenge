package com.smarthost.codingchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.smarthost.codingchallenge.model.BookingResponse;

@SpringBootTest(classes = { CodingchallengeApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookingControllerTests {

	@Autowired
	@LocalServerPort
	private int serverPort;
	
	@Value("${host}")
	private String host;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void test_economy_rooms() {
		String consumeUrl = "http://" + host + ":" + serverPort
				+ "v1/bookings?numberOfEconomyRooms=7&numberOfPremiumRooms=2";

		ResponseEntity<BookingResponse> responseEntity = restTemplate.exchange(consumeUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<BookingResponse>() {
				});

		BookingResponse response = responseEntity.getBody();

		assertFalse(Objects.isNull(response));
		assertEquals(response.getOccupiedPremiumRooms(), 2);
		assertEquals(response.getOccupiedEconomyRooms(), 4);
		assertEquals(response.getPremiumRoomsAmount(), 583);
		assertEquals(response.getEconomyRoomsAmount(), 189.99);

	}

	@Test
	public void test_premium_rooms() {
		String consumeUrl = "http://" + host + ":" + serverPort
				+ "v1/bookings?numberOfEconomyRooms=5&numberOfPremiumRooms=7";

		ResponseEntity<BookingResponse> responseEntity = restTemplate.exchange(consumeUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<BookingResponse>() {
				});

		BookingResponse response = responseEntity.getBody();

		assertFalse(Objects.isNull(response));
		assertEquals(response.getOccupiedPremiumRooms(), 6);
		assertEquals(response.getOccupiedEconomyRooms(), 4);
		assertEquals(response.getPremiumRoomsAmount(), 1054);
		assertEquals(response.getEconomyRoomsAmount(), 189.99);

	}

	@Test
	public void test_equls_no_rooms() {
		String consumeUrl = "http://" + host + ":" + serverPort
				+ "v1/bookings?numberOfEconomyRooms=3&numberOfPremiumRooms=3";

		ResponseEntity<BookingResponse> responseEntity = restTemplate.exchange(consumeUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<BookingResponse>() {
				});

		BookingResponse response = responseEntity.getBody();

		assertFalse(Objects.isNull(response));
		assertEquals(response.getOccupiedPremiumRooms(), 3);
		assertEquals(response.getOccupiedEconomyRooms(), 3);
		assertEquals(response.getPremiumRoomsAmount(), 738);
		assertEquals(response.getEconomyRoomsAmount(), 167.99);

	}

	@Test
	public void test_high_no_of_premium_rooms() {
		String consumeUrl = "http://" + host + ":" + serverPort
				+ "v1/bookings?numberOfEconomyRooms=1&numberOfPremiumRooms=30";

		ResponseEntity<BookingResponse> responseEntity = restTemplate.exchange(consumeUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<BookingResponse>() {
				});

		BookingResponse response = responseEntity.getBody();

		assertFalse(Objects.isNull(response));
		assertEquals(response.getOccupiedPremiumRooms(), 9);
		assertEquals(response.getOccupiedEconomyRooms(), 1);
		assertEquals(response.getPremiumRoomsAmount(), 1221.99);
		assertEquals(response.getEconomyRoomsAmount(), 22);

	}

}
