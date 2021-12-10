package com.console.test;

import java.time.LocalDate;

public class DateTest {
    public static void main(String[] args){  
		
		// Take a date
	    LocalDate date = LocalDate.now();
		// Displaying date
		System.out.println("Date : "+date);
		// Add 2 months to the date
		LocalDate newDate = date.plusMonths(2); 
		System.out.println("New Date : "+newDate);
	}
}
