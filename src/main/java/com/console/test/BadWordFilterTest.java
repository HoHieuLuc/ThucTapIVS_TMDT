

package com.console.test;
import com.tmdt.utilities.BadWordFilter;

public class BadWordFilterTest {
	
	public static void main(String[] args) throws InterruptedException {
		String input="cumming ikuiku";
		String output = BadWordFilter.getCensoredText(input);
		System.out.println(output);

	}

}
