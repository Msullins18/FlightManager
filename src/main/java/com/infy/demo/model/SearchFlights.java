package com.infy.demo.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SearchFlights {
	private Integer airportId;
	private String destination;
	private LocalDate date;
	private Integer numberOfTickets;
}
