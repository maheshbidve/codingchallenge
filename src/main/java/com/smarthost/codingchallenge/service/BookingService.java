package com.smarthost.codingchallenge.service;

import static java.util.stream.Collectors.partitioningBy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.smarthost.codingchallenge.constants.Constants;
import com.smarthost.codingchallenge.model.BookingResponse;

@Service
public class BookingService {

	@Autowired
	@Qualifier("guestData")
	private List<Double> guestData;

	public BookingResponse calculate(int economyRooms, int premiumRooms) {
		Map<Boolean, List<Double>> map = guestData.stream()
				.collect(partitioningBy(bid -> bid >= Constants.PREMIUM_ROOM_CUT_OFF));
		List<Double> premiumGuests = map.get(true);
		List<Double> economyGuests = map.get(false);

		// calculate premium number of rooms that can be allocated
		int premiumRoomsAllocated = Math.min(premiumRooms, premiumGuests.size());

		// calculate number of premium rooms left (if any)
		int premiumRoomsLeft = Math.max(premiumRooms - premiumRoomsAllocated, 0);

		// calculate economy number of rooms that can be allocated
		int extraGuestsForEconomy = Math.max(economyGuests.size() - economyRooms, 0);

		// calculate the number of possible upgrades
		int numberOfUpgrades = Math.min(premiumRoomsLeft, extraGuestsForEconomy);

		// calculate regular economy rooms
		int regularEconomyRooms = Math.min(economyGuests.size() - numberOfUpgrades, economyRooms);

		//calculate premium room income amount
		Double premiumRoomsAmount = premiumGuests.stream().limit(premiumRoomsAllocated).mapToDouble(Double::doubleValue)
				.sum();

		//calculate upgraded room income amount
		Double upgradedRoomsAmount = economyGuests.stream().limit(numberOfUpgrades).mapToDouble(Double::doubleValue)
				.sum();

		//calculate economy room income amount
		Double economyRoomsAmount = economyGuests.stream().skip(numberOfUpgrades).limit(regularEconomyRooms)
				.mapToDouble(Double::doubleValue).sum();

		return new BookingResponse(regularEconomyRooms, premiumRoomsAllocated + numberOfUpgrades, economyRoomsAmount,
				premiumRoomsAmount + upgradedRoomsAmount);
	}
}
