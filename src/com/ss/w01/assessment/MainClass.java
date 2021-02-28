package com.ss.w01.assessment;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainClass {
	
	String message;
	
	public MainClass(String message){
		this.message = message;
	}

	public static void main(String []args) {
		IisOdd isOdd = x -> {
			if( x % 2 == 1) return true;

			return false;
		};
		
		IisPrime isPrime = x -> {
			if(x <= 1) return false;

			if(x == 2) return true;

			if(x % 2 == 0) return false;

			for(int i = 3; i <= Math.sqrt(x); i+=2) {
				if(x % i == 0) return false;
			}

			return true;
		};
		
		IisPalindrome isPalindrome = x -> {
			
			return false;
		};
		
		for(String temp: args) {
			System.out.println(temp);
		}		
		
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