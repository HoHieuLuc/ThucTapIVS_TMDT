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
import mybatis.mapper.TaiKhoanMapper;
import mybatis.mapper.ThongBaoMapper;
import mybatis.mapper.ThongKeMapper;

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
                    thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui, "Bạn bị khóa tài khoản vì " + noiDung);
                    sqlSession.commit();
                    sqlSession.close();
                    return CustomError.createCustomError("Đã duyệt báo cáo này và khóa tài khoản", 200, response);
                } else if (soLanCanhCaoConLai > 0 && soLanCanhCaoConLai < 3) {
                    thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui,
                            "Nếu bị cảnh cáo " + soLanCanhCaoConLai + " thì bị khóa tài khoản vì" + noiDung);
                    sqlSession.commit();
                    sqlSession.close();
                    return CustomError.createCustomError(
                            "Đã duyệt báo cáo này và nhắc nhở số lần cảnh cáo cho người vi phạm", 200, response);
                }
                break;
            case 1:
                baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);
                thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui,
                        noiDung + ". Nếu tiếp tục vi phạm sẽ bị cảnh cáo, quá 2 lần cảnh cáo sẽ khóa tài khoản");
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

    // Lấy thông tin chi tiết báo cáo cần phê duyệt
    @Action(value = "/api/v1/nhanvien/baocao/{maBaoCao}", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String getChiTietBaoCao() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("This is chi tiet bao cao");
        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);

        Map<String, Object> chiTietBaoCao = baoCaoNguoiDungMapper.detaiBaoCao(maBaoCao);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        jsonRes.put("chitiet_baocao", chiTietBaoCao);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // kiểm tra ngày thống kê
    public boolean kiemTraNgayThongKe() {
        return tuNgay != null && denNgay != null;
    }

    // Dùng 1 action cho thống kê
    @Action(value = "/api/v1/nhanvien/thongke/{status}", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String xuLyThongKe() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("Status Thống kê  = " + status);
        ThongKeMapper thongKeMapper = sqlSession.getMapper(ThongKeMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        Map<String, Object> thongKe = thongKeMapper.get4DataThongKe();



        /*
         * 0 là thống kê 4 loại dữ liệu đơn giản
         * 1 là đếm số lượng đơn đặt hàng theo 4 trạng thái (bị hủy, đang chờ tiếp nhận,
         * đang giao, đã giao)
         */
        switch (status) {
            case 0:
                thongKe = thongKeMapper.get4DataThongKe();
                break;
            case 1:
                thongKe = thongKeMapper.getDataTrangThaiDatHang();
                break;
            case 2:
                // Check null cho 2 giá trị ngày
                if ( kiemTraNgayThongKe() ){

                    // Kiểm tra ngày trước phải nhỏ hơn ngày sau
                    if (tuNgay.compareTo(denNgay) > 0){ //>0 là khoảng thời gian không hợp lệ
                        sqlSession.close();
                        return CustomError.createCustomError("Thời gian không hợp lệ",400,response);
                    }
                    // Đủ 2 điều kiện,truy vấn dữ liệu
                    thongKe = thongKeMapper.getDataTrangThaiDatHangByMonth(tuNgay, denNgay);
                }
                break;
            default:
                return CustomError.createCustomError("Yêu cầu thống kê không hợp lệ", 403, response);
        }

        jsonRes.put("thong_ke", thongKe);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

}
