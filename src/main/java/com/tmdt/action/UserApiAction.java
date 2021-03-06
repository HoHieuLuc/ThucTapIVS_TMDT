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
    private String lyDo;

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

    public String getLyDo() {
        if (lyDo == null || lyDo.equals("")) {
            return "Kh??ng c?? l?? do";
        }
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
    // endregion

    /* validate */
    //TODO: validate ???nh (?????nh d???ng t???p ???nh)
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

    // validate to??n b??? input
    public boolean isValid() {
        return tenSanPham != null && tenSanPham.length() > 0 &&
                tenSanPham.length() <= 255 &&
                moTa != null && moTa.length() > 0 &&
                gia > 0 && soLuong > 0;
    }

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    //TODO: action l???y danh s??ch s???n ph???m
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

    //TODO: action l???y chi ti???t s???n ph???m
    @Action(value = "/api/v1/user/sanpham/{maSanPham}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getChiTietSanPhamByMaKH() throws IOException {
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        Map<String, Object> sanPham = sanPhamMapper.getSanPhamByMaKHAndMaSP(maKhachHang, maSanPham);
        if (sanPham == null) {
            return CustomError.createCustomError("Kh??ng t??m th???y s???n ph???m", 404, response);
        }
        AnhSanPhamMapper anhSanPhamMapper = sqlSession.getMapper(AnhSanPhamMapper.class);
        List<String> listAnhSanPham = anhSanPhamMapper.getAllAnhSanPham(maSanPham);

        Map<String, Object> jsonRes = new HashMap<String, Object>();
        sanPham.put("anhSanPhams", listAnhSanPham);
        jsonRes.put("sanpham", sanPham);

        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: action th??m s???n ph???m
    @Action(value = "/api/v1/user/sanpham/them", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String createSanPham() throws IOException {
        if (!isValid() || !validateAnhSanPham()) {
            return CustomError.createCustomError("D??? li???u kh??ng h???p l???", 400, response);
        }
        if (anhSanPhams.size() > 5) {
            return CustomError.createCustomError("S??? l?????ng ???nh kh??ng ???????c v?????t qu?? 5", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        AnhSanPhamMapper anhSanPhamMapper = sqlSession.getMapper(AnhSanPhamMapper.class);
        int maKhachHang = (int) session.getAttribute("maNguoiDung");

        // ki???m tra lo???i s???n ph???m c?? h???p l??? hay kh??ng
        if (loaiSanPhamMapper.isLoaiSanPhamCapThap(maLoaiSanPham) == 0) {
            return CustomError.createCustomError("Lo???i s???n ph???m kh??ng h???p l???", 400, response);
        }

        // ki???m tra xem t??n s???n ph???m c?? b??? tr??ng kh??ng
        // ch??? ki???m tra s???n ph???m m?? kh??ch h??ng s??? h???u
        if (sanPhamMapper.countSanPhamByMaKHAndTenSP(maKhachHang, tenSanPham) > 0) {
            return CustomError.createCustomError("T??n s???n ph???m ???? t???n t???i", 409, response);
        }

        String filePath = session.getServletContext().getRealPath("/") + "images\\product\\";
        String LocalPath = ProjectPath.getPath() + "\\images\\product\\";

        SanPham sanPham = new SanPham(tenSanPham, maKhachHang, moTa, gia, 0, maLoaiSanPham, soLuong, 0);
        String insertedId = "";
        try {
            sanPhamMapper.insertSanPham(sanPham);
            sqlSession.commit();
            // khi th??m s???n ph???m th??nh c??ng th?? m???i b???t ?????u th??m ???nh
            insertedId = sanPhamMapper.getIdSanPhamByMaKHAndTenSP(maKhachHang, tenSanPham);
            for (int i = 0; i < anhSanPhams.size(); i++) {
                String fileNameNew = System.currentTimeMillis() + "_" + anhSanPhamsFileName.get(i);
                File fileTmp = new File(filePath + fileNameNew); // file ???nh ???????c l??u t???m
                File fileNew = new File(LocalPath + fileNameNew); // file ???nh l??u v??o th?? m???c project
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
        jsonRes.put("message", insertedId);
        return JsonResponse.createJsonResponse(jsonRes, 201, response);
    }

    //TODO: action c???p nh???t s???n ph???m
    @Action(value = "/api/v1/user/sanpham/{maSanPham}/edit", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String editSanPham() throws IOException {
        if (!isValid()) {
            return CustomError.createCustomError("D??? li???u kh??ng h???p l???", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        int numb = sanPhamMapper.updateSanPhamInfo(maSanPham, maKhachHang, tenSanPham, moTa, gia, soLuong);
        if (numb == 0) {
            return CustomError.createCustomError("S???n ph???m kh??ng t???n t???i", 404, response);
        }
        sqlSession.commit();
        sqlSession.close();
        return CustomError.createCustomError("C???p nh???t s???n ph???m th??nh c??ng", 200, response);
    }

    //TODO: c???p nh???t t??nh tr???ng s???n ph???m
    @Action(value = "/api/v1/user/sanpham/changestatus", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String changeStatus() throws IOException {
        if (status < -1 || status > 1) {
            return CustomError.createCustomError("Y??u c???u kh??ng h???p l???", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
        int maKhachHang = (int) session.getAttribute("maNguoiDung");
        Integer statusHienTai = sanPhamMapper.getStatusFromSanPham(maSanPham, maKhachHang);
        String message = "";
        // khi s???n ph???m kh??ng thu???c v??? kh??ch h??ng ho???c ko t???n t???i th?? status = null
        if (statusHienTai == null) {
            return CustomError.createCustomError("Kh??ng t??m th???y s???n ph???m", 404, response);
        }
        // n???u status hi???n t???i = 0 (c??n trong kho)
        // v?? status ng?????i d??ng ????a qua l?? -1 ho???c 1 th?? m???i c???p nh???t
        if (statusHienTai == 0 && status != 0) {
            sanPhamMapper.updateSanPhamStatus(status, maSanPham);
            if (status == 1) {
                message = "B???n ???? g???i y??u c???u duy???t s???n ph???m";
            } else {
                message = "X??a s???n ph???m th??nh c??ng";
            }
        }
        // ??ang y??u c???u duy???t th?? n??t duy nh???t l?? h???y y??u c???u
        // t???c chuy???n status v??? l???i 0
        else if (statusHienTai == 1) {
            sanPhamMapper.updateSanPhamStatus(0, maSanPham);
            message = "B???n ???? h???y y??u c???u duy???t s???n ph???m";
        }
        // ??ang b??n th?? n??t duy nh???t l?? h???y b??n
        // t???c chuy???n status v??? l???i 0
        else if (statusHienTai == 2) {
            message = "B???n ???? ng???ng b??n s???n ph???m";
            sanPhamMapper.updateSanPhamStatus(0, maSanPham);
        }
        sqlSession.commit();
        sqlSession.close();
        return CustomError.createCustomError(message, 200, response);
    }

    // ki???m tra ng??y th???ng k??
    public boolean kiemTraNgayThongKe() {
        return tuNgay != null && denNgay != null;
    }

    // SELLER
    //TODO: l???y danh s??ch ?????t h??ng m?? ng?????i kh??c ?????t mua c???a user n??y
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

    //TODO: l???y chi ti???t ????n ?????t h??ng (SELLER)
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
            return CustomError.createCustomError("Kh??ng t??m th???y ????n ?????t h??ng", 404, response);
        }
        sqlSession.close();
        jsonRes.put("thongTinNguoiDat", thongTinNguoiDat);
        jsonRes.put("chiTietDatHang", chiTietDatHang);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: c???p nh???t t??nh tr???ng ????n ?????t h??ng cho ng?????i b??n h??ng
    @Action(value = "/api/v1/user/seller/dathang/{id}/capnhat", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String capNhatTinhTrangDonDatHang() throws IOException {
        if (status < -1 || status > 1) {
            return CustomError.createCustomError("Y??u c???u kh??ng h???p l???", 400, response);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maNguoiBan = (int) session.getAttribute("maNguoiDung");
        Map<String, Object> chiTietDatHang = datHangMapper.getChiTietDatHang(id, maSanPham, maNguoiBan);
        if ((int) chiTietDatHang.get("status") == 2) {
            return CustomError.createCustomError("????n ?????t h??ng ???? ???????c nh???n", 403, response);
        }
        int update = datHangMapper.capNhatTinhTrangChiTietDatHangNguoiBan(status, id, maSanPham, maNguoiBan);
        if (update == 0) {
            return CustomError.createCustomError("S??? l?????ng h??ng kh??ng ?????", 403, response);
        }
        int soLuongDatHang = (int) chiTietDatHang.get("so_luong");
        // c???p nh???t s??? l?????ng s???n ph???m c??n l???i khi chuy???n sang giai ??o???n chuy???n h??ng
        if (status == 1) {
            SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
            sanPhamMapper.updateSoLuongSanPham(maSanPham, maNguoiBan, soLuongDatHang);
        }
        if (status == -1 || status == 0) {
            ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);
            Map<String, Object> thongTinNguoiMua = datHangMapper.getIdTaiKhoanNguoiMua(id, maSanPham);
            int idNguoiMua = (int) thongTinNguoiMua.get("id");
            String _tenSanPham = (String) thongTinNguoiMua.get("ten_san_pham");
            String ngayDat = (String) thongTinNguoiMua.get("ngay_dat");
            String action = "";
            if (status == -1) {
                action = "???? b??? h???y";
            } else {
                // c???p nh???t s??? l?????ng s???n ph???m khi ????n b??? ng???ng v???n chuy???n
                SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
                sanPhamMapper.updateSoLuongSanPham(maSanPham, maNguoiBan, -soLuongDatHang);
                action = "???? b??? ng???ng v???n chuy???n";
            }
            String noiDung = "S???n ph???m <b>" + _tenSanPham + "</b> do b???n ?????t ng??y " + ngayDat + " " + action
                    + ".\r\nL?? do:\r\n"
                    + getLyDo();
            thongBaoMapper.taoThongBao(idNguoiMua, -1, noiDung);
        }
        sqlSession.commit();
        sqlSession.close();
        jsonRes.put("message", "C???p nh???t t??nh tr???ng ????n ?????t h??ng th??nh c??ng");
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    // BUYER
    //TODO: l???y danh s??ch ????n ?????t h??ng cho ng?????i mua
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

    //TODO: l???y chi ti???t 1 ????n ?????t h??ng c???a ng?????i mua
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
            return CustomError.createCustomError("Kh??ng t??m th???y ????n ?????t h??ng", 404, response);
        }
        sqlSession.close();
        jsonRes.put("chiTietDatHang", chiTietDatHang);
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: thay ?????i t??nh tr???ng ????n h??ng cho ng?????i mua
    @Action(value = "/api/v1/user/buyer/dathang/{id}/{maSanPham}/capnhat", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String capNhatTinhTrangDonDatHangChoNguoiMua() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maNguoiMua = (int) session.getAttribute("maNguoiDung");
        Map<String, Object> chiTietDatHang = datHangMapper.getChiTietDatHangChoNguoiMua(maNguoiMua, id, maSanPham);
        // kh??ng t??m th???y status t???c l?? chi ti???t ?????t h??ng ko t???n t???i
        // ho???c chi ti???t ?????t h??ng ???? ko ph???i c???a ng?????i mua
        // status hi???n t???i = 1 l?? ??ang v???n chuy???n, m??nh ch??? cho 1 n??t duy nh???t l?? "????
        // nh???n ???????c h??ng"
        // suy ra c???p nh???t status = 2
        // status hi???n t???i l?? 0 l?? ??ang ch???, l??c n??y m??nh c?? th??? h???y
        if (chiTietDatHang == null) {
            return CustomError.createCustomError("Kh??ng t??m th???y ????n ?????t h??ng", 404, response);
        } else if ((int) chiTietDatHang.get("status") == 1) {
            if (!maNhanHang.equals(chiTietDatHang.get("ma_nhan_hang"))) {
                return CustomError.createCustomError("M?? nh???n h??ng kh??ng ch??nh x??c", 401, response);
            }
            status = 2;
            int soLuongDatHang = (int) chiTietDatHang.get("so_luong");
            SanPhamMapper sanPhamMapper = sqlSession.getMapper(SanPhamMapper.class);
            sanPhamMapper.updateSoLuongDaBan(maSanPham, soLuongDatHang);
        } else if ((int) chiTietDatHang.get("status") == 0) {
            status = -1;
        }
        int update = datHangMapper.capNhatTinhTrangChiTietDatHangNguoiMua(maNguoiMua, id, maSanPham, status);
        // c??i n??y ki???m tra lu??n cho ch???c
        if (update == 0) {
            return CustomError.createCustomError("Kh??ng t??m th???y ????n ?????t h??ng", 404, response);
        }
        if (status == -1) {
            ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);
            Map<String, Object> thongTinNguoiBan = datHangMapper.getIdTaiKhoanNguoiBan(id, maSanPham);
            int idNguoiBan = (int) thongTinNguoiBan.get("id");
            String tenNguoiMua = (String) session.getAttribute("ten");
            String _tenSanPham = (String) thongTinNguoiBan.get("ten_san_pham");
            String ngayDat = (String) thongTinNguoiBan.get("ngay_dat");
            String noiDung = "S???n ph???m <b>" + _tenSanPham + "</b> do <u><i>" +
                    tenNguoiMua + "</i></u> ?????t ng??y " +
                    ngayDat + " ???? b??? h???y.\r\nL?? do:\r\n" + getLyDo();
            thongBaoMapper.taoThongBao(idNguoiBan, -1, noiDung);
        }
        sqlSession.commit();
        sqlSession.close();
        jsonRes.put("message", "C???p nh???t t??nh tr???ng ????n ?????t h??ng th??nh c??ng");
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: th???ng k?? s??? ????n ?????t h??ng c???a kh??ch h??ng
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
                return CustomError.createCustomError("Th???i gian kh??ng h???p l???", 400, response);
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

    //TODO: D??ng 1 action cho th???ng k?? 4 lo???i d??? li???u ????n gi???n v?? th???ng k?? t??nh tr???ng ?????t h??ng
    @Action(value = "/api/v1/user/thongke/{status}", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String xuLyThongKe() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("Status Th???ng k??  = " + status);
        ThongKeMapper thongKeMapper = sqlSession.getMapper(ThongKeMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        int maNguoiBan = (int) session.getAttribute("maNguoiDung");

        /*
         * 0 l?? th???ng k?? 4 lo???i d??? li???u ????n gi???n
         * 1 l?? ?????m s??? l?????ng ????n ?????t h??ng theo 4 tr???ng th??i (b??? h???y, ??ang ch??? ti???p nh???n,
         * ??ang giao, ???? giao)
         */

        switch (status) {
            case 0:
                Map<String, Object> thongKe_4Data = thongKeMapper.get3DataThongKeUser(maNguoiBan);
                jsonRes.put("thong_ke", thongKe_4Data);
                sqlSession.close();
                return JsonResponse.createJsonResponse(jsonRes, 200, response);
            case 1:
                // Check null cho 2 gi?? tr??? ng??y
                if (kiemTraNgayThongKe()) {
                    // Ki???m tra ng??y tr?????c ph???i nh??? h??n ng??y sau
                    if (tuNgay.compareTo(denNgay) > 0) { // >0 l?? kho???ng th???i gian kh??ng h???p l???
                        sqlSession.close();
                        return CustomError.createCustomError("Th???i gian kh??ng h???p l???", 400, response);
                    }
                    // ????? 2 ??i???u ki???n,truy v???n d??? li???u
                    List<Map<String, Object>> thongKeCustom = thongKeMapper
                            .getDataTrangThaiDatHangCustomUser(tuNgay,
                                    denNgay,
                                    maNguoiBan);
                    jsonRes.put("thong_ke", thongKeCustom);
                    sqlSession.close();
                    return JsonResponse.createJsonResponse(jsonRes, 200, response);
                } else {
                    List<Map<String, Object>> thongKe = thongKeMapper
                            .getDataTrangThaiDatHangUser(maNguoiBan);
                    jsonRes.put("thong_ke", thongKe);
                    sqlSession.close();
                    return JsonResponse.createJsonResponse(jsonRes, 200, response);
                }
            case 2:
                List<Map<String, Object>> thongKes = thongKeMapper.top10SPDuocMuaNhieuNhatUserByMonth(maNguoiBan);
                jsonRes.put("thong_kes", thongKes);
                sqlSession.close();
                return JsonResponse.createJsonResponse(jsonRes, 200, response);
            default:
                return CustomError.createCustomError("Y??u c???u th???ng k?? kh??ng h???p l???", 403, response);
        }
    }
}
