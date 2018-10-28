why default(defender methods) and static methods introduced in Java 8 ?
	
Java 8 introduces a new feature “Default Method” , which allows you to add new methods to the interfaces without breaking the existing implementation of interfaces. 
It provides flexibility to allow interface define implementation which will use as default in the situation where a concrete class fails to provide an implementation for that method.
For example Java Compiler not throwing any error even though ArrayList class not providing implementation for default forEach() method of List interface. so the compiler is not forcing to implement forEach() method .

	ArrayList  List Collection  Iterable

Java.lang.Iterable interface with default implementation:
default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
After introducing Default Methods, it seems that interfaces and abstract classes are same. However, they are still different concept in Java 8. for example

Abstract class can hold state of object. It can have constructors and member variables. Whereas interfaces with Java 8 default methods cannot hold state. 
All objects will have three essential features:
State – attributes of the object
public class Employee {
    private String name;
    private int    age;
}
Behaviors – methods
Identity – Unique Identity of each object

Interfaces cannot have constructors and member variables. You should still use Abstract class whenever you think your class can have state or you need to do something in constructor. 
Default method should be used for backward compatibility. Whenever you want to add additional functionality in an existing legacy interface you can use default methods without breaking any existing implementer classes.

Default methods : this capability added for backward compatibility, for example List and Collection interfaces do not have forEach() method declaration ..if such method added in interface it will break the implementation of the interface that means classes which are implementing that interface must implement that method otherwise will get compile time error.
so java8 introduced default method with implementation so compiler will not force classes to implement default methods and if not implementing that method will be used as default implementation for that class. 
if you want can override the behavior of that method in your implementation class.

Java8 mostly used static and default methods in Collection API.
we can not override Object class methods (equals(),notify(), notifyAll(),hashCode(),...) in interface since the compiler will get ambiguity which method needs to be called either Object class or interface method.
public interface ServerInterface {	
default public int hashCode(){
		System.out.println(“calling object class hashCode method…”)
	}
}
A default method cannot override a method from java.lang.Object

But we can override object class methods in our implementation class since our class is subclass for Object class.

 Multiple Inheritance with Interfaces:
if two interfaces having same method signature and both interface are implementing by same class then compiler throws an error. so we need to override that method of interface then compiler will not throw any error.
Example:
public interface Interface1 {
	public default void printMessage(){
		System.out.println("Interface1--->");
	}
}

public interface Interface2 {
	public default void printMessage(){
		System.out.println("Interface1--->"+(new Date()).toString());
	}
}

public class InterfaceImpl implements Interface2,Interface1{
		//error : Duplicate default methods named printMessage with the parameters () and () 			are inherited from the types Interface2 and Interface1
		//override the method then no compiler error
@Override
public void printMessage() {
			Interface1.super.printMessage();//call interface method using super keyword
Interface2.super.printMessage();
}
}
Normally static methods used as helper methods while default methods used as implementation for classes which extends the interface.
can not override Interface static methods but we can define same method signature in implementation class that means we can have same static method signature in implementation class and interface but in case of default method we can override.
interface static  methods called by interface itself as below.
	ex:  Interface.staticmethod()
 static method in jdk  - Map interface Map<Entry>
public static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K,V>> comparingByKey() {
            return (Comparator<Map.Entry<K, V>> & Serializable)
                (c1, c2) -> c1.getKey().compareTo(c2.getKey());
        }
        
        Java 8 Functional Interfaces and Lambda Expressions help us in writing smaller and cleaner code by removing a lot of boiler-plate code.
An interface with exact only one abstract method called as functional interface. functional interface annotated with @FunctionalInterface.
 if we define more than one abstract method then will get compilation error.
java.lang.Runnable with single abstract method run() is a good example of Functional Interface.
One of the major benefits of the functional interface is the ability to use lambda expression to instantiate them.
since functional interface have only one abstract method so lambda expression can easily provide the method implementation. Just we need to pass method arguments and business logic.
In earlier java versions instantiating interfaces with anonymous classes as below.
	Runnable r = new Runnable(){	- anonymous class
	@Override
	public void run(){
		System.out.println("---------------------");
	}
	};  
  //Interface
public interface MyInterface {
	public abstract void testLambda();
}
//instantiating interface with anonymous inner class
MyInterface m1 = new MyInterface() {
         @Override
public void testLambda() {
	System.out.println("testLambda--------");
}
};
m1.testLambda();         

Simplified version of above code using java8 lambda expression as below.
MyInterface m1 = ()-> 
System.out.println("instantiating functional interface using lambda expression ");
m1.testLambda();
 Java 8 introduced a package called java.util.function which contains a bunch of functional interfaces.
if we have only one abstract method inside interface and it will work as Functional Interface even though not annotated with FunctionalInterface.if accidentally another method added then compiler will flag an error.
In Java8 few interfaces have more than one abstract method for example comparator interface have abstract methods like compare () and equals() method 
 In this case compiler not throwing any error since equals method belongs to Object class(implicit member of Object class) and incase if we define default equals() method in interface will get compilation error as we discussed in default methods section. 
 We can define any number of other methods like Default methods, Static methods in Functional Interface.

// Not functional interface because equals is already an implicit member of Object class.
 interface MyInterface { 
	boolean equals(Object obj); 
	//default public equals(Object obj); // A default method cannot override a method from 	java.lang.Object
}
functional interfaces can instantiate using method reference/constructor reference as well.

