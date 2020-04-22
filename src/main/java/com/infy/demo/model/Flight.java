package com.infy.demo.model;

public class Flight {
	private String flightId;
	private String flightNo;
	private String arrival;
	private Integer baseFare;
	private Integer tax;
	private Integer AvailableSeats;
	
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}


	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public Integer getBaseFare() {
		return baseFare;
	}
	public void setBaseFare(Integer baseFare) {
		this.baseFare = baseFare;
	}
	public Integer getTax() {
		return tax;
	}
	public void setTax(Integer tax) {
		this.tax = tax;
	}
	public Integer getAvailableSeats() {
		return AvailableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		AvailableSeats = availableSeats;
	}
//	public Airport getDeparture() {
//		return departure;
//	}
//	public void setDeparture(Airport departure) {
//		this.departure = departure;
//	}

	

}
