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
            HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setStatus(errorCode);
        response.setContentType("application/json;charset=utf-8");
        printWriter.print("{\"message\":\"" + message + "\"}");
        printWriter.flush();
        printWriter.close();
        return "success";
    }
}
