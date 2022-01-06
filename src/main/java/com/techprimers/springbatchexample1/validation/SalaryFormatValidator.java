package com.techprimers.springbatchexample1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalaryFormatValidator implements ConstraintValidator<SalaryValidation, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || !value.contains(".")) {
			return false;
		}

		try {
			Float.valueOf(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
