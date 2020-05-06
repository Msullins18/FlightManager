package com.infy.demo.service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.demo.dao.AdminDAO;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Admin;
import com.infy.demo.model.Airport;
import com.infy.demo.utility.HashingUtility;
import com.infy.demo.validator.EmailValidator;
@Service (value = "adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Admin loginAdmin(Admin admin) throws Exception{
		// TODO Auto-generated method stub
		Admin adminFromDAO = null;
		String emailId = admin.getEmailId().toLowerCase();
		String passwordFromDAO = adminDAO.getPasswordOfAdmin(emailId);
		if(passwordFromDAO != null)
		{
			String hashedPassword = HashingUtility.getHash(admin.getPassword());
			if(hashedPassword.equals(passwordFromDAO))
			{
				adminFromDAO = adminDAO.getAdminByEmailId(emailId);
			}
			else
			{
				throw new InvalidCredentialsException();
			}
			
		}
		else
		{
			throw new UserNotFoundException(emailId);
		}
		return adminFromDAO;
	}

	@Override
	public String registerAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		String emailId = admin.getEmailId().toLowerCase();
		String registered = null;
		Boolean available = adminDAO.checkAvailabilityOfEmailId(emailId);
		if(available)
		{
			String passwordToDB = HashingUtility.getHash(admin.getPassword());
			admin.setEmailId(emailId);
			admin.setPassword(passwordToDB);
			registered = adminDAO.registerAdmin(admin);
		}
		if(registered == null)
		{
			throw new EmailUnavailableException(emailId);
		}
		return registered;
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
		Integer id = adminDAO.deleteAirport(airportId);
		if(id==null){
			throw new Exception("AdminService.AIRPORT_NOT_EXISTS");
		}
		return adminDAO.deleteAirport(airportId);
	}

	@Override
	public List<Airport> getAirports() throws Exception {
		// TODO Auto-generated method stub
		List<Airport> airports = adminDAO.getAirports();
		if(airports==null){
			throw new Exception("Sorry no Airports are available at the moment");
		}
		return airports;
	}
	
	

}
