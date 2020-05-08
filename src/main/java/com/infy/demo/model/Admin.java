package com.infy.demo.model;

import lombok.Getter;
import lombok.Setter;

import lombok.Data;

@Data
public class Admin {
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String phoneNumber;
}
