package com.techprimers.springbatchexample1.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * TODO: Add javadoc
 * @author akaric
 *
 */
public class DateFormatValidator implements ConstraintValidator<DateValidation, String> {
	public static final DateTimeFormatter LOCALDATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.equals("")) {
			return true;
		}

		try {
			LocalDate.parse(value, LOCALDATE_FORMATTER);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

}
