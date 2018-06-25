package com.terminology.functional.programming;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperations {

	/*A stream represents a sequence of elements and supports different kind of operations 
	to perform computations upon collections/elements
	Stream operations are either intermediate or terminal. 
	Intermediate operations return a stream so we can chain multiple intermediate operations without using semicolons. 
	Terminal operations are either void or return a non-stream result.
	
	Streams can be created from various data sources, especially collections. 
	Lists and Sets support new methods stream() and parallelStream() to either create a sequential or a parallel stream. 
	Parallel streams are capable of operating on multiple threads
	*/
	
	public static void main (String args[]){
		Arrays.asList("7", "3", "4","6")
	    .stream()
	    .findFirst()
	    .ifPresent(System.out::println);
		
		System.out.println("****************************************************");
		
		Stream.of("a1", "a2", "a3")
	    .map(s -> s.substring(1))
	    .mapToInt(Integer::parseInt)
	    .max()
	    .ifPresent(System.out::println);
		System.out.println("****************************************************");
		//Primitive streams can be transformed to object streams via mapToObj():
		IntStream.range(1, 4)
	    .mapToObj(i -> "a" + i)
	    .forEach(System.out::println);
		System.out.println("****************************************************");
		//the stream of doubles is first mapped to an int stream and than mapped to an object stream of strings:
		
		Stream.of(1.0, 2.0, 3.0)
	    .mapToInt(Double::intValue)
	    .mapToObj(i -> "a" + i)
	    .forEach(System.out::println);
		System.out.println("****************************************************");
		//An important characteristic of intermediate operations is laziness. 
		//Look at below sample where a terminal operation is missing:
		
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .filter(s -> {
	        System.out.println("filter: " + s);
	        return true;
	    });
		System.out.println("****************************************************");
		//Let's extend the above example by the terminal operation forEach:
		
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .filter(s -> {
	        System.out.println("filter: " + s);
	        return true;
	    })
	    .forEach(s -> System.out.println("forEach: " + s));
		System.out.println("****************************************************");
		
		/*The operation anyMatch returns true as soon as the predicate applies to the given input element. 
		This is true for the second element passed "A2". Due to the vertical execution of the stream chain, map has only to be executed twice in this case. 
		So instead of mapping all elements of the stream, map will be called as few as possible.*/
		Stream.of("d2", "a2", "b1", "b3", "c")
		.map(s-> s.toUpperCase())
		.anyMatch(s->{
			System.out.println(s);
			return s.startsWith("A");
		});
		System.out.println("****************************************************");
		
		Stream.of("d2", "a2", "b1", "b3", "c")
		.map(s-> s.toUpperCase())
		.filter(s->{
			System.out.println(s);
			return s.startsWith("A");
		}).forEach(System.out::println);
		
		System.out.println("****************************************************");
		/*As you might have guessed both map and filter are called five times for every string in the underlying collection whereas forEach is only called once.
		We can greatly reduce the actual number of executions if we change the order of the operations, moving filter to the beginning of the chain:*/
		Stream.of("d2", "a2", "b1", "b3", "c")
		.filter(s->{
			System.out.println(s);
			return s.startsWith("a");
		})
		.map(s->{ 
			System.out.println(s);
			return s.toUpperCase();
		})
		.forEach(s->System.out.println(s));
		
		System.out.println("****************************************************");
		
		/*Sorting is a special kind of intermediate operation. 
		It's a so called stateful operation since in order to sort a collection of elements you have to maintain state during ordering.*/
		
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .sorted((s1, s2) -> {
	        System.out.printf("sort: %s; %s\n", s1, s2);
	        return s1.compareTo(s2);
	    })
	    .filter(s -> {
	        System.out.println("filter: " + s);
	        return s.startsWith("a");
	    })
	    .map(s -> {
	        System.out.println("map: " + s);
	        return s.toUpperCase();
	    })
	    .forEach(s -> System.out.println("forEach: " + s));
		
		System.out.println("****************************************************");
		
		Stream.of("d2", "a2", "b1", "b3", "c","a1")
		.filter(s -> {
	        System.out.println("filter: " + s);
	        return s.startsWith("a");
	    })
	    .sorted((s1, s2) -> {
	        System.out.printf("sort: %s; %s\n", s1, s2);
	        return s1.compareTo(s2);
	    })
	    .map(s -> {
	        System.out.println("map: " + s);
	        return s.toUpperCase();
	    })
	    .forEach(s -> System.out.println("forEach: " + s));
		
		System.out.println("****************************************************");
		//Java 8 streams cannot be reused. As soon as you call any terminal operation the stream is closed:
		
		Stream<String> stream =
			    Stream.of("d2", "a2", "b1", "b3", "c")
			        .filter(s -> s.startsWith("a"));

			stream.anyMatch(s -> true);    // ok
			stream.noneMatch(s -> true);   // java.lang.IllegalStateException: stream has already been operated upon or closed
			
		/*To overcome this limitation we have to to create a new stream chain for every intermediate operation we want to execute
		 * e.g. we could create a stream supplier to construct a new stream with all intermediate operations already set up
		 *Supplier is a functional interface with only one abstract method get() */	
		Supplier<Stream<String>> streamSupplier =
				    () -> Stream.of("d2", "a2", "b1", "b3", "c")
				            .filter(s -> s.startsWith("a"));

				streamSupplier.get().anyMatch(s -> true);   // ok
				streamSupplier.get().noneMatch(s -> true);  // ok
	}
}
