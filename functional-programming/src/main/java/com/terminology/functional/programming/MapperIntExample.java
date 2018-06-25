package com.terminology.functional.programming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.terminology.functional.programming.pojo.Employee;

public class MapperIntExample {


	private static boolean isNotSekahr(String name){
		return !name.equals("sekhar");
	}

	public static void main(String args[]){

		List<String> list = Arrays.asList("raja","sekhar","ramu","devaansh","reddy");


		//map is used transform object one type to another type.
		//terminal operation used to terminate the stream.

		System.out.println("<< Functional Style >>");

		List<Employee> empList = list.stream().filter(MapperIntExample::isNotSekahr)
				.map(Employee::new)
				.collect(Collectors.toList());//terminal operation
		
		int sum = empList.stream().mapToInt(Employee::getAge).sum();
		
		System.out.println("------------------------------sum >>>"+sum);
		
		
	}
}
