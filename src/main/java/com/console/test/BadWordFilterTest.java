

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
		// Đừng chỉnh sửa file CSV bằng Excel, mà hãy chỉnh trực tiếp trong vscode hoặc notepad ++
		String input = new String("vếu aa".getBytes(Charset.forName("utf-8")));
		String output = BadWordFilter.getCensoredText(input);
		System.out.println(output);

	}

}
