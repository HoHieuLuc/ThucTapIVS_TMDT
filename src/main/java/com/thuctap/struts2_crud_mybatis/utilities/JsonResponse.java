package com.thuctap.struts2_crud_mybatis.utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonResponse {
    private JsonResponse() {
    }

    public static String createJsonResponse(
            Map<String, Object> map,
            int statusCode,
            HttpServletResponse response
            ) throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(statusCode);

        Gson gson = new Gson();
        String jsonString = gson.toJson(map);
        
        printWriter.print(jsonString);
        printWriter.flush();
        printWriter.close();
        return "success";
    }
}
