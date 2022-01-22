package com.tmdt.action;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.util.Map;

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

    //TODO: Xử lý đăng nhập và lưu thông tin vào Client Session
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

        TaiKhoan account = taiKhoanMapper.getTaiKhoanByUsername(username);
        if (account != null) {
            // Kiểm tra mật khẩu
            if (BCrypt.checkpw(password, account.getPassword())) {
                // Kiểm tra số lần cảnh cáo
                if (account.getSoLanCanhCao() >= 3) {
                    sqlSession.close();
                    return CustomError.createCustomError("Tài khoản bị khóa vì bị cảnh cáo 3 lần", 403, response);
                }
                Map<String, Object> loginInfo;
                session.setAttribute("loggedIn", true);
                session.setAttribute("permission", account.getMaQuyen());
                session.setAttribute("username", username);
                if (account.getMaQuyen().equals("KH")) {
                    loginInfo = taiKhoanMapper.getKhLoginInfoByUsername(username);
                    int maNguoiDung = (int) loginInfo.get("maNguoiDung");
                    session.setAttribute("maNguoiDung", maNguoiDung); // mã khách hàng
                } else {
                    loginInfo = taiKhoanMapper.getNvLoginInfoByUsername(username);
                    String maNguoiDung = (String) loginInfo.get("maNguoiDung");
                    session.setAttribute("maNguoiDung", maNguoiDung); // nhân viên
                }
                System.out.println("loginInfo: " + loginInfo.toString());

                String ten = (String) loginInfo.get("ten");
                int level = (int) loginInfo.get("level");
                String avatar = (String) loginInfo.get("avatar");

                session.setAttribute("accountID", account.getId());
                session.setAttribute("ten", ten);
                session.setAttribute("level", level);
                session.setAttribute("avatar", avatar);

                System.out.println("Bạn đã đăng nhập account với quyền là " + session.getAttribute("permission"));
                sqlSession.close();
                return "loggedIn";
            }
        }
        sqlSession.close();
        // Thông báo sai tài khoản
        return CustomError.createCustomError("Sai tài khoản hoặc mật khẩu", 401, response);

    }

    //TODO: Đăng xuất
    @Action(value = "/logout", results = {
            @Result(name = "success", location = "/", type = "redirect")
    }, interceptorRefs = {
            @InterceptorRef("authStack")
    })
    public String logout() {
        session.removeAttribute("loggedIn");
        session.removeAttribute("username");
        session.removeAttribute("permission");
        session.removeAttribute("accountID");
        session.removeAttribute("maNguoiDung");
        session.removeAttribute("ten");
        session.removeAttribute("level");
        session.removeAttribute("avatar");
        return SUCCESS;
    }

    @Action(value = "/checkCustomer", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String checkCustomer() throws IOException {

        // level xài Integer thay vì int vì int nó ko nhận null
        // nên là nếu chưa đăng nhập thì tới đoạn này nó crash luôn
        Integer level = (Integer) session.getAttribute("level");
        System.out.println(level);
        if (level != null && level == 0) {

            return CustomError.createCustomError("", 200, response);
        }
        // Thông báo tài khoản không phải khách hàng
        return CustomError.createCustomError("", 403, response);
    }

}
