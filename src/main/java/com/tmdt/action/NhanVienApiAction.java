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
     * D??nh cho nh??n vi??n stack
     * 
     * 
     */
    /****** Ki???m duy???t s???n ph???m ******/
    //TODO: L???y danh s??ch t???t c??? s???n ph???m ch??a duy???t
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

    //TODO: Ph?? duy???t v?? C???p nh???t tr???ng th??i cho t???ng s???n ph???m
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
                return CustomError.createCustomError("C???p nh???t tr???ng th??i s???n ph???m th??nh c??ng", 201, response);
            }
            sqlSession.close();
            return CustomError.createCustomError("M?? s???n ph???m kh??ng h???p l???", 401, response);
        }
        // Map<String, Object> map = new HashMap<String, Object>();
        // map.put("sanphams", listSanPham);
        sqlSession.close();
        return CustomError.createCustomError("Tr???ng th??i s???n ph???m kh??ng h???p l???", 401, response);
    }

    /****** Ki???m duy???t b??o c??o ng?????i d??ng ******/
    //TODO: L???y danh s??ch b??o c??o c???n ph?? duy???t
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

    //TODO: Duy???t b??o c??o ng?????i d??ng
    @Action(value = "/api/v1/admin/baocao/{maBaoCao}/duyet", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String duyetBaoCaoNguoiDung() throws IOException {
        if (status == null || noiDung == null) {
            return CustomError.createCustomError("Y??u c???u kh??ng h???p l???", 400, response);
        }
        // L???y id ng?????i nh???n
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);
        ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);

        Map<String, Object> chiTietBaoCao = baoCaoNguoiDungMapper.getBaoCao(maBaoCao);
        int idNguoiNhan = (int) chiTietBaoCao.get("id_nguoi_nhan");

        // L???y id nh??n vi??n ??ang duy???t
        int idNguoiGui = (int) session.getAttribute("accountID");

        // G??n n???i dung t??y ch???n v?? l???y n???i dung kh??c
        switch (noiDung) {
            case "1":
                noiDung = "B???n b??? t??? c??o v?? b??n s???n ph???m c???m";
                break;
            case "2":
                noiDung = "B???n b??? t??? c??o v?? l???a ?????o";
                break;
            case "3":
                noiDung = "B???n b??? t??? c??o v?? b??n h??ng gi???";
                break;
            default:
                break;
        }

        // Duy???t b??o c??o v???i tr???ng th??i c??? th??? v?? c??c ho???t ?????ng sau ????
        switch (status) {
            // N???i dung c???a th??ng b??o l?? nh??n vi??n vi???t tay (Option: (c?? nhi???u m???c c?? s???n
            // n???i dung, m???c cu???i l?? n???i dung t??? vi???t) ????? g???i cho ng?????i b??? b??o c??o)
            case -2:
                baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);
                // Set s??? l???n c???nh c??o = 3 lu??n, kh??a t??i kho???n
                baoCaoNguoiDungMapper.khoaTaiKhoan(idNguoiNhan);
                thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui, noiDung);
                sqlSession.commit();
                sqlSession.close();
                return CustomError.createCustomError("???? duy???t b??o c??o n??y v?? kh??a t??i kho???n", 401, response);
            case -1:
                baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);
                // C???nh c??o v?? t??ng s??? l???n c???nh c??o c???a t??i kho???n l??n 1 ????n v???
                baoCaoNguoiDungMapper.tangSoLanCanhBao(idNguoiNhan, 1);
                // L???y s??? l???n c???nh c??o c??n l???i bao nhi??u th?? m???i b??? kh??a t??i kho???n
                int soLanCanhCaoConLai = 3 - baoCaoNguoiDungMapper.getSoLanCanhCao(userName);

                if (soLanCanhCaoConLai == 0) {
                    thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui, "B???n b??? kh??a t??i kho???n v??: " + noiDung);
                    sqlSession.commit();
                    sqlSession.close();
                    return CustomError.createCustomError("???? duy???t b??o c??o n??y v?? kh??a t??i kho???n", 200, response);
                } else if (soLanCanhCaoConLai > 0 && soLanCanhCaoConLai < 3) {
                    thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui,
                            "B???n b??? t??? c??o v??: " + noiDung + "\r\n" +
                                    "N???u b??? c???nh c??o " + soLanCanhCaoConLai + " th?? s??? b??? kh??a t??i kho???n.");
                    sqlSession.commit();
                    sqlSession.close();
                    return CustomError.createCustomError(
                            "???? duy???t b??o c??o n??y v?? nh???c nh??? s??? l???n c???nh c??o cho ng?????i vi ph???m", 200, response);
                }
                break;
            case 1:
                baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);
                thongBaoMapper.taoThongBao(idNguoiNhan, idNguoiGui,
                        "B???n b??? t??? c??o v??: " + noiDung + "\r\n" +
                                "N???u ti???p t???c vi ph???m s??? b??? c???nh c??o, qu?? 2 l???n c???nh c??o s??? b??? kh??a t??i kho???n.");
                sqlSession.commit();
                sqlSession.close();
                return CustomError.createCustomError("???? duy???t b??o c??o n??y, kh??ng t??ng s??? l???n c???nh b??o", 200, response);
            case 2:
                baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);
                sqlSession.commit();
                sqlSession.close();
                return CustomError.createCustomError("???? duy???t b??o c??o n??y kh??ng vi ph???m", 200, response);
            default:
                return CustomError.createCustomError("Y??u c???u kh??ng h???p l???", 400, response);
        }
        return CustomError.createCustomError("Y??u c???u kh??ng h???p l???", 400, response);
    }

    //TODO: L???y th??ng tin chi ti???t b??o c??o c???n ph?? duy???t
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

    //TODO: ki???m tra ng??y th???ng k??
    public boolean kiemTraNgayThongKe() {
        return tuNgay != null && denNgay != null;
    }

    //TODO: D??ng 1 action cho th???ng k?? c???a qu???n tr??? vi??n
    @Action(value = "/api/v1/admin/thongke/{status}", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "nhanVienStack"),
    })
    public String xuLyThongKe() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("Status Th???ng k??  = " + status);
        ThongKeMapper thongKeMapper = sqlSession.getMapper(ThongKeMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        /*
         * 0 l?? th???ng k?? 4 lo???i d??? li???u ????n gi???n
         * 1 l?? ?????m s??? l?????ng ????n ?????t h??ng theo 4 tr???ng th??i (b??? h???y, ??ang ch??? ti???p nh???n,
         * ??ang giao, ???? giao)
         */
        switch (status) {
            case 0:
                Map<String, Object> thongKe_4Data = thongKeMapper.get4DataThongKe();
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
                return CustomError.createCustomError("Y??u c???u th???ng k?? kh??ng h???p l???", 403, response);
        }

    }

}
