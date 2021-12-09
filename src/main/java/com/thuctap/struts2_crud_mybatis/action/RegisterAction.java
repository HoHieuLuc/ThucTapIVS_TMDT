package com.thuctap.struts2_crud_mybatis.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import com.thuctap.struts2_crud_mybatis.errors.*;
public class RegisterAction extends ActionSupport{
    //Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();

    private String username,password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public  boolean isValid() {
        String password_regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,14}$";
        String username_regex ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,14}$";
        return Pattern.matches(username_regex, username) && Pattern.matches(password_regex, password);
    }

    @Action(value = "/register",results = { 
        @Result(name = "success",location = "/register_success.html"),
        @Result(name = "input",location = "/login.html")
    })
    public String register() throws IOException {
        if (isValid()){
            System.out.println(isValid());
            return SUCCESS;
        } else
                {
                    ArrayList<String> messages = new ArrayList<String>();
                messages.add("Username Tối thiểu 6 ký tự và tối đa 14 kí tự, ít nhất một chữ cái và một số, không có kí tự khoảng trắng ");
                messages.add("Password Tối thiểu 8 và tối đa 14 ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt, không có khoảng trắng ");
                PrintWriter printWriter = response.getWriter();
                 return ValidateError.push(messages, 400, response, printWriter);
                }
    }
   
}
