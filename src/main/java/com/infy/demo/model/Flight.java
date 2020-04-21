package com.infy.demo.model;

public class Flight {
	private String flightId;
	private String flightNo;
	private Airport arrival;
	private Integer baseFare;
	private Integer tax;
	private Integer totalSeats;
	private boolean isAvaiable;
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

	public Airport getArrival() {
		return arrival;
	}
	public void setArrival(Airport arrival) {
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
	public Integer getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	public boolean isAvaiable() {
		return isAvaiable;
	}
	public void setAvaiable(boolean isAvaiable) {
		this.isAvaiable = isAvaiable;
	}
	
	
	

}
