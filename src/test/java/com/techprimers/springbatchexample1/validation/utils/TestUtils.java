package com.techprimers.springbatchexample1.validation.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * TODO: Add java doc
 * @author akaric
 *
 */
public class TestUtils {
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	private TestUtils() {
		// to avoid instantiation
	}

	/**
	 * TODO: Add javadoc
	 * @param instance
	 * @param validator
	 */
	public static final void assertConstaraintViolations(Object instance) {
		final Set<ConstraintViolation<Object>> constrainViolations = validator.validate(instance);
		assertFalse(
				"Constrain violations are expected in this test, but not preset",
				constrainViolations.isEmpty());
	}
	
	/**
	 * TODO: Add javadoc
	 * @param instance
	 * @param validator
	 */
	public static final void assertNoConstaraintViolations(Object instance) {
		final Set<ConstraintViolation<Object>> constrainViolations = validator.validate(instance);
		assertTrue(
				"There are unexpected constraint violations = " + constrainViolations,
				constrainViolations.isEmpty());
	}

}
