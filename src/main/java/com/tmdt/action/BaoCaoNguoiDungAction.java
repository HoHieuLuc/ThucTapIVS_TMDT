package com.tmdt.action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;
import com.tmdt.utilities.JsonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

public class BaoCaoNguoiDungAction extends ActionSupport {
    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    private String userName;
    private String noiDung;

    /* Region Getter and setter */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Action(value = "/api/v1/baocao/{userName}/submit", results = {
        @Result(name = SUCCESS, location = "/index.html")
        }, interceptorRefs = {
                @InterceptorRef(value = "khachHangStack"),
        })
    public String baoCaoNguoi() throws IOException {
        System.out.println("Username bị báo cáo là " + userName);
        System.out.println("Nội dung báo cáo là " + noiDung);
        return SUCCESS;
        
    }



}
