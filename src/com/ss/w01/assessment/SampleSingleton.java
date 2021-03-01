package com.ss.w01.assessment;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SampleSingleton {
	

	private static Connection conn = null;
	
	private static SampleSingleton instance = null;
	
	//fixed getInstance method to either create the single copy of this of this class allowed to be made,
	//or return the copy that as already been made
	public static SampleSingleton getInstance() {
		if(instance == null) instance = new SampleSingleton();
			
		return instance;
	}
	
	//parameter accepts big decimal type, which doesnt allow arithmetic operators like primitive number values,
	//converted type of x from int to BigDecimal to match parameter, then changed arithmetic operator "*"
	//to the BigDecimal method multiply() in order to multiply 2 BigDecimals
	//also included throws SQLException to catch possible exception
	public static void databaseQuery(BigDecimal input) throws SQLException{
		conn = DriverManager.getConnection("url of database");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select id from table");
		BigDecimal x = new BigDecimal(0);
		while(rs.next()) {
			x = rs.getBigDecimal(1).multiply(input);
		}
	}
}