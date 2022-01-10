package com.tmdt.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;
import com.tmdt.utilities.JsonResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.BaoCaoNguoiDungMapper;
import mybatis.mapper.SanPhamMapper;
import mybatis.mapper.TaiKhoanMapper;
import mybatis.mapper.ThongBaoMapper;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
public class NhanVienApiAction {
    private int status;
    private String maSanPham;
    private int page;
    private int rowsPerPage;
    private String search;
    private String userName;
    private int maBaoCao;
    private String noiDung;

    /* Begin Getter and setter */
    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMaBaoCao() {
        return maBaoCao;
    }

    public void setMaBaoCao(int maBaoCao) {
        this.maBaoCao = maBaoCao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getSearch() {
        return search == null ? "" : search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    /* End getter and setter */

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    /**
     * Dành cho nhân viên stack
     * 
     * 
     */
    /****** Kiểm duyệt sản phẩm ******/
    @Action(value = "/api/v1/nhanvien/sanpham/getbystatus/{status}", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String getAllSanPhamsChuaDuyet() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);

        int _page = getPage();
        int _rowsPerPage = getRowsPerPage();
        int _status = getStatus();
        String _search = getSearch();

        int countSanPham = sanPhamMapper.countSanPhamByStatus(_status, _search);
        int offset = (_page - 1) * _rowsPerPage;
        int totalPage = (int) Math.ceil(countSanPham / (double) _rowsPerPage);

        List<Map<String, Object>> listSanPham = sanPhamMapper.getSP_ByStatus(_status, offset, _rowsPerPage, _search);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("sanphams", listSanPham);
        jsonRes.put("total_page", totalPage);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // Phê duyệt và Cập nhật trạng thái cho từng sản phẩm
    @Action(value = "/api/v1/nhanvien/sanpham/changestatus", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String changeStatusSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);

        if (status >= -1 && status <= 2) {
            int changeStatusSP = sanPhamMapper.updateSP_Status(status, maSanPham);
            if (changeStatusSP == 1) {
                sqlSession.commit();
                sqlSession.close();
                return CustomError.createCustomError("Cập nhật trạng thái sản phẩm thành công", 201, response);
            }
            sqlSession.close();
            return CustomError.createCustomError("Mã sản phẩm không hợp lệ", 401, response);
        }
        // Map<String, Object> map = new HashMap<String, Object>();
        // map.put("sanphams", listSanPham);
        sqlSession.close();
        return CustomError.createCustomError("Trạng thái sản phẩm không hợp lệ", 401, response);
    }

    /****** Kiểm duyệt báo cáo người dùng ******/
    // Lấy danh sách báo cáo cần phê duyệt
    @Action(value = "/api/v1/nhanvien/baocao/getbystatus/{status}", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String getBaoCaoByStatus() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);

        List<Map<String, Object>> listBaoCao = baoCaoNguoiDungMapper.listBaoCaoByStatus(status);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("list_baocaos", listBaoCao);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // api/v1/nhanvien/baocao/changestatus?maBaoCao=${ma_bao_cao}&status=${status}
    @Action(value = "/api/v1/nhanvien/baocao/changestatus", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String duyetBaoCaoNguoiDung() throws IOException {

        // Debug
        System.out.println("Mã báo cáo: " + maBaoCao);
        System.out.println("Status: " + status);
        // Lấy id người nhận
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);
        TaiKhoanMapper taiKhoanMapper = sqlSession.getMapper(TaiKhoanMapper.class);
        ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);

        int idNguoiNhan = taiKhoanMapper.getTaiKhoanIdByUsername(userName);

        // Lấy id nhân viên đang duyệt
        int idNguoiGui = (int) session.getAttribute("accountID");

        // Duyệt báo cáo đó
       // baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);

        switch (status) {
            case -3: //Set số lần cảnh cáo = 3 luôn, khóa tài khoản
                baoCaoNguoiDungMapper.tangSoLanCanhBao(idNguoiNhan,3);
                // Gửi thông báo đến người bị báo cáo với nội dung

                thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui,
                        "Bạn bị khóa tài khoản vì hành vi gian dối lừa đảo người mua hàng");
                sqlSession.commit();
                sqlSession.close();
                return CustomError.createCustomError("Đã duyệt 'vi phạm' cho báo cáo này", 200, response);
            case -2: //Cảnh cáo và tăng số lần cảnh cáo của tài khoản lên 1 đơn vị
                baoCaoNguoiDungMapper.tangSoLanCanhBao(idNguoiNhan, 1);
                // Lấy số lần cảnh cáo còn lại bao nhiêu thì mới bị khóa tài khoản
                int soLanCanhCaoConLai = 3 - baoCaoNguoiDungMapper.getSoLanCanhCao(userName);
                if (soLanCanhCaoConLai == 0) {
                    thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui, "Bạn bị khóa tài khoản vì vi phạm nhiều lần");
                    sqlSession.commit();
                    sqlSession.close();
                    return CustomError.createCustomError("Đã duyệt 'vi phạm' cho báo cáo này", 200, response);
                } else 
                {
                    thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui, "Nếu bị cảnh cáo " + soLanCanhCaoConLai + " thì bị khóa tài khoản");
                    sqlSession.commit();
                    sqlSession.close();
                    return CustomError.createCustomError("Đã duyệt 'vi phạm' cho báo cáo này", 200, response);
                }
                
        }

        sqlSession.commit();
        sqlSession.close();
        return CustomError.createCustomError("SUCCESS cuối cùng", 200, response);
    }

    // Lấy thông tin chi tiết báo cáo cần phê duyệt
    @Action(value = "/api/v1/nhanvien/baocao/{maBaoCao}", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String getChiTietBaoCao() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);

        Map<String, Object> chiTietBaoCao = baoCaoNguoiDungMapper.detaiBaoCao(maBaoCao);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("chitiet_baocao", chiTietBaoCao);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

}
