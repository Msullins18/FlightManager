package com.infy.demo.model;

import java.time.LocalDate;

import lombok.Data;
@Data
public class SearchFlights {
    private Integer airportId;
    private String destination;
    private LocalDate date;
    private Integer numberOfTickets;
}
