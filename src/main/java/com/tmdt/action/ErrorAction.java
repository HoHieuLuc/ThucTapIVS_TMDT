package com.tmdt.action;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import com.tmdt.errors.CustomError;

public class ErrorAction {
    @Action(value = "/bad-request", results = { @Result(location = "/index.html") })
    public String badRequest() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        return CustomError.createCustomError("Vui lòng nhập đầy đủ thông tin", 400, response);
    }
}
