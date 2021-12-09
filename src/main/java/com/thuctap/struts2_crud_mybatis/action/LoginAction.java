package com.thuctap.struts2_crud_mybatis.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import com.thuctap.struts2_crud_mybatis.errors.*;
public class LoginAction extends ActionSupport{
    //Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();


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

    @Action(value = "/login",results = { 
        @Result(name = "success",location = "/index.html"),
        @Result(name = "input",location = "/login.html")
    })
    public String login() throws IOException {
        //Phương thức lấy giá trị session
        String loggedUserName = (String) session.getAttribute("userName");
        //Thử in ra giá trị của session
        System.out.println(loggedUserName);
        if (loggedUserName != null && loggedUserName.equals("admin")) {
            return SUCCESS; // return welcome page
        }
         
        // if no userName stored in the session,
        // check the entered userName and password
        if (username != null && username.equals("admin")
                && password != null && password.equals("admin")) {
             
            // add userName to the session
            session.setAttribute("userName", username);
             
            return SUCCESS; // return welcome page
        }   
         
        // in other cases, return login page
        // Tạo một danh sách các lỗi bằng json thông qua Class ValidateError
                ArrayList<String> messages = new ArrayList<String>();
                messages.add("Username không hợp lệ" + username);
                messages.add("Password không hợp lệ" +password);
                PrintWriter printWriter = response.getWriter();
                 return ValidateError.push(messages, 400, response, printWriter);
                
    }
   
}
