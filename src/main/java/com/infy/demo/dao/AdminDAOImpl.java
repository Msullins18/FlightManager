package com.infy.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	public Optional<String> getPasswordOfAdmin(String emailId) {
		Optional<String> password = Optional.empty();
		AdminEntity adminEntity = entityManager.find(AdminEntity.class, emailId);
		
		if (password.isPresent()){
			password = Optional.of(adminEntity.getPassword());
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
		Optional<AdminEntity> adminEntity = Optional.of(entityManager.find(AdminEntity.class, emailId));

		if(!adminEntity.isPresent())
		{
			available = true;
		}
		
		return available;
	}

	@Override
	public Integer addAirport(Airport airport) {
		// TODO Auto-generated method stub
		AirportEntity newAirport = new AirportEntity();
		
		newAirport.setCity(airport.getCity());
		newAirport.setAirportName(airport.getAirportName());
		List<FlightEntity> listOfFlights = new ArrayList<>();
		
		newAirport.setFlightEntities(listOfFlights);
		
		entityManager.persist(newAirport);
		
		return newAirport.getAirportId();
	}

	@Override
	public Integer deleteAirport(Integer airportId) {
		// TODO Auto-generated method stub
		Integer id = null;
<<<<<<< HEAD
		Optional<AirportEntity> airport = Optional.of(entityManager.find(AirportEntity.class, airportId));
		if(airport.isPresent()){
			AirportEntity ae = airport.get();
			ae.setFlightEntities(null);
			id = ae.getAirportId();
			entityManager.remove(ae);
=======
		AirportEntity airport = entityManager.find(AirportEntity.class, airportId);
		airport.setFlightEntities(null);
		Optional<AirportEntity> checkNull = Optional.ofNullable(airport);
		if(checkNull.isPresent()){
			id = airport.getAirportId();
			entityManager.remove(airport);
>>>>>>> 10bdc8289b8da825c3bfeed7f245566657d738f1
		}
		return id;
	}

	@Override
	public List<Airport> getAirports() {
		// TODO Auto-generated method stub\
		List<Airport> airports = null;

		String dft = "SELECT a FROM AirportEntity a";
		Query query = entityManager.createQuery(dft);
		List<AirportEntity> airportList = query.getResultList();
		airports = new ArrayList<Airport>();
		Optional<List<AirportEntity>> checkNull = Optional.ofNullable(airportList);
		if(checkNull.isPresent()){
			for(AirportEntity a : airportList){
				Airport airport = new Airport();
				airport.setAirportId(a.getAirportId());
				airport.setAirportName(a.getAirportName());
				airport.setCity(a.getCity());
				airport.setFlights(new ArrayList<Flight>());
				airports.add(airport);
			}
		}
		return airports;

	}
	
	

}
