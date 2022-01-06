package com.techprimers.springbatchexample1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.techprimers.springbatchexample1.model.Department;

public class DepartmantFormatValidator implements ConstraintValidator<DepartmantValidation, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Department.valueFromId(value) != null;
	}

}
