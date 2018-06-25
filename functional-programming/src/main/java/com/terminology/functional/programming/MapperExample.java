package com.terminology.functional.programming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.terminology.functional.programming.pojo.Employee;

public class MapperExample {


	private static boolean isNotSekahr(String name){
		return !name.equals("sekhar");
	}

	public static void main(String args[]){

		List<String> list = Arrays.asList("raja","sekhar","ramu","devaansh","reddy");

		System.out.println("<< Imperative(traditional) Style >>");

		for(String name : list){
			if(!"sekhar".equals(name)){
				System.out.println(name);
			}
		}
		//map is used transform the object one type to another type.
		//terminal operation used to terminate the stream.

		System.out.println("<< Functional Style >>");

		//UsingAnonymous class implementation

		list.stream().filter(MapperExample::isNotSekahr)
		.map(new Function<String, Employee>() {
			@Override
			public Employee apply(String name) {
				return new Employee(name);
			}
		})
		.forEach(System.out::println);//terminal operation
		
		//////////////////////////////////////////////////////

		List<Employee> empList = list.stream().filter(MapperExample::isNotSekahr)
				.map(Employee::new)
				.collect(Collectors.toList());//terminal operation
		
		////////////////////////////forEach////////////////////
		empList.forEach((emp)-> {
			System.out.println(emp);
		}
				);

		empList.forEach(System.out::println);
	}
}
