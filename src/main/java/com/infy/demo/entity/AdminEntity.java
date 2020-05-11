package com.infy.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infy.demo.validator.Email;
import com.infy.demo.validator.Password;

import lombok.Getter;
import lombok.Setter;


import lombok.Data;
@Data
@Entity
@Table(name = "Admin")
public class AdminEntity {
	@Id
	@Email
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Password
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

}
