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
import com.tmdt.model.LoaiSanPham;
import com.tmdt.utilities.JsonResponse;
import com.tmdt.utilities.ProjectPath;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.LoaiSanPhamMapper;
import mybatis.mapper.SanPhamMapper;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
public class LoaiSanPhamAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private int maLoaiSanPham;
    private String tenLoaiSanPham;
    private Integer maLoaiCha;
    private File anhLoaiSanPham;
    private String anhLoaiSanPhamFileName;
    private String anhLoaiSanPhamContentType;
    private int page;
    private int rowsPerPage;
    private String search;
    private String orderBy;
    private String order;

    // region Getter and Setter
    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public Integer getMaLoaiCha() {
        return maLoaiCha == 0 ? null : maLoaiCha;
    }

    public void setMaLoaiCha(Integer maLoaiCha) {
        this.maLoaiCha = maLoaiCha;
    }

    public File getAnhLoaiSanPham() {
        return anhLoaiSanPham;
    }

    public void setAnhLoaiSanPham(File anhLoaiSanPham) {
        this.anhLoaiSanPham = anhLoaiSanPham;
    }

    public String getAnhLoaiSanPhamFileName() {
        return anhLoaiSanPhamFileName;
    }

    public void setAnhLoaiSanPhamFileName(String anhLoaiSanPhamFileName) {
        this.anhLoaiSanPhamFileName = anhLoaiSanPhamFileName;
    }

    public String getAnhLoaiSanPhamContentType() {
        return anhLoaiSanPhamContentType;
    }

    public void setAnhLoaiSanPhamContentType(String anhLoaiSanPhamContentType) {
        this.anhLoaiSanPhamContentType = anhLoaiSanPhamContentType;
    }

    public int getPage() {
        return page < 1 ? 1 : page;
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

    public String getSearch() {
        return search == null ? "" : search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        if (orderBy == null) {
            return "ngay_dang";
        }
        switch (orderBy) {
            case "price":
                return "gia";
            case "rating":
                return "xep_hang";
            default:
                return "ngay_dang";
        }
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
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // tất cả loại sản phẩm api cho trang quản lý
    @Action(value = "/api/v1/category", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllLoaiSanPham() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        Map<String, Object> jsonRes = new HashMap<>();
        String _search = getSearch();
        int _page = getPage();
        int _rowsPerPage = getRowsPerPage();

        int countLoaiSanPham = loaiSanPhamMapper.countLoaiSanPham(_search);
        int offset = (_page - 1) * _rowsPerPage;
        int totalPage = (int) Math.ceil(countLoaiSanPham / (double) _rowsPerPage);

        List<Map<String, Object>> loaiSanPhams = loaiSanPhamMapper.getLoaiSanPhamChoAdmin(_search, offset,
                _rowsPerPage);
        jsonRes.put("loaiSanPhams", loaiSanPhams);
        jsonRes.put("totalPages", totalPage);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // Lấy tất cả loại sản phẩm con
    @Action(value = "/api/v1/subcategory/{maLoaiSanPham}", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllLoaiSanPhamCon() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        List<Map<String, Object>> loaiSanPhams = new ArrayList<>();
        if (maLoaiSanPham == 0) {
            loaiSanPhams = loaiSanPhamMapper.getAllLoaiSanPhamCapCao();
        } else {
            loaiSanPhams = loaiSanPhamMapper.getAllLoaiSanPhamCon(maLoaiSanPham);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loaiSanPhams", loaiSanPhams);
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    // Kiểm tra xem category đó có sản phẩm hay chưa
    @Action(value = "/api/v1/category_have_product", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllTenLoaiSanPham() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        List<LoaiSanPham> loaiSanPhams = loaiSanPhamMapper.getAllTenLoaiSanPham();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loaiSanPhams", loaiSanPhams);
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    // Liệt kê sản phẩm cùng category
    @Action(value = "/api/v1/category/{maLoaiSanPham}", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllSanPhamByLSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        List<Map<String, Object>> loaiSanPhams = new ArrayList<>();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        // code chỗ này khá rác vì tui ko suy nghĩ đc gì hết
        if (maLoaiSanPham == 0) {
            loaiSanPhams = loaiSanPhamMapper.getAllLoaiSanPhamCapCao();
            jsonRes.put("loaiSanPhams", loaiSanPhams);
            return JsonResponse.createJsonResponse(jsonRes, 200, response);
        }
        // = 0 tức là vẫn cần phân nhỏ ra nhiều loại nữa
        if (loaiSanPhamMapper.isLoaiSanPhamCapThap(maLoaiSanPham) == 0) {
            Map<String, Object> loaiSanPhamHienTai = loaiSanPhamMapper.getLoaiSanPham(maLoaiSanPham);
            if (loaiSanPhamHienTai == null) {
                return CustomError.createCustomError("Loại sản phẩm không tồn tại", 404, response);
            }
            loaiSanPhams = loaiSanPhamMapper.getAllLoaiSanPhamCon(maLoaiSanPham);
            jsonRes.put("loaiSanPhams", loaiSanPhams);
            jsonRes.put("loaiSanPhamHienTai", loaiSanPhamHienTai);
        } else {
            String _search = getSearch();
            int _page = getPage();
            int _rowsPerPage = 24;
            String _orderBy = getOrderBy();
            String _order = getOrder();

            int countLoaiSanPham = sanPhamMapper.countSanPhamByLSP(maLoaiSanPham, _search);
            int offset = (_page - 1) * _rowsPerPage;
            int totalPage = (int) Math.ceil(countLoaiSanPham / (double) _rowsPerPage);

            List<Map<String, Object>> sanPhams = sanPhamMapper.getAllSanPhamByLSP(maLoaiSanPham, _search, offset,
                    _rowsPerPage, _orderBy, _order);
            List<Map<String, Object>> loaiSanPhamCungCha = loaiSanPhamMapper.getLoaiSanPhamCungCha(maLoaiSanPham);
            Map<String, Object> loaiSanPhamCha = loaiSanPhamMapper.getLoaiSanPhamCha(maLoaiSanPham);
            jsonRes.put("sanPhams", sanPhams);
            jsonRes.put("totalPages", totalPage);
            jsonRes.put("loaiSanPhamCungCha", loaiSanPhamCungCha);
            jsonRes.put("loaiSanPhamCha", loaiSanPhamCha);
        }

        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    /* ================== */
    /* Dành cho nhân viên */
    /* ================== */

    // thêm loại sản phẩm
    boolean isValid() {
        return tenLoaiSanPham != null && tenLoaiSanPham.length() > 0
                && anhLoaiSanPham != null && anhLoaiSanPhamFileName != null
                && anhLoaiSanPhamContentType.contains("image/");
    }

    @Action(value = "/api/v1/admin/loaisanpham/them", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef("nhanVienStack")
    })
    public String themLoaiSanPham() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        if (!isValid()) {
            return CustomError.createCustomError("Vui lòng nhập đầy đủ thông tin", 400, response);
        }
        try {
            String fileNameNew = System.currentTimeMillis() + "_" + anhLoaiSanPhamFileName;
            LoaiSanPham loaiSanPham = new LoaiSanPham(0, tenLoaiSanPham, fileNameNew, getMaLoaiCha());
            loaiSanPhamMapper.addLoaiSanPham(loaiSanPham);

            String filePath = session.getServletContext().getRealPath("/") + "images\\category\\";
            String LocalPath = ProjectPath.getPath() + "\\images\\category\\";

            File fileTmp = new File(filePath + fileNameNew);
            File anhCopy = new File(LocalPath + fileNameNew);

            FileUtils.copyFile(this.anhLoaiSanPham, fileTmp);
            FileUtils.copyFile(this.anhLoaiSanPham, anhCopy);
            return CustomError.createCustomError("Thêm loại sản phẩm thành công", 200, response);
        } catch (PersistenceException e) {
            if (e.getMessage().contains("ten_loai_sp_UNIQUE")) {
                return CustomError.createCustomError("Tên loại sản phẩm đã tồn tại", 409, response);
            }
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return CustomError.createCustomError("Có lỗi xảy ra", 500, response);
    }

    @Action(value = "/api/v1/category/popular", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getPopularCategory() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        List<Map<String, Object>> popularCategory = loaiSanPhamMapper.getPopularCategory();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("popularCategory", popularCategory);
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(map, 200, response);
    }
}
