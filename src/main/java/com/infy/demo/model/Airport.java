package com.infy.demo.model;

import java.util.List;

public class Airport {
	private Integer airportId;
	private String name;
	private String city;
	private List<Flight> flights;
	public Integer getAirportId() {
		return airportId;
	}
	public void setAirportId(Integer airId) {
		this.airportId = airId;
	}
	public String getAirportName() {
		return name;
	}
	public void setAirportName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	
}



