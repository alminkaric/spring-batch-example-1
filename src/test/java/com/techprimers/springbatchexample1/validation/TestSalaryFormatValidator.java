package com.techprimers.springbatchexample1.validation;

import static com.techprimers.springbatchexample1.validation.utils.TestUtils.assertConstaraintViolations;
import static com.techprimers.springbatchexample1.validation.utils.TestUtils.assertNoConstaraintViolations;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * This is the test for {@link SalaryFormatValidator}. The only valid format is float (with dot).
 * Any other format is invalid.
 * 
 * <p><b>Example: </b> 2500.50 is valid, while 2500.50 is invalid
 * 
 * @author akaric
 */
public class TestSalaryFormatValidator {

	private final SalaryFormatValidator salaryFormatValidator = new SalaryFormatValidator();
	
	@Test
	public void shouldReturnTrueWhenValid() {
		// This test contains only valid formats
		Arrays.asList(
				"2.00",
				"2500.00",
				"11111.000",
				"",
				null) // null and empty string should be valid case, @NotNull should take care of this
		.forEach(this::assertSalaryFormatValid);
	}

	@Test
	public void shouldReturnFalseWhenInvalid() {
		// This test contains only invalid formats
		Arrays.asList(
				"  ",
				"xxxx",
				"2000",
				"200,33")
		.forEach(this::assertSalaryFormatNotValid);
	}
	
	@Test
	public void testAnntationInClass() {
		final TestInnerClassForSalaryFormat testInstance = new TestInnerClassForSalaryFormat();
		
		// first case when salary is null, should be valid
		assertNoConstaraintViolations(testInstance);

		// adding empty string as salary should be ok, the instance should be valid
		testInstance.setSalary("");
		assertNoConstaraintViolations(testInstance);

		// adding valid salary, the instance should be valid
		testInstance.setSalary("2500.40");
		assertNoConstaraintViolations(testInstance);

		// adding invalid salary, the instance should be invalid
		testInstance.setSalary("2000");
		assertConstaraintViolations(testInstance);
	}
	
	private static final class TestInnerClassForSalaryFormat {
		@SalaryValidation private String salary;

		public void setSalary(String salary) {
			this.salary = salary;
		}
	}

	private void assertSalaryFormatValid(String salaryString) {
		assertTrue(
				"Salary format is invalid, given salary=" + salaryString,
				salaryFormatValidator.isValid(salaryString, null)
				);
	}

	private void assertSalaryFormatNotValid(String salaryString) {
		assertFalse(
				"Salary format is valid, given salary=" + salaryString + ", expected invalid format in this test",
				salaryFormatValidator.isValid(salaryString, null)
				);
	}
}
