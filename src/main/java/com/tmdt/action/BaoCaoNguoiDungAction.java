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

import mybatis.mapper.BaoCaoNguoiDungMapper;

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
        
        //Lấy id người gửi
        int idNguoiGui = (int) session.getAttribute("accountID");

        // Lấy id người nhận
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);
        int idNguoiNhan = baoCaoNguoiDungMapper.getMaNguoiDungBiBaoCao(userName);

        
        //Chặn không cho mình tự báo cáo chính mình
        if (idNguoiGui == idNguoiNhan)
        {
            sqlSession.close();
            return CustomError.createCustomError("Bạn không thể tự báo cáo chính mình",403,response);
        }

        //Chặn khi nội dung quá ngắn
        if (noiDung.length() <20 || noiDung.length() > 255)
        {
            sqlSession.close();
            return CustomError.createCustomError("Nội dung phải từ 20 ký tự trở lên",403,response);
        }

        //Tiến hành gửi báo cáo
        baoCaoNguoiDungMapper.guiBaoCaoNguoiDung(idNguoiNhan, idNguoiGui);
        return SUCCESS;
    }



}
