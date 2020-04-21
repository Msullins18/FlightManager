package com.infy.demo.service;

import com.infy.demo.model.Admin;

public interface AdminService {
	public abstract Admin loginAdmin(Admin admin);
	public abstract Boolean registerAdmin(Admin admin);
}
