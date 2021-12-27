package com.tmdt.action;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
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

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
public class DanhGiaSanPhamAction extends ActionSupport {
    private String maSanPham;
    private String noiDung;
    private int soSao;
    private int maDanhGia;

    // region Getter and Setter
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
    // endregion

    HttpServletResponse response = ServletActionContext.getResponse();
    // Khởi tạo HttpSession
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // get tất cả đánh giá sản phẩm bởi id sản phẩm
    @Action(value = "/api/v1/danhgia/sanpham/*", params = { "maSanPham", "{1}" }, results = {
            @Result(name = SUCCESS, location = "/index.html"),
    })
    public String getDanhGiaSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);
        List<Map<String, Object>> danhSachDanhGia = new ArrayList<Map<String, Object>>();

        /*
         * Kiểm tra nếu người dùng đăng nhập hay chưa
         * Nếu người dùng chưa đăng nhập thì hiển thị tất cả đánh giá
         * Nếu người dùng đăng nhập rồi thì trừ cái đánh giá của người dùng đó ra
         * Vì mình sẽ dùng getDanhGiaSanPhamByMaKHandMaSP() tên dài vl
         * để ưu tiên hiển thị đánh giá của người dùng đó lên đầu
         * Nếu mà getAll luôn thì sẽ bị trùng
         */
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn) {
            danhSachDanhGia = danhGiaSanPhamMapper.getAll(maSanPham);
        } else {
            int maKhachHang = (int) session.getAttribute("maNguoiDung");
            danhSachDanhGia = danhGiaSanPhamMapper.getAllExceptOwn(maSanPham, maKhachHang);
        }
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("danhGiaSPs", danhSachDanhGia);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }

    // lấy đánh giá 1 sản phẩm của người dùng hiện tại
    @Action(value = "/api/v1/danhgia/sanpham/get", params = { "maSanPham", "{1}" }, results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String getDanhGiaSanPhamByMaKHandMaSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        // Nếu khách hàng đã bình luận sản phẩm này, đổi giao diện sang update sản phẩm
        // Hiển thị bình luận khách hàng đã nhập lên input, hiển thị số sao đã chọn từ
        // trước

        // Lẫy mã người dùng check, lấy mã sản phẩm dựa vào param url check
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        Map<String, Object> dgspHienTai = danhGiaSanPhamMapper.getByMaKHandMaSP(maKhachHang, maSanPham);

        System.out.println("dgspHienTai: " + dgspHienTai);
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("danhGiaSP", dgspHienTai);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }

    // Kiểm tra độ dài chuỗi có nằm trong khoảng từ min đến max
    public boolean between(String variable, int minValueInclusive, int maxValueInclusive) {
        return variable.length() >= minValueInclusive && variable.length() <= maxValueInclusive;
    }

    // Kiểm tra hợp lệ các trường nhập liệu
    public boolean isValid() {
        return between(noiDung, 2, 1000) && soSao >= 1 && soSao <= 5;
    }

    @Action(value = "/api/v1/danhgia/sanpham/submit", params = { "maSanPham", "{1}" }, results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String danhGiaSPSubmit() throws IOException {
        if(!isValid()) {
            return CustomError.createCustomError("Vui lòng nhập đầy đủ thông tin", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        int maKhachHang = (int) session.getAttribute("maNguoiDung");

        Map<String, Object> jsonObject = new HashMap<String, Object>();
        Map<String, Object> danhGiaSanPham = danhGiaSanPhamMapper.getByMaKHandMaSP(maKhachHang, maSanPham);
        if (danhGiaSanPham == null) {
            System.out.println("insert");
            DanhGiaSanPham dgsp = new DanhGiaSanPham(maSanPham, maKhachHang, noiDung, soSao);
            danhGiaSanPhamMapper.themDGSP(dgsp);
            sqlSession.commit();
            sqlSession.close();
            jsonObject.put("message", "Đánh giá sản phẩm thành công");
            return JsonResponse.createJsonResponse(jsonObject, 201, response);
        } else {
            System.out.println("update");
            danhGiaSanPhamMapper.updateDanhGiaSp(maSanPham, maKhachHang, noiDung, soSao);
            sqlSession.commit();
            sqlSession.close();
            jsonObject.put("message", "Cập nhật đánh giá sản phẩm thành công");
            return JsonResponse.createJsonResponse(jsonObject, 200, response);
        }
    }

}
