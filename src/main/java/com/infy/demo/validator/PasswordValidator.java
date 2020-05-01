package com.infy.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String>{

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		return (password != null) &&
				(!password.contains(" ")) &&
				(password.matches("^[a-zA-Z0-9_]*$"));
	}
}