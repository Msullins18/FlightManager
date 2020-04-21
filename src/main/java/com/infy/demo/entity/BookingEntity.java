package com.infy.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.infy.demo.model.Flight;

@Entity
@Table(name="")
public class BookingEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer bookingId;
	private LocalDate travelDate;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="flight_id")
	private FlightEntity flightEntity;
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDate getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
	public FlightEntity getFlightEntity() {
		return flightEntity;
	}
	public void setFlightEntity(FlightEntity flightEntity) {
		this.flightEntity = flightEntity;
	}

	
}
