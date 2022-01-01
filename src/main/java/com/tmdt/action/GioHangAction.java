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
import mybatis.mapper.GioHangMapper;
import com.tmdt.errors.CustomError;
import com.tmdt.utilities.JsonResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

public class GioHangAction extends ActionSupport {
    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    private String maSanPham;
    private int soLuong;

    /* Begin Setter and Getter */
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /* End Getter and setter */

    @Action(value = "/api/v1/giohang", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String getGioHangData() throws IOException {

        // SqlSession và Mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);

        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");
        // Tạo list gioHangs;
        List<Map<String, Object>> gioHangs;

        // Kiểm tra nếu chưa đăng nhập thì in thông báo
        if (maKhachHang != null) {
            gioHangs = gioHangMapper.getGioHangByMaKH(maKhachHang);
            sqlSession.commit();
            sqlSession.close();
            
            Map<String, Object> jsonRes = new HashMap<String, Object>();

            //Kiểm tra nếu giỏ hàng đang rỗng thì in thông báo
            if (gioHangs.size() > 0) {
                jsonRes.put("gioHangs", gioHangs);
                return JsonResponse.createJsonResponse(jsonRes, 200, response);
            } else {
                CustomError.createCustomError("Bạn chưa có sản phẩm nào trong giỏ hàng", 400, response);
            }
        }
        return CustomError.createCustomError("Bạn hãy đăng nhập để xem giỏ hàng", 400, response);

    }

}
