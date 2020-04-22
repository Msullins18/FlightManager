package com.infy.demo.service;

import com.infy.demo.model.Admin;

public interface AdminService {
	public abstract Admin loginAdmin(Admin admin) throws Exception;
	public abstract String registerAdmin(Admin admin) throws Exception;
}
