package com.infy.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.demo.entity.AdminEntity;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.model.Admin;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public String registerAdmin(Admin admin) {
		// TODO Auto-generated method stub
		AdminEntity adminEntity = new AdminEntity();
		
		adminEntity.setEmailId(admin.getEmailId());
		adminEntity.setFirstName(admin.getFirstName());
		adminEntity.setLastName(admin.getLastName());
		adminEntity.setPassword(admin.getPassword());
		adminEntity.setPhoneNumber(admin.getPhoneNumber());
		
		entityManager.persist(adminEntity);
		
		return adminEntity.getEmailId();
	}

	@Override
	public String getPasswordOfAdmin(String emailId) {
		String password = null;
		AdminEntity adminEntity = entityManager.find(AdminEntity.class, emailId);
		
		if (adminEntity!=null){
			password = adminEntity.getPassword();
		}
		
		return password;
	}

	@Override
	public Admin getAdminByEmailId(String emailId) {
		// TODO Auto-generated method stub
		AdminEntity adminEntity = entityManager.find(AdminEntity.class, emailId);
		Admin admin = new Admin();
		
		admin.setEmailId(adminEntity.getEmailId());
		admin.setFirstName(adminEntity.getFirstName());
		admin.setLastName(adminEntity.getLastName());
		admin.setPassword(adminEntity.getPassword());
		admin.setPhoneNumber(adminEntity.getPhoneNumber());
		
		return admin;
	}

	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {
		// TODO Auto-generated method stub
		Boolean available = false;
		AdminEntity adminEntity = entityManager.find(AdminEntity.class, emailId);

		if(adminEntity == null)
		{
			available = true;
		}
		
		return available;
	}

	@Override
	public Integer addAirport(Airport airport) {
		// TODO Auto-generated method stub
		AirportEntity newAirport = new AirportEntity();
		newAirport.setAirportId(airport.getAirportId());
		newAirport.setCity(airport.getCity());
		newAirport.setAirportName(airport.getAirportName());
		List<FlightEntity> flightList = new ArrayList<>();
		FlightEntity f1 = null;
		for(Flight flight : airport.getFlights()){
			f1=new FlightEntity();
			if(flight!=null){
				f1.setDateOfArrival(flight.getDateOfArrival());
				f1.setDateOfDeparture(flight.getDateOfDeparture());
				f1.setDestination(flight.getDestination());
				f1.setFlightFare(flight.getFlightFare());
				f1.setFlightSize(flight.getFlightSize());
				f1.setFlightTax(f1.getFlightTax());
				f1.setFlightType(flight.getFlightType());
				flightList.add(f1);
			}
		}
		newAirport.setFlightEntities(flightList);
		entityManager.persist(newAirport);
		return newAirport.getAirportId();
	}

	@Override
	public Integer deleteAirport(Integer airportId) {
		// TODO Auto-generated method stub
		AirportEntity airport = entityManager.find(AirportEntity.class, airportId);
		if(airport!=null){
			entityManager.remove(airport);
		}
		return airportId;
	}
	
	

}
