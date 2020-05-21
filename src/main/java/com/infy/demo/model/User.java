package com.infy.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class User {
	private String firstName;
	private String lastName;
	@NotEmpty
	@NotNull
	private String emailId;
	@NotEmpty
	@NotNull
	private String password;
	private String phoneNumber;
	private UserType userType;
}
