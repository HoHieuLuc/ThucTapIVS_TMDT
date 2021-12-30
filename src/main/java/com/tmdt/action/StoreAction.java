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
import com.tmdt.errors.CustomError;
import com.tmdt.utilities.JsonResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.KhachHangMapper;

public class StoreAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // lấy thông tin store
    @Action(value = "/api/v1/store/{username}/info", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getStoreInfo() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KhachHangMapper khachHangMapper = sqlSession.getMapper(KhachHangMapper.class);
        // lấy thông tin khách hàng
        Map<String, Object> storeInfo = khachHangMapper.getStoreInfoByUsername(username);
        // lấy số lượng theo từng số sao trong đánh giá sản phẩm
        List<Map<Integer, Integer>> phanLoaiDanhGia = khachHangMapper.getProductRating(username);
        if (storeInfo.get("ten") == null) {
            return CustomError.createCustomError("Người bán hàng không tồn tại", 404, response);
        }
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("store_info", storeInfo);
        jsonRes.put("product_rating", phanLoaiDanhGia);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // Lấy danh sách các store có đăng từ 1 mặt hàng trở lên
    @Action(value = "/api/v1/listStore", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getListStore() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KhachHangMapper khachHangMapper = sqlSession.getMapper(KhachHangMapper.class);
        // lấy mã store, avatar, tên store của từng khách hàng
        List<Map<String, Object>> listStore = khachHangMapper.getListStore();

        // Json Respone
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("listStore", listStore);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }
}
