package com.infy.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.demo.dao.AdminDAO;

import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
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
		Optional<Integer> id = Optional.of(adminDAO.deleteAirport(airportId));
		if (!id.isPresent()) {
			throw new Exception("AdminService.AIRPORT_NOT_EXISTS");
		}
		return id.get();
	}

	@Override
	public List<Airport> getAirports() throws Exception {
		// TODO Auto-generated method stub
		Optional<List<Airport>> airports = Optional.of(adminDAO.getAirports());
		if (!airports.isPresent()) {
			throw new Exception("Sorry no Airports are available at the moment");
		}
		return airports.get();
	}

}
