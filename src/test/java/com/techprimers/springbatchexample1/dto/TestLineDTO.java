package com.techprimers.springbatchexample1.dto;

import org.junit.Test;

import static com.techprimers.springbatchexample1.validation.utils.TestUtils.assertConstaraintViolations;
import static com.techprimers.springbatchexample1.validation.utils.TestUtils.assertNoConstaraintViolations;

/**
 * This is the test for {@link LineDTO} class. Here we are going the check mostly validations
 * 
 * @author akaric
 *
 */
public class TestLineDTO {

	@Test
	public void shouldNotHaveErrorsWhenEverythingValid() {
		// the examples are from users.csv

		// valid lines
		assertValidLine("10,Joe,Doe,1001,2021-11-30,5000.00");
		assertValidLine("13,Mike,Smith,1002,2021-07-31,5100.50");
		assertValidLine("10,Joe,Doe,1001,2021-08-31,5200.00");
		assertValidLine("13,Mike,Smith,1002,2021-09-30,5400.00");
		assertValidLine("11,Bill,Bones,1003,2021-07-31,4450.50");
		assertValidLine("13,Mike,Smith,1002,2021-10-31,5555.00");
		assertValidLine("12,John,Smith,1003,2021-07-31,4350.00");
		assertValidLine("11,Bill,Bones,1003,2021-10-31,4500.00");
		assertValidLine("12,John,Smith,1003,2021-08-31,4800.00");
		assertValidLine("10,Joe,Doe,1001,2021-10-31,5400.00");
		assertValidLine("12,John,Smith,1003,2021-09-30,4600.00");
		assertValidLine("13,Mike,Smith,1002,2021-08-31,5900.00");
		assertValidLine("12,John,Smith,1003,2021-11-30,4400.00");
		assertValidLine("11,Bill,Bones,1003,2021-09-30,4300.00");
		assertValidLine("10,Joe,Doe,1001,2021-09-30,5300.00");
		assertValidLine("11,Bill,Bones,1003,2021-11-30,3800.00");
	}

	@Test
	public void shouldNotHaveErrorsWhenLineIsInvalid() {
		// the examples are from users.csv

		// invalid lines
		assertInvalidLine("12,John,Smith,9999,2021-10-31,4500.00");
		assertInvalidLine("11,Bill,Bones,1003,2021-08-31,4800,00");
		assertInvalidLine("10,Joe,Doe,1001,2021-07-31,");
		assertInvalidLine("13,Mike,Smith,1002,2021/11/31,4800.00");
	}
	
	
	private void assertValidLine(final String line) {
		final String[] columns = line.split(",");
		final LineDTO lineDTO = new LineDTO(
				Integer.valueOf(getDataByColumnIndexOrNull(columns, 0)), 
				getDataByColumnIndexOrNull(columns, 1), 
				getDataByColumnIndexOrNull(columns, 2), 
				getDataByColumnIndexOrNull(columns, 3), 
				getDataByColumnIndexOrNull(columns, 4), 
				getDataByColumnIndexOrNull(columns, 5));
		
		assertNoConstaraintViolations(lineDTO);
	}

	private void assertInvalidLine(final String line) {
		final String[] columns = line.split(",");
		final LineDTO lineDTO = new LineDTO(
				Integer.valueOf(getDataByColumnIndexOrNull(columns, 0)), 
				getDataByColumnIndexOrNull(columns, 1), 
				getDataByColumnIndexOrNull(columns, 2), 
				getDataByColumnIndexOrNull(columns, 3), 
				getDataByColumnIndexOrNull(columns, 4), 
				getDataByColumnIndexOrNull(columns, 5));
		
		assertConstaraintViolations(lineDTO);
	}
	
	private String getDataByColumnIndexOrNull(final String[] columns, final int index) {
		if(index < columns.length) {
			return columns[index];
		}
		
		return null;
	}
}
