package com.terminology.functional.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.terminology.functional.programming.pojo.Employee;

public class FlatMapExample {
	

	public static void main(String args[]){

		//map is used transform object one type to another type.
		//terminal operation used to terminate the stream.
		//flatmap is used to convert one type of stream object to another stream object
		
		List<Employee> empList = Arrays.asList(
				new Employee("raja", 30, Arrays.asList("2")),
				new Employee("sekhar", 29, Arrays.asList("4")),
				new Employee("reddy", 31, Arrays.asList("5")),
				new Employee("vadlathala", 32, Arrays.asList("7","8")),
				new Employee("devaansh", 27, Arrays.asList("10","9"))
				);

		
		Optional<Object> optional = empList.stream().map(emp->emp.getEmpIds().stream())
				.flatMap(new Function<Stream<String>, Stream<?>>() {
					@Override
					public Stream<?> apply(Stream<String> stringStream) {
						return stringStream.filter(empId -> empId.equals("2"));
					}
				}).findAny();
				
		optional.ifPresent(System.out::println);
		
	}
}
