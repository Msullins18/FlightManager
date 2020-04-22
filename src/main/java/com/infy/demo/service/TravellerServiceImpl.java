package com.infy.demo.service;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.demo.dao.TravellerDAO;
import com.infy.demo.model.Traveller;
import com.infy.demo.utility.HashingUtility;
@Service (value = "travellerService")
@Transactional
public class TravellerServiceImpl implements TravellerService {
	
	@Autowired
	TravellerDAO travellerDAO;
	
	@Override
	public Traveller loginTraveller(Traveller traveller) throws Exception {
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
				throw new Exception("TravellerService.INVALID_CREDENTIALS");
			}
			
		}
		else
		{
			throw new Exception("TravellerService.INVALID_CREDENTIALS");
		}
		return travellerFromDAO;
	}

	@Override
	public String registerTraveller(Traveller traveller) throws Exception {
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
		return registered;
	}

}
