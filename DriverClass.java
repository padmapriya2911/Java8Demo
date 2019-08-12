package com.java8.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Clock;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class DriverClass {

	/*This interface allows only one Abstract method in it and could not
	 * be extended to another SAM Interface.Purpose of this feature is to 
	 * have Functional Programming in Java 
	*/
	
	@FunctionalInterface 
		public interface InterDemo{
		String printOutLoud(String hey);
		// Default method   
	    default void printDefault(){  
	        System.out.println("Luck favours the One who work on his wildest impossible dreams");   
	    }  
	}
	public static int printOutLoud(int a,int b){
		return a+b;
	}
	
    public static void main(String args[]){
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	biFunctionDemo(br);
    	lambdaAndOptionalDemo(br);
    	embeddedJSDemo();  
    	clockDemoWithZoneId();
    	streamDemo();
    }

	private static void streamDemo() {
		List<Employee> employeesList = new ArrayList<Employee>();  
         //Adding employees  
         employeesList.add(new Employee(1,"Allan",25000f));  
         employeesList.add(new Employee(2,"Niralya",30000f));  
         employeesList.add(new Employee(3,"Raghuvaran",28000f));  
         employeesList.add(new Employee(4,"Mugen ",28000f));  
         employeesList.add(new Employee(5,"Prakash",90000f));  
         List<Float> employeesSalaryList =   
                 employeesList.stream()  
                             .map(x->x.salary)         // fetching salary
                             .collect(Collectors.toList());  // collecting as list  
         System.out.println("As List"+employeesSalaryList); 
         
         Set<String> employeesSalaryList1 =   
                 employeesList.stream()  
                             .map(x->x.name)         // fetching name
                             .collect(Collectors.toSet());  // collecting as list  
         System.out.println("As Set"+employeesSalaryList1); 
         Double employeesSalaryListAverage =   
                 employeesList.stream()  
                 .collect(Collectors.averagingDouble(p->p.salary));  
         System.out.println("Average Salary : "+employeesSalaryListAverage);
	}

	private static void clockDemoWithZoneId() {
		Clock clock = Clock.system(ZoneId.of("Canada/Central"));
		System.out.println(clock.instant());
	}

	private static void embeddedJSDemo() {
		
		ScriptEngine ee = new ScriptEngineManager().getEngineByName("Nashorn");  
		System.out.println("------embeddedJSDemo -------"+"\n"+"Please Wait while Hello.js file getsloaded");
         // Reading Nashorn file   
         try {
			ee.eval(new FileReader("hello.js"));
		} catch (FileNotFoundException | ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void lambdaAndOptionalDemo(BufferedReader br) {
		String input=null;
		System.out.println("------Lambda and Optional Demo Funtion-------"+"\n"+"Enter Input For the Function");
    	
    	 
    	try {
			input=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//Lambda expression 
    	InterDemo helloWorld = (String name) -> { return "Hello " + name; }; 
    	//Optional Class to check nullable value, Could also be used in Restful Service as an optional Parameter
    	Optional<String> checkNull = Optional.ofNullable(input);  
        if(checkNull.isPresent())
        System.out.println(helloWorld.printOutLoud(input));
        //Calling Default method
        System.out.println("---Printing Values From Default Interface Method---");
        helloWorld.printDefault();
	}

	private static void biFunctionDemo(BufferedReader br) {
		//Static Reference to an Actual Method in the Class
		BiFunction<Integer, Integer, Integer>adder = DriverClass::printOutLoud;  
		int input1 = 0,input2 = 0;
		System.out.println("------BiFunction Demo -------"+"\n"+"Enter Input For the Function,2 numbers");
		try {
		 input1=Integer.parseInt(br.readLine());
	     input2=Integer.parseInt(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    	int result = adder.apply(input1,input2);
    	System.out.println(result);
	}
	
	
}
