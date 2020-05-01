package com.infy.demo.service;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.demo.dao.TravellerDAO;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Traveller;
import com.infy.demo.utility.HashingUtility;
import com.infy.demo.validator.EmailValidator;
@Service (value = "travellerService")
@Transactional
public class TravellerServiceImpl implements TravellerService {
	
	@Autowired
	TravellerDAO travellerDAO;
	
	@Override
	public Traveller loginTraveller(Traveller traveller) throws Exception{
		// TODO Auto-generated method stub
		Traveller travellerFromDAO = null;
		String emailId = traveller.getEmailId().toLowerCase();
		String passwordFromDAO = travellerDAO.getPasswordOfTraveller(emailId);
		if(passwordFromDAO != null)
		{
			String hashedPassword = HashingUtility.getHash(traveller.getPassword());
			if(hashedPassword.equals(passwordFromDAO))
			{
				travellerFromDAO = travellerDAO.getTravellerByEmailId(emailId);
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
		return travellerFromDAO;
	}

	@Override
	public String registerTraveller(Traveller traveller) throws Exception{
		// TODO Auto-generated method stub
		String emailId = traveller.getEmailId().toLowerCase();
		String registered = null;
		Boolean available = travellerDAO.checkAvailabilityOfEmailId(emailId);
		if(available)
		{
			String passwordToDB = HashingUtility.getHash(traveller.getPassword());
			traveller.setEmailId(emailId);
			traveller.setPassword(passwordToDB);
			registered = travellerDAO.registerTraveller(traveller);
		}
		if(registered == null)
		{
			throw new EmailUnavailableException(emailId);
		}
		return registered;
	}

}
