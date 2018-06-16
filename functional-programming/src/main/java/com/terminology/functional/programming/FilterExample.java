package com.terminology.functional.programming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterExample {

	public static void main(String args[]){

		List<String> list = Arrays.asList("raja","sekhar","java","devaansh","terminology");
		//imperative style
		for(String name : list){
			if(!"sekhar".equals(name)){
				System.out.println(name);
			}
		}
		//functional style
		System.out.println("Functional Style..");
		list.stream().filter((name)-> {
			return !name.equals("sekhar");
		})
		.forEach((name)->System.out.println(name));
	}
}
