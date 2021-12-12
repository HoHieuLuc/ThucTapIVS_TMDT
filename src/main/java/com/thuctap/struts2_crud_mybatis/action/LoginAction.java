package com.thuctap.struts2_crud_mybatis.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import mybatis.mapper.AccountMapper;

import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.errors.*;
import com.thuctap.struts2_crud_mybatis.model.Account;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
public class LoginAction extends ActionSupport {
    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

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
            @Result(name = "notLoggedIn", location = "/login.html"),
            @Result(name = "loggedIn", type = "redirect", location = "/welcome"),
    })
    public String viewLogin() {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        System.out.println("hmmmm");
        if (loggedIn == null || !loggedIn) {
            return "notLoggedIn";
        } else {
            // nếu đã đăng nhập rồi thì sẽ tự chuyển sang trang welcome
            // localhost:8080/.../login => welcome
            return "loggedIn";
        }
    }

    @Action(value = "/loginAction", results = {
            @Result(name = "loggedIn", type = "redirect", location = "/welcome"),
            @Result(name = "success", location = "/login.html"),
    })
    public String login() throws IOException {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        // nếu đã đăng nhập rồi thì sẽ tự chuyển sang trang welcome
        // localhost:8080/.../loginAction => welcome
        if (loggedIn != null && loggedIn) {
            return "loggedIn";
        }

        SqlSession sqlSession = sqlSessionFactory.openSession();
        PrintWriter printWriter = response.getWriter();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.getByUsername(username);

        //System.out.println("yes");
        if (account != null) {
            if (BCrypt.checkpw(password, account.getPassword())) {
                System.out.println("Login success");
                session.setAttribute("loggedIn", true);
                session.setAttribute("username", username);
                return "loggedIn";
            }
        }
        sqlSession.close();

        return CustomError.createCustomError("Sai tài khoản hoặc mật khẩu", 401, response, printWriter);
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
