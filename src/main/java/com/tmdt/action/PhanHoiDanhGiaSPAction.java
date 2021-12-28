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

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.PhanHoiDanhGiaSPMapper;

public class PhanHoiDanhGiaSPAction extends ActionSupport {
    private int maDanhGia;
    private String noiDung;

    // Begin region setter getttẻr

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    // End region Setter getter

    // Khởi tạo Session, Session SQL, Connect DB
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
    // Kết thúc khởi tạo Sesion , Session SqlSession, ConnectDB

    // Mã khách hàng lưu vô session
    int maKhachHang = (int) session.getAttribute("maNguoiDung");

    // Kiểm tra độ dài chuỗi có nằm trong khoảng từ min đến max
    public boolean between(String variable, int minValueInclusive, int maxValueInclusive) {
        return variable.length() >= minValueInclusive && variable.length() <= maxValueInclusive;
    }

    // Kiểm tra hợp lệ các trường nhập liệu
    public boolean isValid() {
        return between(noiDung,2,255);
    }

    @Action(value = "/api/v1/phanhoi/sanpham/submit", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })

    //Action thêm đánh giá sản phẩm
    public String danhGiaSPSubmit() throws IOException {
        System.out.println("Mã Đánh Giá" + maDanhGia);
        System.out.println("Nội dung" + noiDung);
        if (!isValid()) {
            return CustomError.createCustomError("Nội dung phải từ 2 đến 255 kí tự", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PhanHoiDanhGiaSPMapper phanHoiDanhGiaSanPham = sqlSession.getMapper(PhanHoiDanhGiaSPMapper.class);

        phanHoiDanhGiaSanPham.themPhanHoiDanhGiaSP(maDanhGia, noiDung, maKhachHang);
        return SUCCESS;

    }
}
