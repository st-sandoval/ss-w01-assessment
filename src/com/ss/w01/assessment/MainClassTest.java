package com.ss.w01.assessment;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainClassTest {

	String message = "Hello World!";
	MainClass main = new MainClass(message);
	
	@Test
	public void testPrintMessage() {
		assertEquals(message, main.printMessage());
	}
	
}
