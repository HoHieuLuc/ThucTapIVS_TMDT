package com.tmdt.action;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

public class DanhGiaSanPhamAction extends ActionSupport {
    private String maSanPham, noiDung;
    private int soSao;

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getSoSao() {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }

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

    // get đánh giá sản phẩm bởi id sản phẩm
    @Action(value = "/danhGiaSanPham/*", params = { "maSanPham", "{1}" }, results = {
            @Result(location = "/index.html"),
    })
    public String getStudent() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        List<Map<String, Object>> danhSachDanhGia = danhGiaSanPhamMapper.getAll(maSanPham);

        if (danhSachDanhGia == null) {
            return CustomError.createCustomError("Sản phầm này chưa có đánh giá Mã sản phẩm là " + maSanPham, 404,
                    response);
        }
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("danhSachDanhGia", danhSachDanhGia);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }

    // lấy dữ liệu đánh giá sản phẩm hiển thị lên web bằng ajax
    @Action(value = "/viewDanhGiaSP", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/danhGiaSanPham.jsp")
    })
    public String viewDanhGiaSP() {
        return SUCCESS;
    }

    // Kiểm tra độ dài chuỗi có nằm trong khoảng từ min đến max
    public static boolean between(String variable, int minValueInclusive, int maxValueInclusive) {
        return variable.length() >= minValueInclusive && variable.length() <= maxValueInclusive;
    }

    // Kiểm tra hợp lệ các trường nhập liệu
    public boolean isValid() {
        return true;
    }

    @Action(value = "/danhGiaSanPhamSubmit", results = {
        @Result(name = SUCCESS,location = "/index.html")
    })
    public String danhGiaSanPhamSubmit() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        System.out.println(noiDung + "--" + soSao + "----" + maSanPham + "----" + session.getAttribute("maKhachHang").toString());

        // Lấy ngày hiện tại:
        LocalDate today = LocalDate.now();
        //Múi giờ mặc định
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // Đổi ngày tạo tài khoản và ngày hết hạn sang SQL Date
        Date ngayTao = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
        DanhGiaSanPham dgsp = new DanhGiaSanPham((int) session.getAttribute("maKhachHang"), soSao, noiDung, "SP001", ngayTao, ngayTao);
      // DanhGiaSanPham dgsp = new DanhGiaSanPham(1, 5, "dasdsads", "SP001", ngayTao, ngayTao);
        try {
            danhGiaSanPhamMapper.themDGSP(dgsp);
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
         
        return SUCCESS;
    }
}
