package com.infy.demo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Airport {
	private Integer airportId;
	private String name;
	private String city;
	private List<Flight> flights;
	
}



