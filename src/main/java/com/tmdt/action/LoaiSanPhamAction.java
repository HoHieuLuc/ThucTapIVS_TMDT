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
import com.tmdt.model.LoaiSanPham;
import com.tmdt.utilities.JsonResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.LoaiSanPhamMapper;

public class LoaiSanPhamAction extends ActionSupport{
    private static final long serialVersionUID = 1L;

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
    
    @Action(value = "/api/v1/loaisanpham", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllLoaiSanPham() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        List<LoaiSanPham> loaiSanPhams = loaiSanPhamMapper.getAllLoaiSanPham();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loaiSanPhams", loaiSanPhams);
        return JsonResponse.createJsonResponse(map, 200, response);
    }
}
