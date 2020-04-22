package com.infy.demo.service;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.demo.dao.AdminDAO;
import com.infy.demo.model.Admin;
import com.infy.demo.utility.HashingUtility;
@Service (value = "adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

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

}
