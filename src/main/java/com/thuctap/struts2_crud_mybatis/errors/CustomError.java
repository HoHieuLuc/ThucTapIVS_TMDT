package com.thuctap.struts2_crud_mybatis.errors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class CustomError {
    private CustomError() {
    }

    public static String createCustomError(
            String message,
            int errorCode,
            HttpServletResponse response,
            PrintWriter printWriter) throws IOException {
        System.out.println("bruh");
        response.setStatus(errorCode);
        printWriter.print("{\"message\":\"" + message + "\"}");
        printWriter.flush();
        printWriter.close();
        return "success";
    }
}
