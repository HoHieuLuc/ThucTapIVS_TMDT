package com.console.test;

import java.time.LocalDate;
import java.sql.Date;

public class SqlDateConversion {
    public static void main(String[] args){  
		
		// Take a date
	    LocalDate date = LocalDate.now();
		// Displaying date
		System.out.println("Date : "+date);
		// Add 2 months to the date
		LocalDate newDate = date.plusMonths(2); 
		System.out.println("New Date : "+newDate);
		//Convert to SQL Date format
		Date date_sql = Date.valueOf(newDate);
		System.out.println("SQL Date is" + date_sql.toString());
	}
}
