package com.tmdt.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;
import com.tmdt.model.DanhGiaKhachHang;
import com.tmdt.utilities.JsonResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.DanhGiaKhachHangMapper;

public class DanhGiaKhachHangAction extends ActionSupport {
    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
    private Integer soSao;
    private int maKHDuocDanhGia;

    public Integer getSoSao() {
        return soSao;
    }

    public void setSoSao(Integer soSao) {
        this.soSao = soSao;
    }

    public int getMaKHDuocDanhGia() {
        return maKHDuocDanhGia;
    }

    public void setMaKHDuocDanhGia(int maKHDuocDanhGia) {
        this.maKHDuocDanhGia = maKHDuocDanhGia;
    }

    @Action(value = "/api/v1/danhgia/khachhang/submit", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String danhGiaKHSubmit() throws IOException {
        if (soSao == null || soSao < 0 || soSao > 5) {
            System.out.print( maKHDuocDanhGia + " " + soSao);
            return CustomError.createCustomError("Đánh giá thất bại ", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DanhGiaKhachHangMapper DanhGiaKhachHangMapper = sqlSession.getMapper(DanhGiaKhachHangMapper.class);
        // Mã khách hàng đánh giá
        int maKHDanhGia = (int) session.getAttribute("maNguoiDung");

        // Kiểm tra xem khách hàng đã đánh giá cửa hàng này hay chưa ?
        if (DanhGiaKhachHangMapper.kiemTraDanhGia(maKHDanhGia,maKHDuocDanhGia)!= null)
        {
            return CustomError.createCustomError("Bạn đã đánh giá khách hàng này", 400, response);
        }
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        // Tạo instance DanhGiaKhachHang
        DanhGiaKhachHang dgkh = new DanhGiaKhachHang(maKHDanhGia, maKHDuocDanhGia, soSao);
        
        DanhGiaKhachHangMapper.themDGKhachHang(dgkh);
        sqlSession.commit();
        sqlSession.close();
        jsonObject.put("message", "Đánh giá khách hàng thành công");
        return JsonResponse.createJsonResponse(jsonObject, 201, response);
    }
}
