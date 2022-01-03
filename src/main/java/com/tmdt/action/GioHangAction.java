package com.tmdt.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import mybatis.mapper.GioHangMapper;
import com.tmdt.errors.CustomError;
import com.tmdt.utilities.JsonResponse;

import org.apache.ibatis.exceptions.PersistenceException;
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
        List<Map<String, Object>> gioHangs = new ArrayList<Map<String, Object>>();

        // Lấy seller id cho các sản phẩm trong giỏ hàng
        List<Map<String, Object>> sellerList = gioHangMapper.getSellerList(maKhachHang);
        for (Map<String, Object> seller : sellerList) {
            // System.out.println(gson.toJson(gioHangMapper.getGH_Info_By_Seller_ID(1,
            // integer)));
            List<Map<String, Object>> sanPhams = gioHangMapper.getGH_Info_By_Seller_ID(1,
                    (String) seller.get("username"));
            seller.put("san_phams", sanPhams);
        }
        gioHangs.addAll(sellerList);
        sqlSession.commit();
        sqlSession.close();

        Map<String, Object> jsonRes = new HashMap<String, Object>();

        jsonRes.put("gio_hangs", gioHangs);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);

    }

    @Action(value = "/api/v1/giohang/{maSanPham}/them", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String themSP_GioHang() throws IOException {

        // SqlSession và Mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);

        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");

        // Thêm sản phẩm
        try {
            gioHangMapper.themSP_GioHang(maKhachHang, maSanPham);
        } catch (PersistenceException e) {
            if (e.getMessage().contains("PRIMARY")) {
                gioHangMapper.increaseSoLuongSP(maKhachHang, maSanPham);
            }
            if (e.getMessage().contains("FOREIGN")) {
                return CustomError.createCustomError("Sản phẩm không tồn tại", 404, response);
            }
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return CustomError.createCustomError("Thêm sp vào giỏ hàng thành công", 200, response);

    }

    @Action(value = "/api/v1/giohang/{maSanPham}/xoa", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })

    public String xoa_GioHang() throws IOException {

        // SqlSession và Mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);

        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");

        // Xóa sản phẩm
        gioHangMapper.deleteSP(maKhachHang, maSanPham);
        sqlSession.commit();
        sqlSession.close();
        return CustomError.createCustomError("Xóa sản phẩm khỏi giỏ hàng thành công", 200, response);

    }

    @Action(value = "/api/v1/giohang/{maSanPham}/sua", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })

    public String sua_GioHang() throws IOException {

        // SqlSession và Mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);

        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");

        // Sửa sản phẩm
        gioHangMapper.updateSoLuongSP_In_GioHang(maKhachHang, maSanPham,soLuong);
        sqlSession.commit();
        sqlSession.close();
        return CustomError.createCustomError("Cập nhật số lượng sản phẩm thành công", 200, response);

    }

}
