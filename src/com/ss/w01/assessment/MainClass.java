package com.ss.w01.assessment;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.HashMap;

public class MainClass {
	
	String message;
	List<Integer> partTwoArgs = Arrays.asList(55, 4, 95784, 0, 111111, 10, 9);
	List<Integer> partThreeArgs = Arrays.asList(-1, 12345, -485, 0, 1277885, 1, 10);
	List<String> partFourArgs = Arrays.asList("red","rexd","bluex","blue", "xxgreenxx", "xx", "x", "xx red xx", " ", "");
	Integer[] groupSumArray1 = {1, 2, 3, 4};
	Integer[] groupSumArray2 = {1,1,1,1,2,2,3,4,5,5,5};

	
	public MainClass() {};
	
	public MainClass(String message){
		this.message = message;
	}

	public static void main(String []args) {
		
		MainClass app = new MainClass();
		
		try {
			System.out.println("------- Part One -------");
			app.partOne(args);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println();
			System.out.println("------- Part Two -------");
			app.partTwoArgs.forEach(x->{
				if(x < 0) {
					throw new IllegalArgumentException("Part two method does not accept negative numbers.");
				}
			});
			System.out.println(app.partTwoArgs);
			System.out.println(app.partTwo(app.partTwoArgs));
		}catch(NoNegativesException e) {
			e.printStackTrace();
			throw new NoNegativesException("Please remove any negative numbers.", e);
			
		}
		
		try {
			System.out.println();
			System.out.println("------- Part Three -------");
			System.out.println(app.partThreeArgs);
			System.out.println(app.partThree(app.partThreeArgs));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println();
			System.out.println("------- Part Four -------");
			System.out.println(app.partFourArgs);
			System.out.println(app.partFour(app.partFourArgs));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println();
			System.out.println("------- Part Five -------");
			System.out.println(app.groupSumArray1.toString());
			System.out.println(app.partFive(0, app.groupSumArray1, 7));
			System.out.println(app.groupSumArray2.toString());
			System.out.println(app.partFive(0, app.groupSumArray2, 8));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println();
			System.out.println("------- Part 6 -------");
			SampleSingleton singy = new SampleSingleton();
			System.out.println(singy.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void partOne(String [] args) throws ArrayIndexOutOfBoundsException, NumberFormatException {
		//Declare matrix to store args in pairs (inside arrays) parsed as Integers
		Integer[][] intArgs = new Integer[args.length/2][2];
		
		//HashMap to hold lambdas in key value pairs for dynamic invocation/overriding of the metho method from 
		//share parent Ilambdas interface
		HashMap<Integer, Ilambdas> lambdas = new HashMap<>();
		Integer count = 0;
		
		//iterate "lines" of incoming args and copy them into matrix
		for(int i = 1; i < args.length; i+=2) {
			intArgs[count][i% 2 - 1] = Integer.parseInt(args[i]);
			intArgs[count][i% 2] = Integer.parseInt(args[i + 1]);
			count++;
		}
		
		//Override methods from functional classes, all typed as a shared parent interface so that they may be
		//typed the same for compatibility with HashMap
		 Ilambdas isOdd = x -> {
			 
			if( x % 2 == 1) return "ODD";

			return "EVEN";
		};
		
		Ilambdas isPrime = x -> {
			if(x <= 1) return "COMPOSITE";

			if(x == 2) return "PRIME";

			if(x % 2 == 0) return "COMPOSITE";

			for(int i = 3; i <= Math.sqrt(x); i+=2) {
				if(x % i == 0) return "COMPOSITE";
			}

			return "PRIME";
		};
		
		Ilambdas isPalindrome = x -> {
			Integer reverse = 0;
			Integer forward = Integer.valueOf(x);
			if(forward < 0) forward *= -1;
			
			while(forward != 0) {
				Integer digit = forward % 10;
				
				reverse = reverse * 10 + digit;
				
				forward /= 10;
			}

			if(x.equals(reverse)) return "PALINDROME";
			
			return "NOT PALINDROME";
		};
		
		//add lambda functions to lambda HashMap so they may be recalled by integer key
		lambdas.put(1, isOdd);
		lambdas.put(2, isPrime);
		lambdas.put(3, isPalindrome);
		
		//stream used to iterate through matrix of arg pairs and apply corresponding lambdas
		Arrays.stream(intArgs).forEach(x -> {
			System.out.print(x[1] + ":  ");
			
			Ilambdas func = lambdas.get(x[0]);
			System.out.println(func.run(x[1]));
		});
	}
	
	private List<Integer> partTwo(List<Integer> args) throws NoNegativesException{
		//simple lambda instance to modulo the singles digit off an integer
		IsinglesDigit singlesDigit = x -> x % 10; 
		
		//integer list to stream, map stream, collect back into integer list after
		List<Integer> postOpArgs = args.stream().map(x -> singlesDigit.run(x))
			.collect(Collectors.toCollection(ArrayList<Integer>::new));
		
		return postOpArgs;
	}
	
	private List<Integer> partThree(List<Integer> args) {
		
		ItimesTwo timesTwo = x -> x * 2;
		
		List<Integer> postOpArgs = args.stream().map(x -> timesTwo.run(x))
			.collect(Collectors.toCollection(ArrayList<Integer>::new));
		
		return postOpArgs;
	}
	
	private List<String> partFour(List<String> args) {
		IremoveX removeX = x -> {
			
			return x.replaceAll("x", "");
		};
		
		List<String> postOpArgs = args.stream().map(x -> removeX.run(x)).collect(Collectors.toCollection(ArrayList<String>::new));
		return postOpArgs;
	}
	
	private boolean partFive(int start, Integer[] intArray, int target) {

		//traverse through array, incrementing our starting index each iteration 
		//for each iteration we must: use the int we are on, or not use the int we are on
		//break recursion if hit a true return, or if our index counter becomes greater than the length of our array 
		
		if(target == 0) {
			return true;
		}else if(start >= intArray.length) {
			return false;
		}else {
			Integer groupEnd = start;
			while(groupEnd < intArray.length && intArray[groupEnd] == intArray[start]) {
				groupEnd++;
			}
			Integer groupLength = groupEnd - start;
			return partFive(groupEnd, intArray, target) || partFive(groupEnd, intArray, target - intArray[start] -  intArray[start] * groupLength);
		}
		
	}
	

	
	public String printMessage() {
		System.out.print(this.message);
		return this.message;
	}
}