Returning value from Lambda Expression:
(param)->{
System.out.println();
return returnval;
}
//return Boolean 
(i,j)->{
return i>j
}
We can use lambda expressions only for Functional Interfaces.

Java 8 Method References :
Method references are just short hand notation of lambda expressions.
:: used before the method name to denote Method reference.
The output can only be assigned to functional interfaces.
Syntax – (Class or Instance)::method name.
Method references can be assigned only for functional interfaces.

In a method reference, you place the object (or class) that contains the method before the :: operator and the name of the method after it without arguments.
But you may be thinking:
How it is clearer?
What about arguments?
How can this be a valid expression?
And how to construct a valid method reference...?
Instead of usingAN ANONYMOUS CLASSyou can useA LAMBDA EXPRESSIONAnd if this just calls one method, you can useA METHOD REFERENCE

Example : 
Public Class Person{
static int getAge(){
	//business logic
 	return age;
}
public static void main String(String[] args){
int age = Person::getAge;
}
}

When a lambda expression calls a method on instance or the class of the target then it can be replaced with lambda expression.
	Example: 
	p->System.out.println(p);  Lambda expression
	Replace with method reference - System.out::println

This one useful for increasing simplicity and removing extra code.

Types of Method references:
Static method reference
Instance Method Reference
Constructor Method Reference
Instance method of an Object of Arbitrary Type

Example for Static and Instance Method reference:

@FunctionalInterface
public interface MyInterface1 {
 
public void testMethodRef();//single abstract method

}

public class TestMethodRefClient {
public static void staticMethod(){
System.out.println("executing static method");
}
public void instanceMethod(){
System.out.println("executing instance method");
}

public static void main(String[] args) {
TestMethodRefClient client = new TestMethodRefClient();


MyInterface1 i1 = TestMethodRefClient::staticMethod;
i1.testMethodRef();//calling interface abstract method

MyInterface1 i2 = ()-> client.instanceMethod();
i2.testMethodRef();//calling interface abstract method

}

When to use method reference :
When a Lambda expression is invoking already defined method, you can replace it with reference to that method.
When can not use Method reference :
You can not pass arguments to the method reference.
for example, you can not use method reference for following lambda.
	DemoInterface demo = () -> ReferenceDemo.testMethod(“testMethod");
	DemoInterface demo = ReferenceDemo::testMethod(“testMethod");//will get error

Why forEach is faster than Enhanced for loop and Iterator:
Java 6 introduced a new enhanced for loop to iterate the collection elements.
for (Object o : List){
System.out.println(“---------------------”);
}
In java 5 we used to do iterate elements from collection using Iterator interface.
Iterator<?> it = list.iterator();
 while(it.hasNext()){
 	ObjectType o =it.next();
}

The above both scenario’s are using external iteration to fetch elements of collection ..so here we have to write logic explicitly to handle iteration.
Now Java 8 introduced forEach method in collection interface, it will use internal iteration and only thing we just need to pass an object to that method.
Collection->Iterable
default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

Type Inference
Invoking a method without specifying a type between angle brackets is called type inference.
List<> list = new ArrayList<>();   java 8 

Type Inference is a java compiler ability to look at each method invocation and corresponding method declaration to determine the type of argument (arguments) that make the invocation applicable.
Java provides improved version of type inference in Java 8. we are creating array list by mentioning integer type explicitly at both side. The following approach is used earlier versions of Java.
generics
List<Integer> list = new ArrayList<Integer>(); Java5 & Java6
List<Integer> list = new ArrayList<>();   java 7 

Java 8 added support generalized target type inference in method context, so we can now remove the specific generic parameter type in method invocations.

Example:
package com.java8.inference;
import java.util.HashMap;
import java.util.Map;
public class TestTypeInference {

public void test(Map<String,Double> map){
System.out.println("-------java---------");
}
public static void main(String[] args) {
TestTypeInference ti = new TestTypeInference();
ti.test(new HashMap<>());//java 8 
ti.test(new HashMap<>());//java 7 - compiler error 
ti.test(new HashMap<String,Double>());//java 7
}
}

Closures :
For example we want to create a simple thread that only prints something on the console
int j = 42;
Thread t = new Thread(
  () -> System.out.println("The answer is: " + j)
);
What if we changed the value of “j” while the thread is executing?

there is a chance to modify the value of variable in lambda or anonymous class, so Java8 implemented closures for lambda/anonymous classes.
Lambda expressions (as well as anonymous classes) can only access to the “final” variables of the enclosing scope.

What are Predicates?
Predicate is a functional interface with only one abstract method boolean test(Object).
	@FunctionalInterface
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);

Represents a Boolean valued function which accepts one argument -> test(Object)
we can pass lambda expressions wherever predicate is expected. For example filter() method from Stream interface.

Predicate interface contain default and static methods as well(isEqual(),and(),or(),negate())
Predicates can be used with Lambda Expressions.
Predicate Syntax
Predicate<Type> predicate = <condition>.
Example: Predicate<Person> p = p-> p.getAge()>30;

When to use predicates:
Use predicates when you want to evaluate a condition to filter similar or collection of objects.
The return value will be true or false.
Some Examples for when to use predicates:
Find All Accounts with more than 1000 $ balance
Find orders which are between 1000 $ and 2000 $.

Create  Predicate helper classes so that other classes can reuse them.
Centralize filter logics which are more readable.
Helps in separate domains and business logic.
optimize the re-usability of code and reduce maintenance.
Predicate classes are easy to test and change

