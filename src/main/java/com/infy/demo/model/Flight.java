package com.infy.demo.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Flight {
	private Integer flightId;
    private String flightType;
    private int flightSize;
    private int seatsAvailable;
    private LocalDate dateOfArrival;
    private LocalDate dateOfDeparture;
    private String destination;
    private Integer airportId;
    private double flightFare;
    private double flightTax;
}
