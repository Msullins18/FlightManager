package com.infy.demo.validator;

public class EmailValidator {
	
	public static void validateEmail(String emailId) throws Exception{
		if( !validateEmailId(emailId)) {
			throw new Exception("SellerValidator.INVALID_EMAIL_FORMAT_FOR_LOGIN");
		}
			
	}
	
	public static Boolean validateEmailId(String emailId){
		Boolean flag = false;
		if(emailId.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+"))
			flag = true;
		return flag;
	}
}
