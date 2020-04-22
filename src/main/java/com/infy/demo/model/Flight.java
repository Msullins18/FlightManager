package com.infy.demo.model;

public class Flight {
	private String flightId;
	private String flightNo;
	private String origin;
	private String destination;
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
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}


}
