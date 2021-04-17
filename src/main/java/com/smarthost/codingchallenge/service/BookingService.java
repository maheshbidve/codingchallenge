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
	private List<Integer> guestData;

	public BookingResponse calculate(int economyRooms, int premiumRooms) {
		Map<Boolean, List<Integer>> map = guestData.stream()
				.collect(partitioningBy(bid -> bid >= Constants.PREMIUM_ROOM_CUT_OFF));
		List<Integer> premiumGuests = map.get(true);
		List<Integer> economyGuests = map.get(false);

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
		int premiumRoomsAmount = premiumGuests.stream().limit(premiumRoomsAllocated).mapToInt(Integer::intValue)
				.sum();

		//calculate upgraded room income amount
		int upgradedRoomsAmount = economyGuests.stream().limit(numberOfUpgrades).mapToInt(Integer::intValue)
				.sum();

		//calculate economy room income amount
		int economyRoomsAmount = economyGuests.stream().skip(numberOfUpgrades).limit(regularEconomyRooms)
				.mapToInt(Integer::intValue).sum();

		return new BookingResponse(regularEconomyRooms, premiumRoomsAllocated + numberOfUpgrades, economyRoomsAmount,
				premiumRoomsAmount + upgradedRoomsAmount);
	}
}
