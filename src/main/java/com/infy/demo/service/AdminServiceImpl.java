package com.infy.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.demo.dao.AdminDAO;
<<<<<<< HEAD

=======
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.exceptions.AirportNotFoundException;
>>>>>>> 10bdc8289b8da825c3bfeed7f245566657d738f1
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.NoAirportsAvailableException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Admin;
import com.infy.demo.model.Airport;
import com.infy.demo.utility.HashingUtility;

@Service(value = "adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Autowired
	EntityManager entityManager;

	@Override
	public Admin loginAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		Admin adminFromDAO = null;
		String emailId = admin.getEmailId().toLowerCase();
		Optional<String> passwordFromDAO = adminDAO.getPasswordOfAdmin(emailId);
		if (passwordFromDAO.isPresent()) {
			String hashedPassword = HashingUtility.getHash(admin.getPassword());
			if (hashedPassword.equals(passwordFromDAO.get())) {
				adminFromDAO = adminDAO.getAdminByEmailId(emailId);
			} else {
				throw new InvalidCredentialsException();
			}

		} else {
			throw new UserNotFoundException(emailId);
		}
		return adminFromDAO;
	}

	@Override
	public String registerAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		String emailId = admin.getEmailId().toLowerCase();
		Optional<String> registered = Optional.empty();
		Boolean available = adminDAO.checkAvailabilityOfEmailId(emailId);
		if (available) {
			String passwordToDB = HashingUtility.getHash(admin.getPassword());
			admin.setEmailId(emailId);
			admin.setPassword(passwordToDB);
			registered = Optional.of(adminDAO.registerAdmin(admin));
		}
		if (!registered.isPresent()) {
			throw new EmailUnavailableException(emailId);
		}
		return registered.get();
	}

	@Override
	public Integer addAirport(Airport airport) throws Exception {
		// TODO Auto-generated method stub
		Integer id = adminDAO.addAirport(airport);
		return id;
	}

	@Override
	public Integer deleteAirport(Integer airportId) throws Exception {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		Optional<Integer> id = Optional.of(adminDAO.deleteAirport(airportId));
		if (!id.isPresent()) {
			throw new Exception("AdminService.AIRPORT_NOT_EXISTS");
=======
		Integer id = adminDAO.deleteAirport(airportId);
		if(id==null){
		//	throw new Exception("AdminService.AIRPORT_NOT_EXISTS");
			throw new AirportNotFoundException(airportId);
>>>>>>> 10bdc8289b8da825c3bfeed7f245566657d738f1
		}
		return id.get();
	}

	@Override
	public List<Airport> getAirports() throws Exception {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		Optional<List<Airport>> airports = Optional.of(adminDAO.getAirports());
		if (!airports.isPresent()) {
			throw new Exception("Sorry no Airports are available at the moment");
=======
		List<Airport> airports = adminDAO.getAirports();
		if(airports==null){
		//	throw new Exception("Sorry no Airports are available at the moment");
			throw new NoAirportsAvailableException();
>>>>>>> 10bdc8289b8da825c3bfeed7f245566657d738f1
		}
		return airports.get();
	}

}
