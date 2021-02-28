package com.ss.w01.assessment;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PartOneTests {

	String message = "Hello World!";
	MainClass main = new MainClass(message);
	
	@Test
	public void testPrintMessage() {
		assertEquals(message, main.printMessage());
	}
	
	@Test
	public void testIsOdd() {
		Ilambdas isOdd = x -> {
			if( x % 2 == 1) return "ODD";

			return "EVEN";
		};
		assertEquals("ODD", isOdd.run(5));
		assertEquals("EVEN", isOdd.run(0));
	}
	
	@Test
	public void testIsPrime() {
		Ilambdas isPrime = x -> {
			if(x <= 1) return "COMPOSITE";

			if(x == 2) return "PRIME";

			if(x % 2 == 0) return "COMPOSITE";

			for(int i = 3; i <= Math.sqrt(x); i+=2) {
				if(x % i == 0) return "COMPOSITE";
			}

			return "PRIME";
		};
		assertEquals("PRIME", isPrime.run(5));
		assertEquals("COMPOSITE", isPrime.run(0));
		assertEquals("COMPOSITE", isPrime.run(-4));
	}
	
	@Test
	public void testIsPalindrome() {
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
		
		assertEquals("PALINDROME", isPalindrome.run(1551));
		assertEquals("PALINDROME", isPalindrome.run(0));
		assertEquals("NOT PALINDROME", isPalindrome.run(-4));
		assertEquals("PALINDROME", isPalindrome.run(567898765));
		assertEquals("NOT PALINDROME", isPalindrome.run(-123221));
	}
	
}
