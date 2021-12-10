package com.thuctap.struts2_crud_mybatis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import mybatis.mapper.UserAdminMapper;

import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.errors.*;
import com.thuctap.struts2_crud_mybatis.model.UserAdmin;

public class RegisterAction extends ActionSupport {
    // Tạo SQL_SESSION_FACTORY để chuẩn bị cho kết nối database
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();

    private String username, password, email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public boolean isValid() {
        String password_regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,14}$";
        String username_regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,14}$";
        String email_regex = "^(.+)@(\\S+)$";
        return Pattern.matches(username_regex, username) && Pattern.matches(password_regex, password)
                && Pattern.matches(email_regex, email);
    }

    @Action(value = "/register", results = {
            @Result(name = "success", location = "/register_success.html"),
            @Result(name = "input", location = "/login.html")
    })
    public String register() throws IOException {
        if (isValid()) {
            // Ở đây insert vô database sau khi validate form ok
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // Lấy ngày hiện tại:
            LocalDate today = LocalDate.now();
            // Đổi ngày tạo tài khoản và ngày hết hạn sang SQL Date
            Date date_created = Date.valueOf(today);
            Date date_expired = Date.valueOf(today.plusMonths(1));

            // Tạo userAdminMapper
            UserAdminMapper userAdminMapper = sqlSession.getMapper(UserAdminMapper.class);

            // String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            // Hash password sang BCrypt:
            password = BCrypt.hashpw(password, BCrypt.gensalt(12));

            // Tạo đối tượng lấy dữ liệu useradmin từ constructor
            UserAdmin userAdmin = new UserAdmin(username, password, email, date_created, date_expired);

            // Thêm dữ liệu vào database
            userAdminMapper.insert(userAdmin);
            // Flush database connection, batch script and close connection
            sqlSession.commit();
            sqlSession.close();

            System.out.println(isValid());
            return SUCCESS;
        } else {
            ArrayList<String> messages = new ArrayList<String>();
            messages.add(
                    "Username Tối thiểu 6 ký tự và tối đa 14 kí tự, ít nhất một chữ cái và một số, không có kí tự khoảng trắng ");
            messages.add(
                    "Password Tối thiểu 8 và tối đa 14 ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt, không có khoảng trắng ");
            PrintWriter printWriter = response.getWriter();
            return ValidateError.push(messages, 400, response, printWriter);
        }
    }

}
