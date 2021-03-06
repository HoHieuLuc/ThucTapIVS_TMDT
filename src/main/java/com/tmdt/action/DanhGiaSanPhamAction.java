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
    private int page;
    private int rowsPerPage;
    private String orderBy;
    private String order;

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

    public int getPage() {
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowsPerPage() {
        switch (rowsPerPage) {
            case 5:
            case 10:
            case 20:
            case 30:
                return rowsPerPage;
            default:
                return 5;
        }
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public String getOrderBy() {
        if (orderBy == null) {
            return "dgsp.ngay_tao";
        }
        if (orderBy.equals("rating")) {
            return "dgsp.so_sao";
        }
        return "dgsp.ngay_tao";
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        if (order == null) {
            return "desc";
        }
        if (order.equals("asc")) {
            return "asc";
        }
        return "desc";
    }

    public void setOrder(String order) {
        this.order = order;
    }
    // endregion

    HttpServletResponse response = ServletActionContext.getResponse();
    // Kh???i t???o HttpSession
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    //TODO: get t???t c??? ????nh gi?? s???n ph???m b???i id s???n ph???m
    @Action(value = "/api/v1/danhgia/sanpham/{maSanPham}", results = {
            @Result(name = SUCCESS, location = "/index.html"),
    })
    public String getDanhGiaSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        List<Map<String, Object>> danhSachDanhGia = new LinkedList<Map<String, Object>>();
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        // c??i username n??y d??ng ????? lo???i b??? ????nh gi?? c???a ng?????i d??ng ra khi chuy???n sang
        // trang 2
        // b??? ????nh gi?? ra b???ng c??ch ????a l??n ?????u danh s??ch
        // khi ch??a ????ng nh???p, username tr???ng, kh??ng lo???i b??? ai h???t
        // n???u l?? nh??n vi??n th?? c?? ????nh gi?? ???????c ????u, n??n b??? ra c??ng kh??ng sao
        // n???u l?? kh??ch h??ng, th?? trang 1 s??? ??u ti??n hi???n th??? ????nh gi?? ???? l??n ?????u
        // trang 2 tr??? ??i s??? lo???i b??? c??i ????nh gi?? ???? ra
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "";
        }

        int _page = getPage();
        int _rowsPerPage = getRowsPerPage();
        String _orderBy = getOrderBy();
        String _order = getOrder();

        int countDanhGiaSanPham = danhGiaSanPhamMapper.countAllDanhGiaSanPham(maSanPham);
        int offset = (_page - 1) * _rowsPerPage;
        int totalPages = (int) Math.ceil(countDanhGiaSanPham / (double) _rowsPerPage);

        //Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        // level x??i Integer thay v?? int v?? int n?? ko nh???n null
        // n??n l?? n???u ch??a ????ng nh???p th?? t???i ??o???n n??y n?? crash lu??n
        Integer level = (Integer) session.getAttribute("level");
        // khi ch??a ????ng nh???p ho???c kh??ng ph???i l?? kh??ch h??ng th?? l???y h???t t???t c??? ????nh gi??
        // if (loggedIn == null || !loggedIn || level > 0 || page > 1) {
        danhSachDanhGia = danhGiaSanPhamMapper.getAllDanhGiaSanPham(maSanPham, offset,
                _rowsPerPage, username, _orderBy, _order);
        if (!danhSachDanhGia.isEmpty() && ((String) danhSachDanhGia.get(0).get("username")).equals(username)) {
            jsonRes.put("daDanhGia", true);
        } else {
            if (level != null && level > 0) {
                jsonRes.put("daDanhGia", false);
            } else {
                Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");
                if (maKhachHang == null) {
                    maKhachHang = -1;
                }
                int daDanhGia = danhGiaSanPhamMapper.getDanhGiaSanPhamByMaKHandMaSP(maKhachHang, maSanPham);
                jsonRes.put("daDanhGia", daDanhGia == 1);
            }
        }
        jsonRes.put("danhGiaSPs", danhSachDanhGia);
        jsonRes.put("totalPages", totalPages);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: get action c?? th??? c?? c???a ng?????i d??ng ?????i v???i 1 ????nh gi??
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
        // n???u l?? ????nh gi?? c???a ch??nh m??nh th?? s??? hi???n ???????c n??t s???a ??? front end
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

    // Ki???m tra ????? d??i chu???i c?? n???m trong kho???ng t??? min ?????n max
    public boolean between(String variable, int minValueInclusive, int maxValueInclusive) {
        return variable.length() >= minValueInclusive && variable.length() <= maxValueInclusive;
    }

    // Ki???m tra h???p l??? c??c tr?????ng nh???p li???u
    public boolean isValid() {
        return noiDung != null && between(noiDung, 2, 1000) && soSao >= 1 && soSao <= 5;
    }

    @Action(value = "/api/v1/danhgia/sanpham/submit", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    //TODO: G???i ????nh gi?? s???n ph???m
    public String danhGiaSPSubmit() throws IOException {
        if (!isValid()) {
            return CustomError.createCustomError("Vui l??ng nh???p ?????y ????? th??ng tin", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

        int maKhachHang = (int) session.getAttribute("maNguoiDung");

        Map<String, Object> jsonObject = new HashMap<String, Object>();
        int danhGiaSanPham = danhGiaSanPhamMapper.getDanhGiaSanPhamByMaKHandMaSP(maKhachHang,
                maSanPham);
        if (danhGiaSanPham == 0) {
            DanhGiaSanPham dgsp = new DanhGiaSanPham(maSanPham, maKhachHang, noiDung, soSao);
            // Ng??n kh??ng cho kh??ch h??ng t??? ????nh gi?? s???n ph???m ch??nh m??nh
            if (maKhachHang == danhGiaSanPhamMapper.getMaKHFromMaSP(maSanPham)) {
                jsonObject.put("message", "B???n kh??ng th??? t??? ????nh gi?? ch??nh m??nh");
                return JsonResponse.createJsonResponse(jsonObject, 403, response);
            }
            danhGiaSanPhamMapper.themDGSP(dgsp);
            jsonObject.put("message", "????nh gi?? s???n ph???m th??nh c??ng");
            sqlSession.commit();
            sqlSession.close();
            return JsonResponse.createJsonResponse(jsonObject, 200, response);

        } else {
            danhGiaSanPhamMapper.updateDanhGiaSp(maSanPham, maKhachHang, noiDung, soSao);
            sqlSession.commit();
            sqlSession.close();
            jsonObject.put("message", "C???p nh???t ????nh gi?? s???n ph???m th??nh c??ng");
            return JsonResponse.createJsonResponse(jsonObject, 200, response);
        }
    }

    //TODO: x??a ????nh gi?? s???n ph???m
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
        if (numb == 0) {
            jsonObject.put("message", "B???n kh??ng c?? quy???n x??a ????nh gi?? n??y");
            return JsonResponse.createJsonResponse(jsonObject, 403, response);
        }
        jsonObject.put("message", "X??a ????nh gi?? th??nh c??ng");
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }
}
