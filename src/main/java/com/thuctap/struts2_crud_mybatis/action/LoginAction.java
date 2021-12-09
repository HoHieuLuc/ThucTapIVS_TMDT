package com.thuctap.struts2_crud_mybatis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import com.thuctap.struts2_crud_mybatis.errors.*;

public class LoginAction extends ActionSupport {
    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private String username, password;

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

    @Action(value = "/login", results = {
            @Result(name = "success", location = "/login.html"),
            @Result(name = "loggedIn", type = "redirect", location = "/student/index"),
    })
    public String viewLogin() {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn) {
            return "success";
        } else {
            // nếu đã đăng nhập rồi thì sẽ tự chuyển sang trang student/index
            // localhost:8080/.../login => student/index
            return "loggedIn";
        }
    }

    @Action(value = "/loginAction", results = {
            @Result(name = "success", type = "redirect", location = "/student/index"),
            @Result(name = "input", location = "/login.html")
    })
    public String login() throws IOException {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        // nếu đã đăng nhập rồi thì sẽ tự chuyển sang trang student/index
        // localhost:8080/.../loginAction => student/index
        if (loggedIn != null && loggedIn) {
            return SUCCESS;
        }

        // if no userName stored in the session,
        // check the entered userName and password
        if (username != null && username.equals("admin")
                && password != null && password.equals("admin")) {
            session.setAttribute("loggedIn", true);
            // add userName to the session
            session.setAttribute("userName", username);

            return SUCCESS; // return welcome page
        }
        System.out.println("Login failed");

        // in other cases, return login page
        // Tạo một danh sách các lỗi bằng json thông qua Class ValidateError
        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Username không hợp lệ" + username);
        messages.add("Password không hợp lệ" + password);
        PrintWriter printWriter = response.getWriter();
        return ValidateError.push(messages, 400, response, printWriter);
    }

    @Action(value = "/logout", results = {
            @Result(name = "success", location = "/login", type = "redirect")
    })
    public String logout() {
        System.out.println("logout");
        session.removeAttribute("loggedIn");
        session.removeAttribute("userName");
        return SUCCESS;
    }
}
