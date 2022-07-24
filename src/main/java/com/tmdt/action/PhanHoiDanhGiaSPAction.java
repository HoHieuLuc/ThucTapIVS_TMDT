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

import mybatis.mapper.PhanHoiDanhGiaSPMapper;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
public class PhanHoiDanhGiaSPAction extends ActionSupport {
    private int maPhanHoi;
    private int maDanhGia;
    private String noiDung;

    // region Getter and Setter
    public int getMaPhanHoi() {
        return maPhanHoi;
    }

    public void setMaPhanHoi(int maPhanHoi) {
        this.maPhanHoi = maPhanHoi;
    }

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
    // endregion

    // Khởi tạo Session, Session SQL, Connect DB
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
    // Kết thúc khởi tạo Sesion , Session SqlSession, ConnectDB

    // Kiểm tra độ dài chuỗi có nằm trong khoảng từ min đến max
    public boolean between(String variable, int minValueInclusive, int maxValueInclusive) {
        return variable.length() >= minValueInclusive && variable.length() <= maxValueInclusive;
    }

    // Kiểm tra hợp lệ các trường nhập liệu
    public boolean isValid() {
        return noiDung != null && between(noiDung, 2, 255);
    }

    //TODO: Action thêm đánh giá sản phẩm
    @Action(value = "/api/v1/phanhoi/submit", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String danhGiaSPSubmit() throws IOException {
        // Kiểm tra đã đăng nhập chưa
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn) {
            return CustomError.createCustomError("Bạn chưa đăng nhập", 401, response);
        }
        if ((int) session.getAttribute("level") > 0) {
            return CustomError.createCustomError("Bạn không thể làm điều này", 403, response);
        }
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        if (!isValid()) {
            return CustomError.createCustomError("Nội dung phải từ 2 đến 255 kí tự", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PhanHoiDanhGiaSPMapper phanHoiDanhGiaSanPham = sqlSession.getMapper(PhanHoiDanhGiaSPMapper.class);

        phanHoiDanhGiaSanPham.themPhanHoiDanhGiaSP(maDanhGia, noiDung, maKhachHang);
        sqlSession.commit();
        sqlSession.close();
        return SUCCESS;
    }

    //TODO: Lấy danh sách  phản hồi của đánh giá sản phẩm bất kì

    @Action(value = "/api/v1/phanhoi/{maDanhGia}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getPhanHoiDGSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PhanHoiDanhGiaSPMapper phanHoiDanhGiaSpMapper = sqlSession.getMapper(PhanHoiDanhGiaSPMapper.class);

        List<Map<String, Object>> phanHoiDGSPs = phanHoiDanhGiaSpMapper.getPhanHoiDGSP(maDanhGia);

        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("phanHoiDGSPs", phanHoiDGSPs);

        sqlSession.commit();
        sqlSession.close();

        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    @Action(value = "/api/v1/phanhoi/{maPhanHoi}/action", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getPhanHoiDGSPByMaDanhGia() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PhanHoiDanhGiaSPMapper phanHoiDanhGiaSpMapper = sqlSession.getMapper(PhanHoiDanhGiaSPMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        Integer level = (Integer) session.getAttribute("level");
        boolean yours = false;

        if (loggedIn != null && loggedIn && level == 0) {
            int maKhachHang = (int) session.getAttribute("maNguoiDung");
            if (phanHoiDanhGiaSpMapper.laPhanHoiCuaKhachHang(maPhanHoi, maKhachHang) == 1) {
                yours = true;
            }
        }
        sqlSession.close();
        jsonRes.put("yours", yours);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: cập nhật phản hồi
    @Action(value = "/api/v1/phanhoi/{maPhanHoi}/update", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String updatePhanHoiDGSP() throws IOException {
        if (!isValid()) {
            return CustomError.createCustomError("Nội dung phải từ 2 đến 255 kí tự", 400, response);
        }
        System.out.println(noiDung);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PhanHoiDanhGiaSPMapper phanHoiDanhGiaSpMapper = sqlSession.getMapper(PhanHoiDanhGiaSPMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        int maKhachHang = (int) session.getAttribute("maNguoiDung");

        int numb = phanHoiDanhGiaSpMapper.capNhatPhanHoi(maPhanHoi, noiDung, maKhachHang);
        if (numb == 0) {
            return CustomError.createCustomError("Bạn không được phép làm điều này", 403, response);
        }

        sqlSession.commit();
        sqlSession.close();
        jsonRes.put("message", "Cập nhật phản hồi thành công");
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: xóa phản hồi
    @Action(value = "/api/v1/phanhoi/{maPhanHoi}/delete", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String deletePhanHoiDGSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PhanHoiDanhGiaSPMapper phanHoiDanhGiaSpMapper = sqlSession.getMapper(PhanHoiDanhGiaSPMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        int maKhachHang = (int) session.getAttribute("maNguoiDung");

        int numb = phanHoiDanhGiaSpMapper.xoaPhanHoi(maPhanHoi, maKhachHang);
        if (numb == 0) {
            return CustomError.createCustomError("Bạn không được phép làm điều này", 403, response);
        }

        sqlSession.commit();
        sqlSession.close();
        jsonRes.put("message", "Xóa phản hồi thành công");
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }
}
