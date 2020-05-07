package com.infy.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
import lombok.Data;
@Data
@Entity
@Table(name = "Traveller")
public class TravellerEntity {
	@Id
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
}
