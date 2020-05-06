package com.infy.demo.model;

import java.util.List;

import lombok.Data;
@Data
public class Airport {
	private Integer airportId;
	private String airportName;
	private String city;
	private List<Flight> flights;
}



