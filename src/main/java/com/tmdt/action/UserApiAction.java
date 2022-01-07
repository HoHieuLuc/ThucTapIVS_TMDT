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

import mybatis.mapper.*;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
    "namespace", "/",
    "actionName", "bad-request"
})
@InterceptorRef("khachHangStack")
public class UserApiAction extends ActionSupport{
    private static final long serialVersionUID = 1L;
    private String maSanPham;
    private String tenSanPham;
    private String moTa;
    private int gia;
    private int maLoaiSanPham;
    private int soLuong;
    private int status;
    private List<File> anhSanPhams = new ArrayList<File>();
    private List<String> anhSanPhamsFileName = new ArrayList<String>();
    private List<String> anhSanPhamsContentType = new ArrayList<String>();
    private String search;
    private int page;
    private int rowsPerPage;

    // region Getter and Setter
    public String getMaSanPham() {
        return maSanPham;
    }

    public int getStatus() {
        switch (status) {
            case 0:
            case 1:
            case 2:
                return status;
            default:
                return 0;
        }
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getSearch() {
        return search == null ? "" : search;
    }

    public void setSearch(String search) {
        this.search = search;
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

    /* ========================= */
    /* Các action cho khách hàng */
    /* ========================= */
    // action lấy danh sách sản phẩm
    // tiếp theo: phân trang
    @Action(value = "/api/v1/user/sanpham", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getSanPhamByMaKH() throws IOException {
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        int _page = getPage();
        int _rowsPerPage = getRowsPerPage();
        String _search = getSearch();
        int _status = getStatus();

        int countSanPham = sanPhamMapper.countSanPhamByMaKh(maKhachHang, _search, _status);
        int offset = (_page - 1) * _rowsPerPage;
        int totalPage = (int) Math.ceil(countSanPham / (double) _rowsPerPage);

        List<Map<String, Object>> listSanPham = sanPhamMapper.getSanPhamByMaKH(maKhachHang, _search, _status, offset, _rowsPerPage);
        sqlSession.close();
        
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("sanphams", listSanPham);
        jsonRes.put("total_page", totalPage);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // action lấy chi tiết sản phẩm
    @Action(value = "/api/v1/user/sanpham/{maSanPham}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getChiTietSanPhamByMaKH() throws IOException {
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        Map<String, Object> sanPham = sanPhamMapper.getSanPhamByMaKHAndMaSP(maKhachHang, maSanPham);
        if (sanPham.isEmpty()) {
            return CustomError.createCustomError("Không tìm thấy sản phẩm", 404, response);
        }
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("sanpham", sanPham);
        jsonRes.put("status", 200);
        System.out.println(jsonRes);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // action thêm sản phẩm
    @Action(value = "/api/v1/user/sanpham/them", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String createSanPham() throws IOException {
        if (!isValid()) {
            return CustomError.createCustomError("Dữ liệu không hợp lệ", 400, response);
        }
        if (anhSanPhams.size() > 5) {
            return CustomError.createCustomError("Số lượng ảnh không được vượt quá 5", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        AnhSanPhamMapper anhSanPhamMapper = sqlSession.getMapper(AnhSanPhamMapper.class);
        int maKhachHang = (int) session.getAttribute("maNguoiDung");

        // kiểm tra loại sản phẩm có hợp lệ hay không
        if(loaiSanPhamMapper.isLoaiSanPhamCapThap(maLoaiSanPham) == 0){
            return CustomError.createCustomError("Loại sản phẩm không hợp lệ", 400, response);
        }

        // kiểm tra xem tên sản phẩm có bị trùng không
        // chỉ kiểm tra sản phẩm mà khách hàng sở hữu
        if (sanPhamMapper.countSanPhamByMaKHAndTenSP(maKhachHang, tenSanPham) > 0) {
            return CustomError.createCustomError("Tên sản phẩm đã tồn tại", 409, response);
        }

        String filePath = session.getServletContext().getRealPath("/") + "images\\product\\";
        String LocalPath = ProjectPath.getPath() + "\\images\\product\\";

        SanPham sanPham = new SanPham(tenSanPham, maKhachHang, moTa, gia, 0, maLoaiSanPham, soLuong, 0);
        try {
            sanPhamMapper.insertSanPham(sanPham);
            sqlSession.commit();
            // khi thêm sản phẩm thành công thì mới bắt đầu thêm ảnh
            String insertedId = sanPhamMapper.getIdSanPhamByMaKHAndTenSP(maKhachHang, tenSanPham);
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
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("message", "Thêm sản phẩm thành công");
        return JsonResponse.createJsonResponse(jsonRes, 201, response);
    }
}