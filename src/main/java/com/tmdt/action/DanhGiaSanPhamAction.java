package com.tmdt.action;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
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
    @Action(value = "/api/v1/danhgia/sanpham/{maSanPham}", results = {
            @Result(name = SUCCESS, location = "/index.html"),
    })
    public String getDanhGiaSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        List<Map<String, Object>> danhSachDanhGia = new LinkedList<Map<String, Object>>();
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        // level xài Integer thay vì int vì int nó ko nhận null
        // nên là nếu chưa đăng nhập thì tới đoạn này nó crash luôn
        Integer level = (Integer) session.getAttribute("level");
        // khi chưa đăng nhập hoặc không phải là khách hàng thì lấy hết tất cả đánh giá
        if (loggedIn == null || !loggedIn || level > 0) {
            danhSachDanhGia = danhGiaSanPhamMapper.getAllDanhGiaSanPham(maSanPham);
        } else {
            int maKhachHang = (int) session.getAttribute("maNguoiDung");
            danhSachDanhGia = danhGiaSanPhamMapper.getAllDanhGiaSanPhamOrderByCurrentUser(maSanPham, maKhachHang);
            if (!danhSachDanhGia.isEmpty() && (int) danhSachDanhGia.get(0).get("ma_khach_hang") == maKhachHang) {
                jsonRes.put("daDanhGia", true);
            } else {
                jsonRes.put("daDanhGia", false);
            }
        }
        jsonRes.put("danhGiaSPs", danhSachDanhGia);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // get action có thể có của người dùng đối với 1 đánh giá
    @Action(value = "/api/v1/danhgia/sanpham/{maDanhGia}/action", results = {
            @Result(name = SUCCESS, location = "/index.html"),
    })
    public String getActionDanhGiaSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        Integer level = (Integer) session.getAttribute("level");
        boolean yours = false;
        // nếu là đánh giá của chính mình thì sẽ hiện được nút sửa ở front end
        if (loggedIn != null && loggedIn && level == 0) {
            int maKhachHang = (int) session.getAttribute("maNguoiDung");
            if (danhGiaSanPhamMapper.laDanhGiaCuaKhachHang(maDanhGia, maKhachHang) == 1) {
                yours = true;
            }
        }
        jsonRes.put("yours", yours);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // Kiểm tra độ dài chuỗi có nằm trong khoảng từ min đến max
    public boolean between(String variable, int minValueInclusive, int maxValueInclusive) {
        return variable.length() >= minValueInclusive && variable.length() <= maxValueInclusive;
    }

    // Kiểm tra hợp lệ các trường nhập liệu
    public boolean isValid() {
        return noiDung != null && between(noiDung, 2, 1000) && soSao >= 1 && soSao <= 5;
    }

    @Action(value = "/api/v1/danhgia/sanpham/submit", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String danhGiaSPSubmit() throws IOException {
        if (!isValid()) {
            return CustomError.createCustomError("Vui lòng nhập đầy đủ thông tin", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        int maKhachHang = (int) session.getAttribute("maNguoiDung");

        Map<String, Object> jsonObject = new HashMap<String, Object>();
        Map<String, Object> danhGiaSanPham = danhGiaSanPhamMapper.getDanhGiaSanPhamByMaKHandMaSP(maKhachHang,
                maSanPham);
        if (danhGiaSanPham == null) {
            System.out.println("insert");
            DanhGiaSanPham dgsp = new DanhGiaSanPham(maSanPham, maKhachHang, noiDung, soSao);
            // Ngăn không cho khách hàng tự đánh giá sản phẩm chính mình
            if (maKhachHang == danhGiaSanPhamMapper.getMaKHFromMaSP(maSanPham)) {
                jsonObject.put("message", "Bạn không thể tự đánh giá chính mình");
                return JsonResponse.createJsonResponse(jsonObject, 403, response);
            }
            danhGiaSanPhamMapper.themDGSP(dgsp);
            jsonObject.put("message", "Đánh giá sản phẩm thành công");
            sqlSession.commit();
            sqlSession.close();
            return JsonResponse.createJsonResponse(jsonObject, 200, response);

        } else {
            System.out.println("update");
            danhGiaSanPhamMapper.updateDanhGiaSp(maSanPham, maKhachHang, noiDung, soSao);
            sqlSession.commit();
            sqlSession.close();
            jsonObject.put("message", "Cập nhật đánh giá sản phẩm thành công");
            return JsonResponse.createJsonResponse(jsonObject, 200, response);
        }
    }

    // xóa đánh giá sản phẩm
    @Action(value = "/api/v1/danhgia/sanpham/delete", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String deleteDanhGiaSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        int numb = danhGiaSanPhamMapper.xoaDanhGiaSanPham(maDanhGia, maKhachHang);
        sqlSession.commit();
        sqlSession.close();
        if(numb == 0){
            jsonObject.put("message", "Bạn không có quyền xóa đánh giá này");
            return JsonResponse.createJsonResponse(jsonObject, 403, response);
        }
        jsonObject.put("message", "Xóa đánh giá thành công");
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }
}
