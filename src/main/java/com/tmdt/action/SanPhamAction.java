package com.tmdt.action;

import java.io.File;
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
import com.tmdt.model.AnhSanPham;
import com.tmdt.model.SanPham;
import com.tmdt.utilities.JsonResponse;
import com.tmdt.utilities.ProjectPath;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.AnhSanPhamMapper;
import mybatis.mapper.SanPhamMapper;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
public class SanPhamAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String maSanPham;
    private String tenSanPham;
    private String moTa;
    private int gia;
    private int maLoaiSanPham;
    private int soLuong;
    private List<File> anhSanPhams = new ArrayList<File>();
    private List<String> anhSanPhamsFileName = new ArrayList<String>();
    private List<String> anhSanPhamsContentType = new ArrayList<String>();

    // region Getter and Setter
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public List<File> getAnhSanPhams() {
        return anhSanPhams;
    }

    public void setAnhSanPhams(List<File> anhSanPhams) {
        this.anhSanPhams = anhSanPhams;
    }

    public List<String> getAnhSanPhamsFileName() {
        return anhSanPhamsFileName;
    }

    public void setAnhSanPhamsFileName(List<String> anhSanPhamsFileName) {
        this.anhSanPhamsFileName = anhSanPhamsFileName;
    }

    public List<String> getAnhSanPhamsContentType() {
        return anhSanPhamsContentType;
    }

    public void setAnhSanPhamsContentType(List<String> anhSanPhamsContentType) {
        this.anhSanPhamsContentType = anhSanPhamsContentType;
    }
    // endregion

    /* validate */
    // validate ảnh
    public boolean validateAnhSanPham() {
        if (anhSanPhams.isEmpty()) {
            return false;
        }
        for (File u : anhSanPhams) {
            if (u == null) {
                return false;
            }
        }
        for (String c : anhSanPhamsContentType) {
            if (!c.contains("image/")) {
                return false;
            }
        }
        return true;
    }

    // validate toàn bộ input
    public boolean isValid() {
        return tenSanPham != null && tenSanPham.length() > 0 &&
                moTa != null && moTa.length() > 0 &&
                gia > 0 && soLuong > 0 &&
                validateAnhSanPham();
    }

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // Danh sách sản phẩm
    @Action(value = "/api/v1/sanpham/getall", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllSanPhams() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        List<Map<String, Object>> listSanPham = sanPhamMapper.getAllSanPham();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sanphams", listSanPham);
        System.out.println(listSanPham);
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    // Chi tiết sản phẩm
    @Action(value = "/api/v1/sanpham/*", params = { "maSanPham", "{1}" }, results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String chiTietSanPhams() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        Map<String, Object> listSanPham = sanPhamMapper.getDetailSanPham(maSanPham);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sanpham", listSanPham);
        System.out.println(listSanPham);
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    // Hiển thị giao diện xem chi tiết sản phẩm
    @Action(value = "/sanpham/*", results = {
            @Result(name = SUCCESS, location = "/WEB-INF/jsp/product/index.jsp")
    })
    public String viewChiTietSanPham() {
        return SUCCESS;
    }

    /* Các action cho khách hàng */
    // action lấy danh sách sản phẩm
    @Action(value = "/api/v1/user/sanpham", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef("khachHangStack")
    })
    public String getSanPhamByMaKH() throws IOException {
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        List<Map<String, Object>> listSanPham = sanPhamMapper.getAllSanPhamByMaKH(maKhachHang);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sanphams", listSanPham);
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    // action lấy chi tiết sản phẩm
    @Action(value = "/api/v1/user/sanpham/*", params = { "maSanPham", "{1}" }, results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef("khachHangStack")
    })
    public String getChiTietSanPhamByMaKH() throws IOException {
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        Map<String, Object> listSanPham = sanPhamMapper.getSanPhamByMaKHAndMaSP(maKhachHang, maSanPham);
        if (listSanPham.isEmpty()) {
            return CustomError.createCustomError("Không tìm thấy sản phẩm", 404, response);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sanpham", listSanPham);
        map.put("status", 200);
        System.out.println(map);
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    // action thêm sản phẩm
    @Action(value = "/api/v1/user/sanpham/create", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef("khachHangStack")
    })
    public String createSanPham() throws IOException {
        System.out.println("here");
        if (!isValid()) {
            return CustomError.createCustomError("Dữ liệu không hợp lệ", 400, response);
        }
        if (anhSanPhams.size() > 5) {
            return CustomError.createCustomError("Số lượng ảnh không được vượt quá 5", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        AnhSanPhamMapper anhSanPhamMapper = sqlSession.getMapper(AnhSanPhamMapper.class);
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
  

        String filePath = session.getServletContext().getRealPath("/") + "images\\product\\";
        String LocalPath = ProjectPath.getPath() + "\\images\\product\\";

        SanPham sanPham = new SanPham(tenSanPham, maKhachHang, moTa, gia, 0, maLoaiSanPham, soLuong, 0);
        try {
            sanPhamMapper.insert(sanPham);
            sqlSession.commit();
            // khi thêm sản phẩm thành công thì mới bắt đầu thêm ảnh
            String insertedId = sanPhamMapper.getIdSanPham(sanPham);
            for (int i = 0; i < anhSanPhams.size(); i++) {
                String fileNameNew = System.currentTimeMillis() + "_" + anhSanPhamsFileName.get(i);
                File fileTmp = new File(filePath + fileNameNew); // file ảnh được lưu tạm
                File fileNew = new File(LocalPath + fileNameNew); // file ảnh lưu vào thư mục project
                FileUtils.copyFile(anhSanPhams.get(i), fileTmp);
                FileUtils.copyFile(anhSanPhams.get(i), fileNew);
                AnhSanPham anhSanPham = new AnhSanPham(insertedId, fileNameNew);
                anhSanPhamMapper.insertAnhSanPham(anhSanPham);
            }
            sqlSession.commit();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            sqlSession.close();
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "Thêm sản phẩm thành công");
        return JsonResponse.createJsonResponse(map, 201, response);
    }

}
