package com.tmdt.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class UserApiAction extends ActionSupport {
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
    private Date tuNgay;
    private Date denNgay;
    private int id;
    private String maNhanHang;

    // region Getter and Setter
    public String getMaSanPham() {
        return maSanPham;
    }

    public int getStatus() {
        switch (status) {
            case -1:
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
                return 10;
        }
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaNhanHang() {
        return maNhanHang;
    }

    public void setMaNhanHang(String maNhanHang) {
        this.maNhanHang = maNhanHang;
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

    // action lấy danh sách sản phẩm
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

        List<Map<String, Object>> listSanPham = sanPhamMapper.getSanPhamByMaKH(maKhachHang, _search, _status, offset,
                _rowsPerPage);
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
        if (sanPham == null) {
            return CustomError.createCustomError("Không tìm thấy sản phẩm", 404, response);
        }
        AnhSanPhamMapper anhSanPhamMapper = sqlSession.getMapper(AnhSanPhamMapper.class);
        List<String> listAnhSanPham = anhSanPhamMapper.getAllAnhSanPham(maSanPham);

        Map<String, Object> jsonRes = new HashMap<String, Object>();
        sanPham.put("anhSanPhams", listAnhSanPham);
        jsonRes.put("sanpham", sanPham);

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
        if (loaiSanPhamMapper.isLoaiSanPhamCapThap(maLoaiSanPham) == 0) {
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

    // cập nhật tình trạng sản phẩm
    @Action(value = "/api/v1/user/sanpham/changestatus", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String changeStatus() throws IOException {
        if (status < -1 || status > 1) {
            return CustomError.createCustomError("Yêu cầu không hợp lệ", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        Integer statusHienTai = sanPhamMapper.getStatusFromSanPham(maSanPham, maKhachHang);
        String message = "";
        // khi sản phẩm không thuộc về khách hàng hoặc ko tồn tại thì status = null
        if (statusHienTai == null) {
            return CustomError.createCustomError("Không tìm thấy sản phẩm", 404, response);
        }
        // nếu status hiện tại = 0 (còn trong kho)
        // và status người dùng đưa qua là -1 hoặc 1 thì mới cập nhật
        if (statusHienTai == 0 && status != 0) {
            sanPhamMapper.updateSanPhamStatus(status, maSanPham);
            if (status == 1) {
                message = "Bạn đã gửi yêu cầu duyệt sản phẩm";
            } else {
                message = "Xóa sản phẩm thành công";
            }
        }
        // đang yêu cầu duyệt thì nút duy nhất là hủy yêu cầu
        // tức chuyển status về lại 0
        else if (statusHienTai == 1) {
            sanPhamMapper.updateSanPhamStatus(0, maSanPham);
            message = "Bạn đã hủy yêu cầu duyệt sản phẩm";
        }
        // đang bán thì nút duy nhất là hủy bán
        // tức chuyển status về lại 0
        else if (statusHienTai == 2) {
            message = "Bạn đã ngừng bán sản phẩm";
            sanPhamMapper.updateSanPhamStatus(0, maSanPham);
        }
        sqlSession.commit();
        sqlSession.close();
        return CustomError.createCustomError(message, 200, response);
    }

    // kiểm tra ngày thống kê
    public boolean kiemTraNgayThongKe() {
        return tuNgay != null && denNgay != null;
    }

    // thống kê số đơn đặt hàng của khách hàng
    @Action(value = "/api/v1/user/thongke/dathang", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String thongKeDonDat() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ThongKeMapper thongKeMapper = sqlSession.getMapper(ThongKeMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        List<Map<String, Object>> thongKe = new ArrayList<>();

        if (kiemTraNgayThongKe()) {
            if (tuNgay.compareTo(denNgay) > 0) {
                return CustomError.createCustomError("Thời gian không hợp lệ", 400, response);
            }
            thongKe = thongKeMapper.thongKeSoDonDatHangChoKhachHangTuyChon(maKhachHang, tuNgay, denNgay);
            jsonRes.put("trongThang", false);
        } else {
            thongKe = thongKeMapper.thongKeSoDonDatHangChoKhachHangTrongThang(maKhachHang);
            jsonRes.put("trongThang", true);
        }
        sqlSession.close();
        jsonRes.put("soDonDatHang", thongKe);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // SELLER
    // lấy danh sách đặt hàng mà người khác đặt của user này
    @Action(value = "/api/v1/user/seller/dathang", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String hangDuocDat() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maNguoiBan = (int) session.getAttribute("maNguoiDung");

        int _page = getPage();
        int _rowsPerPage = getRowsPerPage();
        String _search = getSearch();
        int _status = getStatus();

        int countDatHang = datHangMapper.countDonDatHangDuocDat(maNguoiBan, _status, _search);
        int offset = (_page - 1) * _rowsPerPage;
        int totalPage = (int) Math.ceil(countDatHang / (double) _rowsPerPage);

        List<Map<String, Object>> danhSachDatHang = datHangMapper.getDonDatHangDuocDat(maNguoiBan,
                _status, _search, offset, _rowsPerPage);
        sqlSession.close();
        jsonRes.put("danhSachDatHang", danhSachDatHang);
        jsonRes.put("totalPages", totalPage);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // lấy chi tiết đơn đặt hàng
    @Action(value = "/api/v1/user/seller/dathang/{id}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String chiTietDonDatHang() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maNguoiBan = (int) session.getAttribute("maNguoiDung");
        Map<String, Object> thongTinNguoiDat = datHangMapper.getThongTinNguoiDatHang(id);
        List<Map<String, Object>> chiTietDatHang = datHangMapper.getChiTietDonDatHangDuocDat(maNguoiBan, id);
        if (chiTietDatHang.isEmpty()) {
            return CustomError.createCustomError("Không tìm thấy đơn đặt hàng", 404, response);
        }
        sqlSession.close();
        jsonRes.put("thongTinNguoiDat", thongTinNguoiDat);
        jsonRes.put("chiTietDatHang", chiTietDatHang);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // cập nhật tình trạng đơn đặt hàng cho người bán hàng
    @Action(value = "/api/v1/user/seller/dathang/{id}/capnhat", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String capNhatTinhTrangDonDatHang() throws IOException {
        // người bán hàng ko thể cập nhật status thành 2
        // status 2 là người mua đã nhận hàng, người mua sẽ cập nhật
        if (status < -1 || status > 1) {
            return CustomError.createCustomError("Yêu cầu không hợp lệ", 400, response);
        }
        // TODO: nếu status = 1 (đang ship) thì cập nhật lại số lượng sản phẩm luôn
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maNguoiBan = (int) session.getAttribute("maNguoiDung");
        if (datHangMapper.getCurrentStatusChiTietDatHangSeller(id, maSanPham, maNguoiBan) == 2) {
            return CustomError.createCustomError("Đơn đặt hàng đã được nhận", 403, response);
        }
        int update = datHangMapper.capNhatTinhTrangChiTietDatHangNguoiBan(status, id, maSanPham, maNguoiBan);
        if (update == 0) {
            return CustomError.createCustomError("Không tìm thấy đơn đặt hàng", 404, response);
        }
        // TODO: sau khi cập nhật đơn đặt hàng thành công sẽ gửi thông báo đến người mua
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // BUYER
    // lấy danh sách đơn đặt hàng cho người mua
    @Action(value = "/api/v1/user/buyer/dathang", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String hangDaDat() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maNguoiMua = (int) session.getAttribute("maNguoiDung");

        int _page = getPage();
        int _rowsPerPage = getRowsPerPage();
        String _search = getSearch();
        int _status = getStatus();

        int countDatHang = datHangMapper.countDonDatHangChoNguoiMua(maNguoiMua, _status, _search);
        int offset = (_page - 1) * _rowsPerPage;
        int totalPage = (int) Math.ceil(countDatHang / (double) _rowsPerPage);

        List<Map<String, Object>> danhSachDatHang = datHangMapper.getDonDatHangChoNguoiMua(maNguoiMua,
                _status, _search, offset, _rowsPerPage);
        sqlSession.close();
        jsonRes.put("danhSachDatHang", danhSachDatHang);
        jsonRes.put("totalPages", totalPage);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // lấy chi tiết 1 đơn đặt hàng
    @Action(value = "/api/v1/user/buyer/dathang/{id}/{maSanPham}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String chiTietDonDatHangChoNguoiMua() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maNguoiMua = (int) session.getAttribute("maNguoiDung");
        Map<String, Object> chiTietDatHang = datHangMapper.getChiTietDonDatHangChoNguoiMua(
                maNguoiMua, id, maSanPham);
        if (chiTietDatHang == null) {
            return CustomError.createCustomError("Không tìm thấy đơn đặt hàng", 404, response);
        }
        sqlSession.close();
        jsonRes.put("chiTietDatHang", chiTietDatHang);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // thay đổi tình trạng đơn hàng cho người mua
    @Action(value = "/api/v1/user/buyer/dathang/{id}/{maSanPham}/capnhat", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String capNhatTinhTrangDonDatHangChoNguoiMua() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maNguoiMua = (int) session.getAttribute("maNguoiDung");
        Map<String, Object> currentStatus = datHangMapper.getStatusAndMaNhanHangCTDHNguoiMua(maNguoiMua, id, maSanPham);
        // không tìm thấy status tức là chi tiết đặt hàng ko tồn tại
        // hoặc chi tiết đặt hàng đó ko phải của người mua
        // status hiện tại = 1 là đang vận chuyển, mình chỉ cho 1 nút duy nhất là "đã
        // nhận được hàng"
        // suy ra cập nhật status = 2
        // status hiện tại là 0 là đang chờ, lúc này mình có thể hủy
        if (currentStatus == null) {
            return CustomError.createCustomError("Không tìm thấy đơn đặt hàng", 404, response);
        } else if ((int) currentStatus.get("status") == 1) {
            if (!maNhanHang.equals(currentStatus.get("ma_nhan_hang"))) {
                return CustomError.createCustomError("Mã nhận hàng không chính xác", 401, response);
            }
            status = 2;
        } else if ((int) currentStatus.get("status") == 0) {
            status = -1;
        }
        int update = datHangMapper.capNhatTinhTrangChiTietDatHangNguoiMua(maNguoiMua, id, maSanPham, status);
        // cái này kiểm tra luôn cho chắc
        if (update == 0) {
            return CustomError.createCustomError("Không tìm thấy đơn đặt hàng", 404, response);
        }
        // TODO: sau khi cập nhật đơn đặt hàng thành công sẽ gửi thông báo đến người bán
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

        // Dùng 1 action cho thống kê 4 loại dữ liệu đơn giản và thống kê tình trạng đặt hàng
        @Action(value = "/api/v1/nhanvien/thongke/{status}", results = {
            @Result(name = "success", location = "/index.html")
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
                ArrayList<Integer> thongKeTrangThaiDH = thongKeMapper.getDataTrangThaiDatHang();
                jsonRes.put("thong_ke", thongKeTrangThaiDH);
                sqlSession.close();
                return JsonResponse.createJsonResponse(jsonRes, 200, response);
            case 2:
                // Check null cho 2 giá trị ngày
                if ( kiemTraNgayThongKe() ){

                    // Kiểm tra ngày trước phải nhỏ hơn ngày sau
                    if (tuNgay.compareTo(denNgay) > 0){ //>0 là khoảng thời gian không hợp lệ
                        sqlSession.close();
                        return CustomError.createCustomError("Thời gian không hợp lệ",400,response);
                    }
                    // Đủ 2 điều kiện,truy vấn dữ liệu
                     ArrayList<Integer> thongKeCustom = thongKeMapper.getDataTrangThaiDatHangCustom(tuNgay, denNgay);
                     jsonRes.put("thong_ke", thongKeCustom);
                     sqlSession.close();
                     return JsonResponse.createJsonResponse(jsonRes, 200, response);
                }
                return CustomError.createCustomError("Yêu cầu thống kê không hợp lệ", 403, response);
            case 3: 
                List<Map<String, Object>> thongKes = thongKeMapper.top10SPDuocMuaNhieuNhat();
                jsonRes.put("thong_kes",thongKes);
                sqlSession.close();
                return JsonResponse.createJsonResponse(jsonRes, 200, response);
            default:
                return CustomError.createCustomError("Yêu cầu thống kê không hợp lệ", 403, response);
        }


    }

  

}
