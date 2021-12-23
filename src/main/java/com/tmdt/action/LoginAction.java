package com.tmdt.action;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;

import mybatis.mapper.*;
import com.tmdt.model.*;

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
            @Result(name = "success", location = "/WEB-INF/jsp/login.jsp"),
    }, interceptorRefs = {
            @InterceptorRef("loggedInStack")
    })
    public String viewLogin() {
        return "success";

    }

    @Action(value = "/loginAction", results = {
            @Result(name = "loggedIn", type = "redirect", location = "/"),
            @Result(name = "success", location = "/WEB-INF/jsp/login.jsp"),
    }, interceptorRefs = {
            @InterceptorRef("loggedInStack")
    })
    public String login() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // Mapper lấy tài khoản
        TaiKhoanMapper taiKhoanMapper = sqlSession.getMapper(TaiKhoanMapper.class);
        // Mapper lấy thông tin khách Hàng
        KhachHangMapper khachHangMapper = sqlSession.getMapper(KhachHangMapper.class);

        TaiKhoan account = taiKhoanMapper.getByUsername(username);
        if (account != null) {
            // Kiểm tra mật khẩu và thời hạn tài khoản
            if (BCrypt.checkpw(password, account.getPassword())) {
                System.out.println("bababooey");
                session.setAttribute("loggedIn", true);
                session.setAttribute("permission", account.getMaQuyen());
                session.setAttribute("username", username);
                // lấy mã khách hàng bỏ vào trong session
                session.setAttribute("maKhachHang", khachHangMapper.getMaKh(account.getId()));
                System.out.println("Bạn đã đăng nhập account với quyền là " + session.getAttribute("permission"));
                return "loggedIn";
            }
        }
        sqlSession.close();
        // Thông báo sai tài khoản
        return CustomError.createCustomError("Tài khoản sai tên đăng nhập hoặc mật khẩu", 401, response);

    }

    @Action(value = "/logout", results = {
            @Result(name = "success", location = "/", type = "redirect")
    }, interceptorRefs = {
            @InterceptorRef("authStack")
    })
    public String logout() {
        session.removeAttribute("loggedIn");
        session.removeAttribute("userName");
        return SUCCESS;
    }

}
