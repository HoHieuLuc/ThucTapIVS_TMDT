package com.tmdt.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.DatHangMapper;

public class DatHangAction extends ActionSupport {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    //TODO: Đặt hàng
    @Action(value = "/api/v1/dathang", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String datHang() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);
        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");
        // Thêm đơn đặt hàng mới
        try {
            int maDonDatHang;
            List<Map<String, Object>> sanPhams;
            // đặt theo người bán
            if (!username.equals("null")) {
                maDonDatHang = datHangMapper.themDonDHTheoSeller(maKhachHang, username);
                sanPhams = datHangMapper.getGioHangBySeller(maKhachHang, username);
            } else { // đặt tất cả
                maDonDatHang = datHangMapper.themDonDHMoi(maKhachHang);
                sanPhams = datHangMapper.getGioHangByMaKH(maKhachHang);
            }
            if (sanPhams.isEmpty()){
                return CustomError.createCustomError("Không có sản phẩm nào trong giỏ hàng", 404, response);
            }
            for (Map<String, Object> sanPham : sanPhams) {
                datHangMapper.themChiTietDatHang(
                        maKhachHang,
                        maDonDatHang,
                        (String) sanPham.get("ma_san_pham"),
                        (Integer) sanPham.get("so_luong"));
            }     
            // Khi sai mã sản phẩm ở câu query thứ 2
            // Thì câu query thứ nhất không commit được dữ liệu vào database, dù nó chạy
            // được
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (e.getMessage().contains("foreign")) {
                return CustomError.createCustomError("Thêm đơn đặt hàng thất bại", 400, response);
            }
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return CustomError.createCustomError("Cảm ơn, đơn đặt hàng của bạn đã được gửi", 201, response);
    }
}