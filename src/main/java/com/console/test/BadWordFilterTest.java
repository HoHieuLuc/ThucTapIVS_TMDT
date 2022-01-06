

package com.console.test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.tmdt.utilities.BadWordFilter;
import com.tmdt.utilities.ProjectPath;

public class BadWordFilterTest {
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		String input="vếu aaa";
		input.getBytes(Charset.forName("utf-8"));
		String hi = new String("vếu aa".getBytes(Charset.forName("utf-8")));
		//input = new String(input.getBytes("UTF-8"), "ISO-1");
		//String output = BadWordFilter.getCensoredText(input);
		//System.out.println(output);
		System.out.println(input);
		System.out.println("Test");

	}

}
