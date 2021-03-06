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

import mybatis.mapper.KhachHangMapper;
import mybatis.mapper.SanPhamMapper;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
    "namespace", "/",
    "actionName", "bad-request"
})
public class StoreApiAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String username;
    private String search;
    private String orderBy;
    private String order;
    private int page;
    private int rowsPerPage;
    private String maSanPham;
    private int maLoaiSanPham;
    private Integer maLoaiCha;

    // region Getter and Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
            case 1:
            case 2:
            case 3:
            case 10:
            case 20:
            case 30:
                return rowsPerPage;
            default:
                return 10;
        }
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public Integer getMaLoaiCha() {
        if (maLoaiCha == null){
            return 0;
        }
        return maLoaiCha;
    }

    public void setMaLoaiCha(Integer maLoaiCha) {
        this.maLoaiCha = maLoaiCha;
    }
    // endregion

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    //TODO: Xử lí để hiển thị thông tin chi tiết của store bất kì
    @Action(value = "/api/v1/store/{username}/info", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getStoreInfo() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KhachHangMapper khachHangMapper = sqlSession.getMapper(KhachHangMapper.class);
        // lấy thông tin khách hàng
        Map<String, Object> storeInfo = khachHangMapper.getStoreInfoByUsername(username);
        // lấy số lượng theo từng số sao trong đánh giá sản phẩm
        List<Map<Integer, Integer>> phanLoaiDanhGia = khachHangMapper.getProductRating(username);
        if (storeInfo.get("ten") == null) {
            return CustomError.createCustomError("Người bán hàng không tồn tại", 404, response);
        }
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("store_info", storeInfo);
        jsonRes.put("product_rating", phanLoaiDanhGia);

        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: lấy danh sách sản phẩm của cửa hàng cụ thể
    @Action(value = "/api/v1/store/{username}/products", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getStoreProducts() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);

        int _page = getPage();
        int _rowsPerPage = getRowsPerPage();
        String _search = getSearch();
        String _orderBy = getOrderBy();
        String _order = getOrder();

        int countSanPham = sanPhamMapper.countSanPhamByUsername(username, _search);

        int offset = (_page - 1) * _rowsPerPage;
        int totalPages = (int) Math.ceil(countSanPham / (double) _rowsPerPage);

        List<Map<String, Object>> storeProducts = sanPhamMapper.getSanPhamByUsername(username, _search, _orderBy,
                _order, offset, _rowsPerPage);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("products", storeProducts);
        jsonRes.put("totalPages", totalPages);

        sqlSession.close();

        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: Liệt kê tất cả store
    @Action(value = "/api/v1/store", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getListStore() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KhachHangMapper khachHangMapper = sqlSession.getMapper(KhachHangMapper.class);
        // lấy mã store, avatar, tên store của từng khách hàng
        List<Map<String, Object>> listStore = khachHangMapper.getListStore();

        // Json Respone
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("listStores", listStore);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: api lấy top store
    @Action(value = "/api/v1/store/top", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getTopStore() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KhachHangMapper khachHangMapper = sqlSession.getMapper(KhachHangMapper.class);
        List<Map<String, Object>> listStore = khachHangMapper.getTopStore();
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("topStores", listStore);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: api lấy sản phẩm cùng store để gợi ý cho người dùng
    @Action(value = "/api/v1/store/{username}/products/suggestion", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getSameStoreProducts() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        Integer _maLoaiCha = getMaLoaiCha();
        List<Map<String, Object>> sanPhamGoiYCungStore = sanPhamMapper.getSanPhamGoiYCungStore(
            username, maSanPham, maLoaiSanPham, _maLoaiCha);
        sqlSession.close();
        jsonRes.put("products", sanPhamGoiYCungStore);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }
}
