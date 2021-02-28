package com.ss.w01.assessment;

public class Lambdas implements IisOdd, IisPrime, IisPalindrome {

	@Override
	public boolean isPalindrome(int x) {
		if(x == (x % 10)) return true;
		
		return false;
	}

	@Override
	public boolean isPrime(int x) {
		if(x <= 1) return false;

		if(x == 2) return true;

		if(x % 2 == 0) return false;

		for(int i = 3; i <= Math.sqrt(x); i+=2) {
			if(x % i == 0) return false;
		}

		return true;
	}

	@Override
	public boolean isOdd(int x) {
		if( x % 2 == 1) return true;

		return false;
	}

	
}
