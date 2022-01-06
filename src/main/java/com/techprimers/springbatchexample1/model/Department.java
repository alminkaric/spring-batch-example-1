package com.techprimers.springbatchexample1.model;

import java.util.HashMap;
import java.util.Map;

public enum Department {
	SALES("1001"),
	IT("1002"),
	HR("1003");
	
	private final String id; 
	private static final Map<String, Department> VALUES_BY_ID = createValuesById();
	
	private Department(String id) {
		this.id = id;
	}
	
	public static Department valueFromId(String id) {
		return VALUES_BY_ID.get(id);
	}
	
	private static Map<String, Department> createValuesById() {
		final Map<String, Department> result = new HashMap<>();
		for (Department department : Department.values()) {
			result.put(department.id, department);
		}
		return result;
	}
}
