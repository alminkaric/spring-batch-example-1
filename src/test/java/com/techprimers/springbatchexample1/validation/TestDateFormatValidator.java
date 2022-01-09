package com.techprimers.springbatchexample1.validation;

import static com.techprimers.springbatchexample1.validation.utils.TestUtils.assertConstaraintViolations;
import static com.techprimers.springbatchexample1.validation.utils.TestUtils.assertNoConstaraintViolations;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * This is the test for {@link DateFormatValidator}. Only valid format is "yyyy-MM-dd". Any other format is invalid
 * 
 * <p><b>Example: </b> 2021-05-05 is valid, while 04.03.2021 is invalid
 * 
 * @author akaric
 */
public final class TestDateFormatValidator {

	private final DateFormatValidator dateFormatValidator = new DateFormatValidator();
	
	@Test
	public void shouldReturnTrueWhenValid() {
		// This test contains only valid formats
		Arrays.asList(
				"2012-08-30",
				"2020-04-01",
				"2021-01-01",
				"2020-12-31",
				"",
				null) // null and empty string should be valid case
		.forEach(this::assertDateFormatValid);
	}
	
	
	@Test
	public void shouldRetrunFalseWhenInvalid() {
		// This test contains only invalid formats
		Arrays.asList(
				"21.08.2021", 
				"01/01/2021", 
				"2021/03/03", 
				"31-08-2021")
		.forEach(this::assertDateFormatInvalid);
	}
	
	@Test
	public void testAnnotationInClass() {
		final TestInnerClassForDateValidator testInstance = new TestInnerClassForDateValidator();
		
		// first case when date is null, should be valid
		assertNoConstaraintViolations(testInstance);

		// adding empty string as date should be ok, the instance should be valid
		testInstance.setDate("");
		assertNoConstaraintViolations(testInstance);

		// adding valid date, the instance should be valid
		testInstance.setDate("2021-08-30");
		assertNoConstaraintViolations(testInstance);

		// adding invalid date, the instance should be invalid
		testInstance.setDate("01.01.2021"); // valid format is "yyyy-MM-dd"
		assertConstaraintViolations(testInstance);
	}
	
	private static final class TestInnerClassForDateValidator {
		@DateValidation private String date;

		public void setDate(String date) {
			this.date = date;
		}
	}

	private void assertDateFormatValid(final String dateString) {
		assertTrue(
				"Date format is not valid, given date=" + dateString + " valid format is 'yyyy-MM-dd'",
				dateFormatValidator.isValid(dateString, null)
				);
	}

	private void assertDateFormatInvalid(final String dateString) {
		assertFalse(
				"Date format is valid, given date=" + dateString + " is valid, expected invalid format for this test",
				dateFormatValidator.isValid(dateString, null)
				);
	}
}