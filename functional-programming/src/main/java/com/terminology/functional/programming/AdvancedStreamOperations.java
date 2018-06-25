package com.terminology.functional.programming;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.terminology.functional.programming.pojo.Person;

public class AdvancedStreamOperations {

	
	public static void main(String[] args) {

	/*Collect is an extremely useful terminal operation to transform the elements of the stream into a different kind of result, e.g. a List, Set or Map.
	 * Collect accepts a Collector which consists of four different operations: a supplier, an accumulator, a combiner and a finisher. 
	 * This sounds super complicated at first, but the good part is Java 8 supports various built-in collectors via the Collectors class.
	 * So for the most common operations you don't have to implement a collector yourself.*/
		List<Person> persons =
			    Arrays.asList(
			        new Person("Max", 18),
			        new Person("Peter", 23),
			        new Person("Raja", 23),
			        new Person("David", 12));
	
		
		Optional<String> s = persons.stream()
		.filter(p->p.getName().startsWith("P"))
		.map(t-> t.getName())
		.findAny();
		//.forEach(person-> System.out.println(person.getName()));
			  
		s.ifPresent(new Consumer<String>() {
			@Override
			public void accept(String str) {
				System.out.println(str);
				
			}
		});
	
	
	
	}

}
