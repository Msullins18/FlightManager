package com.infy.demo.dao;

import com.infy.demo.model.Admin;

public interface AdminDAO {
	public abstract String getPasswordOfAdmin(String emailId);
	public abstract Admin getAdminByEmailId(String emailId);
	public abstract String registerAdmin(Admin admin);
	public Boolean checkAvailabilityOfEmailId(String emailId);
}
