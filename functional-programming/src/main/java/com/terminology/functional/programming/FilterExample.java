package com.terminology.functional.programming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterExample {

	
	/*A stream represents a sequence of elements and supports different kind of operations 
	to perform computations upon collections/elements
	Stream operations are either intermediate or terminal. 
	Intermediate operations return a stream so we can chain multiple intermediate operations without using semicolons. 
	Terminal operations are either void or return a non-stream result.
	
	Streams can be created from various data sources, especially collections. 
	Lists and Sets support new methods stream() and parallelStream() to either create a sequential or a parallel stream. 
	Parallel streams are capable of operating on multiple threads
	*/
	
	private static boolean isNotSekahr(String name){
		return !name.equals("sekhar");
	}
	
	public static void main(String args[]){

		List<String> list = Arrays.asList("raja","sekhar","java","devaansh","terminology");
		//imperative style
		for(String name : list){
			if(!"sekhar".equals(name)){
				System.out.println(name);
			}
		}
		//functional style
		/*System.out.println("Functional Style..");
		list.stream().filter((name)-> {
			return !name.equals("sekhar");
		})
		.forEach((name)->System.out.println(name));*/
		
		System.out.println("Functional Style..");
		list.stream().filter(FilterExample::isNotSekahr)
		.forEach(System.out::println);
		
		
	}
}
