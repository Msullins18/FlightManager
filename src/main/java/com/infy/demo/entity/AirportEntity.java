package com.infy.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="AIRPORT")
public class AirportEntity {
	@Id
	@Column(name="AIRPORT_ID")
  	@SequenceGenerator(name = "airportSeqGen", sequenceName = "airport_seq", allocationSize = 1)
  	@GeneratedValue(generator = "airportSeqGen")
	private Integer airportId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CITY")
	private String city;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="AIRPORT_ID")
	private List<FlightEntity> flightEntities;

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

	public List<FlightEntity> getFlightEntities() {
		return flightEntities;
	}

	public void setFlightEntities(List<FlightEntity> flightEntities) {
		this.flightEntities = flightEntities;
	}
	
	
}
