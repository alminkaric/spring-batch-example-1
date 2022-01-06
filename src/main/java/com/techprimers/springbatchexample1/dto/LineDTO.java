package com.techprimers.springbatchexample1.dto;

import javax.validation.constraints.NotNull;

import com.techprimers.springbatchexample1.validation.DateValidation;

public class LineDTO {

	@NotNull  Integer empId;
	@NotNull  String firstName;
	@NotNull  String lastName;
	@NotNull  String deptId;
	@DateValidation @NotNull private String salaryDate;
	@NotNull private Float salary;

	public LineDTO(Integer empId, String firstName, String lastName, String deptId, String salaryDate, Float salary) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.deptId = deptId;
		this.salaryDate = salaryDate;
		this.salary = salary;
	}
	
	public LineDTO() {
		
	}

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
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "LineDTO [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", deptId=" + deptId
				+ ", salaryDate=" + salaryDate + ", salary=" + salary + "]";
	}
}
