package com.smarthost.codingchallenge.model;

public class BookingResponse {

	private int occupiedEconomyRooms;
	private int occupiedPremiumRooms;
	private double economyRoomsAmount;
	private double premiumRoomsAmount;

	public BookingResponse() {
		super();
	}

	public BookingResponse(int occupiedEconomyRooms, int occupiedPremiumRooms, double economyRoomsAmount,
			double premiumRoomsAmount) {
		super();
		this.occupiedEconomyRooms = occupiedEconomyRooms;
		this.occupiedPremiumRooms = occupiedPremiumRooms;
		this.economyRoomsAmount = economyRoomsAmount;
		this.premiumRoomsAmount = premiumRoomsAmount;
	}

	public int getOccupiedEconomyRooms() {
		return occupiedEconomyRooms;
	}

	public void setOccupiedEconomyRooms(int occupiedEconomyRooms) {
		this.occupiedEconomyRooms = occupiedEconomyRooms;
	}

	public int getOccupiedPremiumRooms() {
		return occupiedPremiumRooms;
	}

	public void setOccupiedPremiumRooms(int occupiedPremiumRooms) {
		this.occupiedPremiumRooms = occupiedPremiumRooms;
	}

	public double getEconomyRoomsAmount() {
		return economyRoomsAmount;
	}

	public void setEconomyRoomsAmount(double economyRoomsAmount) {
		this.economyRoomsAmount = economyRoomsAmount;
	}

	public double getPremiumRoomsAmount() {
		return premiumRoomsAmount;
	}

	public void setPremiumRoomsAmount(double premiumRoomsAmount) {
		this.premiumRoomsAmount = premiumRoomsAmount;
	}
}
