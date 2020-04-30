package com.infy.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String>{

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return (email != null) &&
				(email.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+"));
	}
}
