package com.infy.demo.model;

import java.time.LocalDate;

public class Booking {
	private LocalDate travelDate;
	private Flight flight;
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public LocalDate getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

}
