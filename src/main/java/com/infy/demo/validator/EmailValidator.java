package com.infy.demo.validator;

import com.infy.demo.exceptions.InvalidEmailException;

public class EmailValidator {
	
	public static void validateEmail(String emailId) throws RuntimeException{
		if( !validateEmailId(emailId)) {
			throw new InvalidEmailException();
		}
			
	}
	
	public static Boolean validateEmailId(String emailId){
		Boolean valid = false;
		if(emailId.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+"))
			valid = true;
		return valid;
	}
}
