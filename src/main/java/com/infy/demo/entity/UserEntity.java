package com.infy.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import com.infy.demo.model.UserType;
import com.infy.demo.validator.Email;

import lombok.Data;
@Data
@Entity
@Table(name = "USER_TABLE")
public class UserEntity {
	@Id
	@Email
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "USER_TYPE")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

}
