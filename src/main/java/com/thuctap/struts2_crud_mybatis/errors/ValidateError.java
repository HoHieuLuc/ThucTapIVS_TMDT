package com.thuctap.struts2_crud_mybatis.errors;


import java.io.PrintWriter;
import java.util.ArrayList;


import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ValidateError {
    private ValidateError() {
    }

    public static String push(
        ArrayList<String> message,
        int errorCode,
        HttpServletResponse response,
        PrintWriter printWriter
    )
    {
        response.setStatus(errorCode);
        Gson gson = new Gson();
        printWriter.println(gson.toJson(message));
        printWriter.flush();
        printWriter.close();
        return "input";
    }
    

}
