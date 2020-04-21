package com.infy.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.infy.demo.dao.AdminDAO;
import com.infy.demo.model.Admin;
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public Admin loginAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Admin adminFromDAO = adminDAO.getAdminByEmailId(admin.getEmailId());
		String passwordFromDAO = adminDAO.getPasswordOfAdmin(admin.getEmailId());
		if(admin.getPassword().matches(passwordFromDAO))
		{
			
		}
		return null;
	}

	@Override
	public Boolean registerAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
