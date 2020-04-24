package com.infy.demo.model;

import java.time.LocalDate;

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
    public double getFlightFare() {
        return flightFare;
    }
    public void setFlightFare(double flightFare) {
        this.flightFare = flightFare;
    }
    public double getFlightTax() {
        return flightTax;
    }
    public void setFlightTax(double flightTax) {
        this.flightTax = flightTax;
    }
    public Integer getAirportId() {
        return airportId;
    }
    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
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
    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }
    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }
    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }
    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    


}
