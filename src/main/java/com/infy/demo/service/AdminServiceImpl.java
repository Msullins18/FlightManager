package com.infy.demo.service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.demo.dao.AdminDAO;
import com.infy.demo.entity.AirportEntity;
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
				throw new Exception("AdminService.INVALID_CREDENTIALS");
			}
			
		}
		else
		{
			throw new Exception("AdminService.INVALID_CREDENTIALS");
		}
		return adminFromDAO;
	}

	@Override
	public String registerAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		String emailId = admin.getEmailId().toLowerCase();
		EmailValidator.validateEmail(emailId);
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
			throw new Exception("AdminService.EMAIL_TAKEN");
		}
		return registered;
	}
	

	@Override
	public Integer addAirport(Airport airport) throws Exception {
		// TODO Auto-generated method stub
		AirportEntity airportEntity = entityManager.find(AirportEntity.class, airport.getAirportId());
		if(airportEntity!=null){
			throw new Exception("AdminService.AIRPORT_EXISTS");
		}
		return adminDAO.addAirport(airport);
	}

	@Override
	public Integer deleteAirport(Integer airportId) throws Exception {
		// TODO Auto-generated method stub
		AirportEntity airportEntity = entityManager.find(AirportEntity.class, airportId);
		if(airportEntity==null){
			throw new Exception("AdminService.AIRPORT_NOT_EXISTS");
		}
		return adminDAO.deleteAirport(airportId);
	}
	
	

}
