package com.tmdt.action;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;
import com.tmdt.utilities.JsonResponse;

import mybatis.mapper.*;
import com.tmdt.model.*;

public class DanhGiaSanPhamAction extends ActionSupport{
    private String maSanPham;
    
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    HttpServletResponse response = ServletActionContext.getResponse();
    // Khởi tạo HttpSession
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
        // get đánh giá sản phẩm  bởi id sản phẩm

    @Action(value = "/danhGiaSanPham/*", params = { "maSanPham", "{1}" }, results = {
        @Result(location = "/index.html"),
    })
    public String getStudent() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        List<Map<String,Object>> danhSachDanhGia = danhGiaSanPhamMapper.getAll(maSanPham);

        if (danhSachDanhGia == null) {
            return CustomError.createCustomError("Sản phầm này chưa có đánh giá Mã sản phẩm là " + maSanPham, 404, response);
        }
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("danhSachDanhGia", danhSachDanhGia);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }

    @Action(value = "/viewDanhGiaSP",results = { 
        @Result(name = "success",location = "/WEB-INF/jsp/danhGiaSanPham.jsp")
    })
    public String viewDanhGiaSP(){
        return SUCCESS;
    }
}
