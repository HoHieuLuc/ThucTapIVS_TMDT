package com.tmdt.action;

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
import mybatis.mapper.GioHangMapper;
import mybatis.mapper.SanPhamYeuThichMapper;

import com.tmdt.errors.CustomError;
import com.tmdt.utilities.JsonResponse;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
public class GioHangAction extends ActionSupport {
    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    private String maSanPham;
    private Integer soLuong;
    private String username;

    // region Getter and Setter
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    // endregion

    /****** Lấy giỏ hàng **********/
    @Action(value = "/api/v1/giohang", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String getGioHangData() throws IOException {
        // SqlSession và Mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);
        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");
        // Tạo list gioHangs;
        List<Map<String, Object>> gioHangs = new ArrayList<Map<String, Object>>();
        // Lấy seller id cho các sản phẩm trong giỏ hàng
        List<Map<String, Object>> sellerList = new ArrayList<Map<String, Object>>();
        if (username != null) {
            sellerList = gioHangMapper.getSellerInfo(username);
        } else {
            sellerList = gioHangMapper.getSellerList(maKhachHang);
        }
        for (Map<String, Object> seller : sellerList) {
            List<Map<String, Object>> sanPhams = gioHangMapper.getGioHangBySeller(maKhachHang,
                    (String) seller.get("username"));
            seller.put("san_phams", sanPhams);
        }
        gioHangs.addAll(sellerList);
        sqlSession.commit();
        sqlSession.close();
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("gio_hangs", gioHangs);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // Lấy tổng tiền và số lượng trong giỏ hàng
    // không biết đặt tên kiểu gì nên đặt đại
    @Action(value = "/api/v1/giohang/sum", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String getGioHangSum() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");
        Map<String, Object> gioHang = gioHangMapper.getTongTienVaSoLuongGioHang(maKhachHang);
        jsonRes.put("gioHang", gioHang);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    @Action(value = "/api/v1/giohang/them", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String themVaoGioHang() throws IOException {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn) {
            return CustomError.createCustomError("Bạn chưa đăng nhập", 401, response);
        }
        // Kiểm tra không cho nhân viên đặt hàng
        Integer level = (Integer) session.getAttribute("level");
        if (level > 0) {
            return CustomError.createCustomError("Nhân viên không thể thêm vào giỏ hàng", 403, response);
        }

        // SqlSession và Mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);

        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");
        if (maKhachHang == gioHangMapper.getMaKhachHangByMaSP(maSanPham)) {
            return CustomError.createCustomError("Bạn không thể thêm sản phẩm của mình vào giỏ hàng", 403, response);
        }
        int soLuongSPHienCo = gioHangMapper.getSoLuongSPHienCo(maSanPham);
        if(soLuongSPHienCo <= 0) {
            return CustomError.createCustomError("Sản phẩm đã hết hàng", 403, response);
        }
        // Thêm sản phẩm
        try {
            gioHangMapper.themSanPhamVaoGioHang(maKhachHang, maSanPham);
        } catch (PersistenceException e) {
            if (e.getMessage().contains("PRIMARY")) {
                int soLuongSPTrongGioHang = gioHangMapper.getSoLuongSPTrongGioHang(maKhachHang, maSanPham);
                if (soLuongSPTrongGioHang >= soLuongSPHienCo) {
                    gioHangMapper.updateSoLuongSanPhamTrongGioHang(maKhachHang, maSanPham, soLuongSPHienCo);
                    return CustomError.createCustomError(
                            "Bạn chỉ có thể đặt tối đa " + soLuongSPHienCo + " sản phẩm cho sản phẩm này", 403,
                            response);
                }
                gioHangMapper.increaseSoLuongSP(maKhachHang, maSanPham);
            }
            if (e.getMessage().contains("FOREIGN")) {
                return CustomError.createCustomError("Sản phẩm không tồn tại", 404, response);
            }
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return CustomError.createCustomError("Thêm sản phẩm vào giỏ hàng thành công", 201, response);
    }

    @Action(value = "/api/v1/giohang/xoa", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })

    public String xoaKhoiGioHang() throws IOException {

        // SqlSession và Mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);

        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");

        // Xóa sản phẩm
        gioHangMapper.deleteSanPhamTrongGioHang(maKhachHang, maSanPham);
        sqlSession.commit();
        sqlSession.close();
        return CustomError.createCustomError("Xóa sản phẩm khỏi giỏ hàng thành công", 200, response);

    }

    @Action(value = "/api/v1/giohang/capnhat", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })

    public String capNhatGioHang() throws IOException {
        // SqlSession và Mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");

        // Cập nhật lại giỏ hàng
        if (soLuong == null || soLuong < 1) {
            sqlSession.close();
            jsonRes.put("soLuong", 1);
            jsonRes.put("message", "Số lượng không hợp lệ");
            return JsonResponse.createJsonResponse(jsonRes, 400, response);
        }

        /// Kiểm tra xem số lượng trong giỏ hàng có <= số lượng hiện có sản phẩm đó hay
        /// không
        int soLuongSPHienCo = gioHangMapper.getSoLuongSPHienCo(maSanPham);

        if (soLuong > soLuongSPHienCo) {
            // Điều chỉnh lại con số trong input số lượng của sản phẩm đó
            // Bằng JsonRes và hiện thông báo
            gioHangMapper.updateSoLuongSanPhamTrongGioHang(maKhachHang, maSanPham, soLuongSPHienCo);
            sqlSession.commit();
            sqlSession.close();
            jsonRes.put("soLuong", soLuongSPHienCo);
            if (soLuongSPHienCo <= 0){
                jsonRes.put("message", "Sản phẩm đã hết hàng");
            } else {
                jsonRes.put("message", "Bạn chỉ có thể đặt tối đa " + soLuongSPHienCo + " sản phẩm cho sản phẩm này");
            }
            return JsonResponse.createJsonResponse(jsonRes, 403, response);
        }
        gioHangMapper.updateSoLuongSanPhamTrongGioHang(maKhachHang, maSanPham, soLuong);
        sqlSession.commit();
        sqlSession.close();
        jsonRes.put("soLuong", soLuong);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    @Action(value = "/api/v1/fav", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String getSanPhamYeuThich() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamYeuThichMapper sanPhamYeuThichMapper = sqlSession.getMapper(SanPhamYeuThichMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");

        List<Map<String, Object>> sanPhamYeuThich = sanPhamYeuThichMapper.getSPYeuThich(maKhachHang);
        sqlSession.close();
        jsonRes.put("sanPhamYeuThich", sanPhamYeuThich);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // thêm sản phẩm yêu thích
    @Action(value = "/api/v1/fav/them", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String themSanPhamYeuThich() throws IOException {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn) {
            return CustomError.createCustomError("Bạn chưa đăng nhập", 401, response);
        }
        Integer level = (Integer) session.getAttribute("level");
        if (level > 0) {
            return CustomError.createCustomError("Nhân viên không thể thêm vào giỏ hàng", 403, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamYeuThichMapper sanPhamYeuThichMapper = sqlSession.getMapper(SanPhamYeuThichMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");
        try {
            sanPhamYeuThichMapper.themSPYeuThich(maKhachHang, maSanPham);
        } catch (PersistenceException e) {
            sqlSession.close();
            jsonRes.put("message", "Sản phẩm đã tồn tại trong danh sách yêu thích");
            return JsonResponse.createJsonResponse(jsonRes, 403, response);
        }
        sqlSession.commit();
        sqlSession.close();
        jsonRes.put("message", "Thêm sản phẩm yêu thích thành công");
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // xóa sản phẩm yêu thích
    @Action(value = "/api/v1/fav/xoa", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String xoaSanPhamYeuThich() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamYeuThichMapper sanPhamYeuThichMapper = sqlSession.getMapper(SanPhamYeuThichMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        // Lấy mã khách hàng từ session
        Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");

        int numb = sanPhamYeuThichMapper.xoaSPYeuThich(maKhachHang, maSanPham);
        if (numb == 0){
            sqlSession.close();
            jsonRes.put("message", "Sản phẩm không tồn tại trong danh sách yêu thích");
            return JsonResponse.createJsonResponse(jsonRes, 404, response);
        }
        sqlSession.commit();
        sqlSession.close();
        jsonRes.put("message", "Xóa sản phẩm yêu thích thành công");
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }
}