package com.infy.demo.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.demo.entity.AdminEntity;
import com.infy.demo.model.Admin;
@Repository(value = "adminDAO")
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

}
