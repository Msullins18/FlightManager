package com.infy.demo.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
import lombok.Data;
@Data
@Entity
@Table (name="FLIGHT")
public class FlightEntity {
	 @Id
	    @Column(name="FLIGHT_ID")
	  	@SequenceGenerator(name = "flightSeqGen", sequenceName = "flight_seq", allocationSize = 1)
	  	@GeneratedValue(generator = "flightSeqGen")
	    private Integer flightId;
	    @Column(name="FLIGHT_TYPE")
	    private String flightType;
	    @Column(name="FLIGHT_SIZE")
	    private int flightSize;
	    @Column(name="SEATS_AVAILABLE")
	    private int seatsAvailable;
	    @Column(name="DATE_OF_DEPARTURE")
	    private LocalDate dateOfDeparture;
	    @Column(name="DATE_OF_ARRIVAL")
	    private LocalDate dateOfArrival;
	    @Column(name="DESTINATION")
	    private String destination;
	    @Column(name="FLIGHT_FARE")
	    private Double flightFare;
	    @Column(name="FLIGHT_TAX")
	    private Double flightTax;
	    @Column(name="AIRPORT_ID")
	    private Integer airportId;
}
