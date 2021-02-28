package com.ss.w01.assessment;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.function.Consumer;



public class MainClass {
	
	String message;
	
	public MainClass(String message){
		this.message = message;
	}

	public static void main(String []args) {
		Integer[][] intArgs = new Integer[args.length/2][2];
		HashMap<Integer, Ilambdas> lambdas = new HashMap<>();
		ArrayList lambdasArray = new ArrayList();
		Integer count = 0;
		
//		for(String x : args) {
//			System.out.println("enhanced for args: " + x);
//			if(count == 0 || count == args.length) {
//				count++;
//				continue;	
//			}else{
//				count++;
//				String[] partial = x.split(" ");
//				argsMap.put(Integer.parseInt(partial[0]), Integer.parseInt(partial[1]));
//			}
//			
//			
//		}
		
//		for(int i = 1; i > args.length; i+=2) {
//			argsMap.put(Integer.parseInt(args[i]), Integer.parseInt(args[1 + 1]));
//		};
		
		
		
		for(int i = 1; i < args.length; i+=2) {
			intArgs[count][i% 2 - 1] = Integer.parseInt(args[i]);
			intArgs[count][i% 2] = Integer.parseInt(args[i + 1]);
			
			count++;
			System.out.println("args[i]: " + args[i] + " .. args[i + 1]" + args[i+1]);
			
		}
		
//		System.out.println("argsMap: " + argsMap);
//		
//		argsMap.forEach((key, value) -> {
//			System.out.println("key: " + key + " .. Value: " + value);
//		});
//		
		
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
			
			while(forward != 0) {
				Integer digit = forward % 10;
				
				reverse = reverse * 10 + digit;
				
				forward /= 10;
			}

			if(x.equals(reverse)) return "PALINDROME";
			
			return "NOT PALINDROME";
		};
		
		//add lambda functions to lambda hashmap so they may be recalled by integer key
		lambdas.put(1, isOdd);
		lambdas.put(2, isPrime);
		lambdas.put(3, isPalindrome);
		
		Arrays.stream(intArgs).forEach(x -> {
			System.out.println(x[1]);
			
			Ilambdas func = lambdas.get(x[0]);
			func.metho(x[1]);
			System.out.println(func.metho(x[1]));
			
			
		});
		
		List<Integer[]> intCollection = Arrays.asList(intArgs);
//		List<Integer> oddList = intCollection.stream().filter(x -> isOdd.isOdd(x)).collect(Collectors.toList());
//		intCollection.stream().map(x -> isOdd.isOdd(x)).forEach(x -> {System.out.println(x);});
		
//		oddList.forEach(x -> {
//			System.out.println("oddList: " + x);
//		});
//		
	}
	
	
	
	public String printMessage() {
		System.out.print(this.message);
		return this.message;
	}
}


class Streamer {
	Streamer (int x){
		System.out.println(x);
	}
}