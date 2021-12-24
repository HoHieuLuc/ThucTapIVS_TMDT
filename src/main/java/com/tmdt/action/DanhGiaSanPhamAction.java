package com.tmdt.action;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import java.io.IOException;
import org.apache.ibatis.binding.BindingException;
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
            @Result(name=SUCCESS,location = "/index.html"),
    })
    public String getDanhGiaSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        List<Map<String, Object>> danhSachDanhGia = danhGiaSanPhamMapper.getAll(maSanPham);

        if (danhSachDanhGia == null) {
            return CustomError.createCustomError("Sản phầm này chưa có đánh giá Mã sản phẩm là " + maSanPham, 404,
                    response);
        }
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("danhSachDanhGia", danhSachDanhGia);
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }

    // lấy dữ liệu đánh giá sản phẩm hiển thị lên web bằng ajax
    @Action(value = "/viewDanhGiaSP/*",params = { "maSanPham", "{1}" },results = {
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
    public String danhGiaSanPhamSubmit() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);
        // Lấy ngày hiện tại:
        LocalDate today = LocalDate.now();
        //Múi giờ mặc định
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // Đổi ngày tạo tài khoản và ngày hết hạn sang SQL Date
        Date ngayTao = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
        int maKhachHang =(int) session.getAttribute("maKhachHang");
        DanhGiaSanPham dgsp = new DanhGiaSanPham(maKhachHang, soSao, noiDung, maSanPham, ngayTao, ngayTao);
        System.out.println("Debug:Mã khách hàng:" + maKhachHang + " Mã Sản phẩm:" + maSanPham + "Star:" + soSao + "Nội dung:" + noiDung);
        //DanhGiaSanPham dgsp = new DanhGiaSanPham(2, 5, "test cho khách hàng 2", "SP001", ngayTao, ngayTao);

        Map<String, Object> jsonObject = new HashMap<String, Object>();
        try {
            try {
                danhGiaSanPhamMapper.checkCusCommented(maSanPham, maKhachHang);
                jsonObject.put("error","Bạn đã bình luận sản phẩm này, chức năng sửa bình luận đang update");
                System.out.println("Bạn đã bình luận sản phẩm này, chức năng sửa bình luận đang update");
                return JsonResponse.createJsonResponse(jsonObject, 404, response);
            } catch(BindingException e) {
                danhGiaSanPhamMapper.themDGSP(dgsp);
                
            }
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            jsonObject.put("lỗi", "Thêm bình luận không được, vui lòng kiểm tra bạn");
            return JsonResponse.createJsonResponse(jsonObject, 404, response);  
        }
        sqlSession.commit();
        sqlSession.close();
         System.out.println("Insert Completly");
        return SUCCESS;
    }
}
