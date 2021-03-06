package com.tmdt.action;

import java.io.IOException;
import java.util.Date;
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
import mybatis.mapper.ThongBaoMapper;
import mybatis.mapper.ThongKeMapper;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
public class NhanVienApiAction {
    private Integer status;
    private String maSanPham;
    private int page;
    private int rowsPerPage;
    private String search;
    private String userName;
    private int maBaoCao;
    private String noiDung;
    private Date tuNgay;
    private Date denNgay;
    private int thang;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
                return 10;
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

    public Date getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(Date tuNgay) {
        this.tuNgay = tuNgay;
    }

    public Date getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(Date denNgay) {
        this.denNgay = denNgay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

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
    //TODO: Lấy danh sách tất cả sản phẩm chưa duyệt
    @Action(value = "/api/v1/admin/sanpham/getbystatus/{status}", results = {
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

        List<Map<String, Object>> listSanPham = sanPhamMapper.getSanPhamByStatus(_status, offset, _rowsPerPage,
                _search);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("sanphams", listSanPham);
        jsonRes.put("total_page", totalPage);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: Phê duyệt và Cập nhật trạng thái cho từng sản phẩm
    @Action(value = "/api/v1/admin/sanpham/{maSanPham}/duyet", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String changeStatusSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);

        if (status >= -1 && status <= 2) {
            int changeStatusSP = sanPhamMapper.updateSanPhamStatus(status, maSanPham);
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
    //TODO: Lấy danh sách báo cáo cần phê duyệt
    @Action(value = "/api/v1/admin/baocao/getbystatus/{status}", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String getBaoCaoByStatus() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);
        String _search = getSearch();
        int _page = getPage();
        int _rowsPerPage = getRowsPerPage();

        int countBaoCao = baoCaoNguoiDungMapper.countBaoCaoByStatus(status, _search);
        int offset = (_page - 1) * _rowsPerPage;
        int totalPages = (int) Math.ceil(countBaoCao / (double) _rowsPerPage);

        List<Map<String, Object>> listBaoCao = baoCaoNguoiDungMapper.listBaoCaoByStatus(status, _search, offset,
                _rowsPerPage);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("list_baocaos", listBaoCao);
        jsonRes.put("totalPages", totalPages);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: Duyệt báo cáo người dùng
    @Action(value = "/api/v1/admin/baocao/{maBaoCao}/duyet", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String duyetBaoCaoNguoiDung() throws IOException {
        if (status == null || noiDung == null) {
            return CustomError.createCustomError("Yêu cầu không hợp lệ", 400, response);
        }
        // Lấy id người nhận
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);
        ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);

        Map<String, Object> chiTietBaoCao = baoCaoNguoiDungMapper.getBaoCao(maBaoCao);
        int idNguoiNhan = (int) chiTietBaoCao.get("id_nguoi_nhan");

        // Lấy id nhân viên đang duyệt
        int idNguoiGui = (int) session.getAttribute("accountID");

        // Gán nội dung tùy chọn và lấy nội dung khác
        switch (noiDung) {
            case "1":
                noiDung = "Bạn bị tố cáo vì bán sản phẩm cấm";
                break;
            case "2":
                noiDung = "Bạn bị tố cáo vì lừa đảo";
                break;
            case "3":
                noiDung = "Bạn bị tố cáo vì bán hàng giả";
                break;
            default:
                break;
        }

        // Duyệt báo cáo với trạng thái cụ thể và các hoạt động sau đó
        switch (status) {
            // Nội dung của thông báo là nhân viên viết tay (Option: (có nhiều mục có sẵn
            // nội dung, mục cuối là nội dung tự viết) để gửi cho người bị báo cáo)
            case -2:
                baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);
                // Set số lần cảnh cáo = 3 luôn, khóa tài khoản
                baoCaoNguoiDungMapper.khoaTaiKhoan(idNguoiNhan);
                thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui, noiDung);
                sqlSession.commit();
                sqlSession.close();
                return CustomError.createCustomError("Đã duyệt báo cáo này và khóa tài khoản", 401, response);
            case -1:
                baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);
                // Cảnh cáo và tăng số lần cảnh cáo của tài khoản lên 1 đơn vị
                baoCaoNguoiDungMapper.tangSoLanCanhBao(idNguoiNhan, 1);
                // Lấy số lần cảnh cáo còn lại bao nhiêu thì mới bị khóa tài khoản
                int soLanCanhCaoConLai = 3 - baoCaoNguoiDungMapper.getSoLanCanhCao(userName);

                if (soLanCanhCaoConLai == 0) {
                    thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui, "Bạn bị khóa tài khoản vì: " + noiDung);
                    sqlSession.commit();
                    sqlSession.close();
                    return CustomError.createCustomError("Đã duyệt báo cáo này và khóa tài khoản", 200, response);
                } else if (soLanCanhCaoConLai > 0 && soLanCanhCaoConLai < 3) {
                    thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui,
                            "Bạn bị tố cáo vì: " + noiDung + "\r\n" +
                                    "Nếu bị cảnh cáo " + soLanCanhCaoConLai + " thì sẽ bị khóa tài khoản.");
                    sqlSession.commit();
                    sqlSession.close();
                    return CustomError.createCustomError(
                            "Đã duyệt báo cáo này và nhắc nhở số lần cảnh cáo cho người vi phạm", 200, response);
                }
                break;
            case 1:
                baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);
                thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui,
                        "Bạn bị tố cáo vì: " + noiDung + "\r\n" +
                                "Nếu tiếp tục vi phạm sẽ bị cảnh cáo, quá 2 lần cảnh cáo sẽ bị khóa tài khoản.");
                sqlSession.commit();
                sqlSession.close();
                return CustomError.createCustomError("Đã duyệt báo cáo này, không tăng số lần cảnh báo", 200, response);
            case 2:
                baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);
                sqlSession.commit();
                sqlSession.close();
                return CustomError.createCustomError("Đã duyệt báo cáo này không vi phạm", 200, response);
            default:
                return CustomError.createCustomError("Yêu cầu không hợp lệ", 400, response);
        }
        return CustomError.createCustomError("Yêu cầu không hợp lệ", 400, response);
    }

    //TODO: Lấy thông tin chi tiết báo cáo cần phê duyệt
    @Action(value = "/api/v1/admin/baocao/{maBaoCao}", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String getChiTietBaoCao() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);
        Map<String, Object> chiTietBaoCao = baoCaoNguoiDungMapper.detailBaoCao(maBaoCao);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("chitiet_baocao", chiTietBaoCao);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: kiểm tra ngày thống kê
    public boolean kiemTraNgayThongKe() {
        return tuNgay != null && denNgay != null;
    }

    //TODO: Dùng 1 action cho thống kê của quản trị viên
    @Action(value = "/api/v1/admin/thongke/{status}", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String xuLyThongKe() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("Status Thống kê  = " + status);
        ThongKeMapper thongKeMapper = sqlSession.getMapper(ThongKeMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        /*
         * 0 là thống kê 4 loại dữ liệu đơn giản
         * 1 là đếm số lượng đơn đặt hàng theo 4 trạng thái (bị hủy, đang chờ tiếp nhận,
         * đang giao, đã giao)
         */
        switch (status) {
            case 0:
                Map<String, Object> thongKe_4Data = thongKeMapper.get4DataThongKe();
                jsonRes.put("thong_ke", thongKe_4Data);
                sqlSession.close();
                return JsonResponse.createJsonResponse(jsonRes, 200, response);
            case 1:
                // Check null cho 2 giá trị ngày
                if (kiemTraNgayThongKe()) {

                    // Kiểm tra ngày trước phải nhỏ hơn ngày sau
                    if (tuNgay.compareTo(denNgay) > 0) { // >0 là khoảng thời gian không hợp lệ
                        sqlSession.close();
                        return CustomError.createCustomError("Thời gian không hợp lệ", 400, response);
                    }
                    // Đủ 2 điều kiện,truy vấn dữ liệu
                    List<Map<String, Object>> thongKeCustom = thongKeMapper
                            .getDataTrangThaiDatHangCustom(tuNgay, denNgay);
                    jsonRes.put("thong_ke", thongKeCustom);
                    sqlSession.close();
                    return JsonResponse.createJsonResponse(jsonRes, 200, response);
                } else {
                    List<Map<String, Object>> thongKeTrangThaiDH = thongKeMapper.getDataTrangThaiDatHang();
                    jsonRes.put("thong_ke", thongKeTrangThaiDH);
                    sqlSession.close();
                    return JsonResponse.createJsonResponse(jsonRes, 200, response);
                }
            case 2:
                List<Map<String, Object>> thongKes = thongKeMapper.top10SPDuocMuaNhieuNhat();
                jsonRes.put("thong_kes", thongKes);
                sqlSession.close();
                return JsonResponse.createJsonResponse(jsonRes, 200, response);
            default:
                return CustomError.createCustomError("Yêu cầu thống kê không hợp lệ", 403, response);
        }

    }

}
