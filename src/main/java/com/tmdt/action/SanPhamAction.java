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
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import mybatis.mapper.AnhSanPhamMapper;
import mybatis.mapper.SanPhamMapper;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
public class SanPhamAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String maSanPham;
    private String search;
    private int page;
    private int rowsPerPage;
    private Integer minPrice;
    private Integer maxPrice;
    private String order;

    // region Getter and Setter
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
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
            case 50:
            case 100:
                return rowsPerPage;
            default:
                return 5;
        }
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public Integer getMinPrice() {
        return minPrice == null ? 0 : minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice == null ? 0 : maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getOrder() {
        if (order == null) {
            return "";
        }
        switch (order) {
            case "date-desc":
                return "sp.ngay_dang desc";
            case "date-asc":
                return "sp.ngay_dang asc";
            case "rating-desc":
                return "xep_hang desc";
            case "rating-asc":
                return "xep_hang asc";
            default:
                return "";
        }
    }

    public void setOrder(String order) {
        this.order = order;
    }
    // endregion

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // Danh sách sản phẩm
    @Action(value = "/api/v1/sanpham", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllSanPhams() throws IOException {
        int _minPrice = getMinPrice();
        int _maxPrice = getMaxPrice();
        if (_minPrice > _maxPrice || _minPrice < 0 || _maxPrice < 0) {
            return CustomError.createCustomError("Giá không hợp lệ", 400, response);
        }
        if (_minPrice == 0 && _maxPrice == 0) {
            _minPrice = 0;
            _maxPrice = Integer.MAX_VALUE;
        }
        String _order = getOrder();
        if (!_order.equals("")) {
            _order = "ORDER BY " + _order;
        }

        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        String _search = getSearch();

        int countSanPham = sanPhamMapper.countAllSanPham(_search, _minPrice, _maxPrice);
        int _page = getPage();
        int _rowsPerPage = 20;

        int offset = (_page - 1) * _rowsPerPage;
        int totalPages = (int) Math.ceil(countSanPham / (double) _rowsPerPage);

        List<Map<String, Object>> listSanPham = sanPhamMapper.searchSanPham(
                _search, _minPrice, _maxPrice, _order, offset, _rowsPerPage);

        if (listSanPham.isEmpty()) {
            if (!_order.equals("")) {
                _order = _order.replace("ORDER BY", ", ");
            }
            countSanPham = sanPhamMapper.countAllSanPhamFulltext(_search, _minPrice, _maxPrice);
            totalPages = (int) Math.ceil(countSanPham / (double) _rowsPerPage);
            listSanPham = sanPhamMapper.searchSanPhamFulltext(
                    _search, _minPrice, _maxPrice, _order, offset, _rowsPerPage);
        }
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("sanphams", listSanPham);
        jsonRes.put("totalPages", totalPages);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // Chi tiết sản phẩm
    @Action(value = "/api/v1/sanpham/{maSanPham}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String chiTietSanPhams() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        AnhSanPhamMapper anhSanPhamMapper = sqlSession.getMapper(AnhSanPhamMapper.class);

        Map<String, Object> sanPham = sanPhamMapper.getDetailSanPham(maSanPham);
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        if (sanPham == null) {
            jsonRes.put("message", "Không tìm thấy sản phẩm");
            return JsonResponse.createJsonResponse(jsonRes, 404, response);
        }

        List<String> listAnhSanPham = anhSanPhamMapper.getAllAnhSanPham(maSanPham);
        sanPham.put("anhSanPhams", listAnhSanPham);

        jsonRes.put("sanpham", sanPham);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // sản phẩm mới nhất
    @Action(value = "/api/v1/sanpham/new", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getNewSanPhams() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        List<Map<String, Object>> listSanPham = sanPhamMapper.getNewestProducts();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sanphams", listSanPham);
        return JsonResponse.createJsonResponse(map, 200, response);
    }
}
