package com.thuctap.struts2_crud_mybatis.action;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import com.thuctap.struts2_crud_mybatis.errors.CustomError;

public class ErrorAction {
    @Action(value = "/bad-request", results = { @Result(location = "/index.html") })
    public String errorAction() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter printWriter = response.getWriter();
        return CustomError.createCustomError("Có lỗi xảy ra", 400, response, printWriter);
    }
}
