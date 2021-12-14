package com.thuctap.struts2_crud_mybatis.action;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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

    private String username;
    private String password;

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
            @Result(name = "notLoggedIn", location = "/WEB-INF/jsp/login.jsp"),
            @Result(name = "loggedIn", type = "redirect", location = "/"),
    })
    public String viewLogin() {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn) {
            return "notLoggedIn";
        } else {
            // nếu đã đăng nhập rồi thì sẽ tự chuyển sang trang index
            // localhost:8080/.../login => index
            return "loggedIn";
        }
    }

    @Action(value = "/loginAction", results = {
            @Result(name = "loggedIn", type = "redirect", location = "/"),
            @Result(name = "success", location = "/WEB-INF/jsp/login.jsp"),
    })
    public String login() throws IOException {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        // nếu đã đăng nhập rồi thì sẽ tự chuyển sang trang index
        // localhost:8080/.../loginAction => index
        if (loggedIn != null && loggedIn) {
            return "loggedIn";
        }

        SqlSession sqlSession = sqlSessionFactory.openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.getByUsername(username);

        //Lấy ngày hiện tại
        Date today = Date.valueOf(LocalDate.now());
       
        //So sánh thời gian
        // System.out.println(today.compareTo(expiredDate));
        //System.out.println("yes");
        if (account != null) {
            if (BCrypt.checkpw(password, account.getPassword()) && today.compareTo(account.getDateExpired())<=0) {
                session.setAttribute("loggedIn", true);
                session.setAttribute("username", username);
                return "loggedIn";
            }
        }
        sqlSession.close();

        //Tài khoản hợp lệ nhưng lại hết hạn  thì in thông báo cho người dùng biết
        if (account != null) {
            if (today.compareTo(account.getDateExpired())>0){
                return CustomError.createCustomError("Tài khoản đã hết hạn", 401, response);
            } 
        }
        
        //Thông báo sai tài khoản
        return CustomError.createCustomError("Tài khoản sai tên đăng nhập hoặc mật khẩu", 401, response);
        
    }

    @Action(value = "/logout", results = {
            @Result(name = "success", location = "/", type = "redirect")
    })
    public String logout() {
        session.removeAttribute("loggedIn");
        session.removeAttribute("userName");
        return SUCCESS;
    }
}
