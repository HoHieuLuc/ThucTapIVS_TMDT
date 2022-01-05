

package com.console.test;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.tmdt.utilities.BadWordFilter;
import com.tmdt.utilities.ProjectPath;

public class BadWordFilterTest {
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		String input="cumming ikuiku";
		FileReader fr = new FileReader(ProjectPath.getPath() + "Word_Filter - Sheet.csv");
		// String output = BadWordFilter.getCensoredText(input);
		// System.out.println(output);

	}

}
