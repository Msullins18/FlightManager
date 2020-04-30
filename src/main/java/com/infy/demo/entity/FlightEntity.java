package com.infy.demo.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	    public Integer getAirportId() {
			return airportId;
		}
		public void setAirportId(Integer airportId) {
			this.airportId = airportId;
		}
		public Double getFlightFare() {
	        return flightFare;
	    }
	    public void setFlightFare(Double flightFare) {
	        this.flightFare = flightFare;
	    }
	    public Double getFlightTax() {
	        return flightTax;
	    }
	    public void setFlightTax(Double flightTax) {
	        this.flightTax = flightTax;
	    }
	    public Integer getFlightId() {
	        return flightId;
	    }
	    public void setFlightId(Integer flightId) {
	        this.flightId = flightId;
	    }
	    public String getFlightType() {
	        return flightType;
	    }
	    public void setFlightType(String flightType) {
	        this.flightType = flightType;
	    }
	    public int getFlightSize() {
	        return flightSize;
	    }
	    public void setFlightSize(int flightSize) {
	        this.flightSize = flightSize;
	    }
	    public int getSeatsAvailable() {
	        return seatsAvailable;
	    }
	    public void setSeatsAvailable(int seatsAvailable) {
	        this.seatsAvailable = seatsAvailable;
	    }
	    public LocalDate getDateOfDeparture() {
	        return dateOfDeparture;
	    }
	    public void setDateOfDeparture(LocalDate dateOfDeparture) {
	        this.dateOfDeparture = dateOfDeparture;
	    }
	    public LocalDate getDateOfArrival() {
	        return dateOfArrival;
	    }
	    public void setDateOfArrival(LocalDate dateOfArrival) {
	        this.dateOfArrival = dateOfArrival;
	    }
	    public String getDestination() {
	        return destination;
	    }
	    public void setDestination(String destination) {
	        this.destination = destination;
	    } 

}
