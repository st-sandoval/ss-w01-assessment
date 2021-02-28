package com.ss.w01.assessment;

public class NoNegativesException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoNegativesException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
