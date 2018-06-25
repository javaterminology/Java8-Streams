package com.terminology.functional.programming.pojo;

import java.util.List;

public class Employee {
	
	private String name;

	
	private Integer age=29;
	
	private List<String> empIds;
	
	public Employee(String name) {
		super();
		this.name = name;
	}
	
	public Employee(String name, Integer age, List<String> empIds) {
		super();
		this.name = name;
		this.age = age;
		this.empIds = empIds;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	public List<String> getEmpIds() {
		return empIds;
	}

	public void setEmpIds(List<String> empIds) {
		this.empIds = empIds;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + "]";
	}
	
}
