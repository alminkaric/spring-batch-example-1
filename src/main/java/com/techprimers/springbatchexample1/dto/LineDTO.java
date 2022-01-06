package com.techprimers.springbatchexample1.dto;

import javax.validation.constraints.NotNull;

import com.techprimers.springbatchexample1.validation.DateValidation;
import com.techprimers.springbatchexample1.validation.DepartmantValidation;
import com.techprimers.springbatchexample1.validation.SalaryValidation;

public class LineDTO {

	@NotNull  Integer empId;
	@NotNull  String firstName;
	@NotNull  String lastName;
	@DepartmantValidation @NotNull  String deptId;
	@DateValidation @NotNull private String salaryDate;
	@SalaryValidation @NotNull private String salary;

	public LineDTO(
			final Integer empId, 
			final String firstName, 
			final String lastName, 
			final String deptId, 
			final String salaryDate, 
			final String salary) {
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.deptId = deptId;
		this.salaryDate = salaryDate;
		this.salary = salary;
	}
	
	public LineDTO() { }

	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getSalaryDate() {
		return salaryDate;
	}
	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "LineDTO [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", deptId=" + deptId
				+ ", salaryDate=" + salaryDate + ", salary=" + salary + "]";
	}
}
