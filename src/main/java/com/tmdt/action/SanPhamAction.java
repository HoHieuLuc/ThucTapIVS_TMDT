package com.tmdt.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.utilities.JsonResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.SanPhamMapper;

public class SanPhamAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String maSanPham;
    

    public String getMaSanPham() {
        return maSanPham;
    }


    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }


    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();


    
    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    //Danh sách sản phẩm
    @Action(value = "/api/v1/sanpham/getall", results = {
        @Result(name = "success", location = "/index.html")
    })
    public String getAllSanPhams() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        List<Map<String, Object>> listSanPham = sanPhamMapper.getAllSanPham();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sanphams", listSanPham);
        System.out.println(listSanPham);
        return JsonResponse.createJsonResponse(map, 200, response);
    }


    //Chi tiết sản phẩm
    @Action(value = "/api/v1/sanpham/details/*",params = { "maSanPham", "{1}" }, results = {
        @Result(name=SUCCESS, location = "/index.html")
    })
    public String chiTietSanPhams() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        List<Map<String, Object>> listSanPham = sanPhamMapper.getDetailSanPham(maSanPham);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sanpham", listSanPham);
        System.out.println(listSanPham);
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    //Hiển thị giao diện xem chi tiết sản phẩm
    @Action(value = "/chiTietSanPham/*",results = {
        @Result(name=SUCCESS,location = "/WEB-INF/jsp/chiTietSanPham.jsp")
    })
    public String viewChiTietSanPham() throws IOException {
        return SUCCESS;
    }
    
}
