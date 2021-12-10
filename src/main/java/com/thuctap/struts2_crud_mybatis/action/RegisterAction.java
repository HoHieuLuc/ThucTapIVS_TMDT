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

import org.apache.ibatis.exceptions.PersistenceException;
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
 
    //Regex vừa dùng kiểm tra đại số boolean, vừa dùng để in từng thông báo lỗi cụ thể cho phía Client
    String password_regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,14}$";
    String username_regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,14}$";
    String email_regex = "^(.+)@(\\S+)$";

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
        return Pattern.matches(username_regex, username) && Pattern.matches(password_regex, password)
                && Pattern.matches(email_regex, email);
    }

    @Action(value = "/register", results = {
            @Result(name = "success", location = "/register_success.html"),
            @Result(name = "input", location = "/login.html")
    })
    public String register() throws IOException {
        //PrintWriter dùng để in thông báo lỗi
        PrintWriter printWriter = response.getWriter();

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

            // Thêm dữ liệu vào database, 
            //khoan khoan, kiểm tra xem có trùng username, trùng password không đã
            try {
                userAdminMapper.insert(userAdmin);
            }
            catch (PersistenceException e) {
                //System.out.println(e.getMessage());
                ArrayList<String> messages = new ArrayList<String>();
                if (e.getMessage().contains("user_admin.username_UNIQUE")){
                    messages.add("Ây da, username này đã có người dùng");
                    messages.add("email sẽ được kiểm tra khi username không bị trùng");
                }
                if (e.getMessage().contains("user_admin.email_UNIQUE")){
                    messages.add("Good job babe, username không bị trùng");
                    messages.add("Oh nồ nô, email bị trùng rồi");
                }
                return ValidateError.push(messages, 400, response, printWriter);
            }
            // Flush database connection, batch script and close connection
            sqlSession.commit();
            sqlSession.close();

            System.out.println(isValid());
            return SUCCESS;
        } else {
            ArrayList<String> messages = new ArrayList<String>();
            if (!Pattern.matches(username_regex, username))
            messages.add(
                    "Username Tối thiểu 6 ký tự và tối đa 14 kí tự, ít nhất một chữ cái và một số, không có kí tự khoảng trắng ");
            else messages.add("Username đúng quy tắc");

            if (!Pattern.matches(password_regex, password))
            messages.add(
                    "Password Tối thiểu 8 và tối đa 14 ký tự, ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt, không có khoảng trắng ");
            else messages.add("Password đúng quy tắc");
            return ValidateError.push(messages, 400, response, printWriter);
        }
    }
}
