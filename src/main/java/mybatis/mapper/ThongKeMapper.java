package mybatis.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ThongKeMapper {
    /*****
     * 
     * Dành cho khách hàng
     */
    // thống kê số lượng đơn đặt hàng cho khách hàng
    final String THONG_KE_SO_DON_DAT_HANG_CHO_KHACH_HANG_TRONG_THANG = "SELECT " +
            "COUNT(DISTINCT dh.ma_dat_hang) AS so_luong, DATE(dh.ngay_dat) AS ngay " +
            "FROM khach_hang kh JOIN san_pham sp ON sp.ma_khach_hang = kh.ma_khach_hang " +
            "JOIN chi_tiet_dat_hang ctdh ON ctdh.ma_san_pham = sp.ma_san_pham " +
            "JOIN dat_hang dh ON dh.ma_dat_hang = ctdh.ma_dat_hang " +
            "WHERE kh.ma_khach_hang = #{maKhachHang} " +
            "AND MONTH(dh.ngay_dat) = MONTH(NOW()) " +
            "GROUP BY DATE(dh.ngay_dat)";

    @Select(THONG_KE_SO_DON_DAT_HANG_CHO_KHACH_HANG_TRONG_THANG)
    public List<Map<String, Object>> thongKeSoDonDatHangChoKhachHangTrongThang(int maKhachHang);

    // thống kê số lượng đơn đặt hàng theo thời gian tùy chọn
    final String THONG_KE_SO_DON_DAT_HANG_CHO_KHACH_HANG_TUY_CHON = "SELECT " +
            "COUNT(DISTINCT dh.ma_dat_hang) AS so_luong, DATE(dh.ngay_dat) AS ngay " +
            "FROM khach_hang kh JOIN san_pham sp ON sp.ma_khach_hang = kh.ma_khach_hang " +
            "JOIN chi_tiet_dat_hang ctdh ON ctdh.ma_san_pham = sp.ma_san_pham " +
            "JOIN dat_hang dh ON dh.ma_dat_hang = ctdh.ma_dat_hang " +
            "WHERE kh.ma_khach_hang = 6 AND (DATE(dh.ngay_dat) BETWEEN #{tuNgay} AND #{denNgay}) " +
            "GROUP BY DATE(dh.ngay_dat)";

    @Select(THONG_KE_SO_DON_DAT_HANG_CHO_KHACH_HANG_TUY_CHON)
    public List<Map<String, Object>> thongKeSoDonDatHangChoKhachHangTuyChon(
            @Param("maKhachHang") int maKhachHang,
            @Param("tuNgay") Date tuNgay,
            @Param("denNgay") Date denNgay);

    // Note để nhớ vị trí
    // 4 truy vấn để lấy tổng số đánh giá sản phẩm, tổng số sản phẩm, tổng số thành
    // viên, tổng số đơn đặt hàng
    final String GET_3_DATA_THONG_KE_USER = "SELECT " +
            "(SELECT COUNT(ma_danh_gia) " +
            "FROM danh_gia_san_pham dgsp JOIN san_pham sp on dgsp.ma_san_pham = sp.ma_san_pham " +
            "WHERE sp.ma_khach_hang = #{maNguoiBan}) AS so_danh_gia, " +
            "(SELECT COUNT(ma_san_pham) " +
            "FROM san_pham sp JOIN khach_hang kh ON sp.ma_khach_hang = kh.ma_khach_hang " +
            "WHERE kh.ma_khach_hang = #{maNguoiBan}) AS so_san_pham, " +
            "(SELECT COUNT(ctdh.ma_dat_hang) " +
            "FROM chi_tiet_dat_hang ctdh JOIN san_pham sp ON ctdh.ma_san_pham = sp.ma_san_pham " +
            "WHERE sp.ma_khach_hang = #{maNguoiBan}) AS so_don_dat_hang";

    @Select(GET_3_DATA_THONG_KE_USER)
    public Map<String, Object> get3DataThongKeUser(int maNguoiBan);

    // Dành cho Vẽ đồ thị tròn biểu diễn trạng thái của từng chi tiết đơn đặt hàng
    // theo tháng cụ thể
    final String GET_DATA_TRANG_THAI_DAT_HANG_CUSTOM_USER = "SELECT COUNT(*) AS so_luong, ctdh.status " +
            "FROM chi_tiet_dat_hang ctdh JOIN san_pham sp ON " +
            "ctdh.ma_san_pham = sp.ma_san_pham " +
            "JOIN dat_hang dh ON ctdh.ma_dat_hang = dh.ma_dat_hang " +
            "WHERE sp.ma_khach_hang = #{maNguoiBan} AND DATE(dh.ngay_dat) BETWEEN #{tuNgay} AND #{denNgay}" +
            "GROUP BY ctdh.status;";

    @Select(GET_DATA_TRANG_THAI_DAT_HANG_CUSTOM_USER)
    public List<Map<String, Object>> getDataTrangThaiDatHangCustomUser(
            @Param("tuNgay") Date tuNgay,
            @Param("denNgay") Date denNgay,
            @Param("maNguoiBan") int maNguoiBan);

    // Dành cho Vẽ đồ thị tròn biểu diễn trạng thái của từng chi tiết đơn đặt hàng
    final String GET_DATA_TRANG_THAI_DAT_HANG_USER = "SELECT COUNT(*) AS so_luong, ctdh.status " +
            "FROM chi_tiet_dat_hang ctdh JOIN san_pham sp " +
            "ON ctdh.ma_san_pham = sp.ma_san_pham JOIN dat_hang dh ON ctdh.ma_dat_hang = dh.ma_dat_hang " +
            "WHERE sp.ma_khach_hang = #{maNguoiBan} " +
            "AND MONTH(dh.ngay_dat) = MONTH(NOW()) " +
            "GROUP BY ctdh.status;";

    @Select(GET_DATA_TRANG_THAI_DAT_HANG_USER)
    public List<Map<String, Object>> getDataTrangThaiDatHangUser(int maNguoiBan);

    // Top 10 sản phẩm được mua nhiều nhất
    final String TOP_10_SP_DUOC_MUA_NHIEU_NHAT_USER_BY_MONTH = "SELECT lsp.ten_loai_sp,sp.ten_san_pham, " +
            "COUNT(ctdh.ma_san_pham) as 'so_luot_mua', sp.gia FROM " +
            "loai_san_pham lsp JOIN san_pham sp ON lsp.ma_loai_sp = sp.ma_loai_san_pham " +
            "LEFT JOIN chi_tiet_dat_hang ctdh ON ctdh.ma_san_pham = sp.ma_san_pham " +
            "WHERE sp.ma_khach_hang = #{maNguoiBan} " +
            "GROUP BY sp.ten_san_pham, lsp.ten_loai_sp " +
            "HAVING COUNT(ctdh.ma_san_pham) > 0 " +
            "ORDER BY COUNT(ctdh.ma_san_pham) DESC LIMIT 10";

    @Select(TOP_10_SP_DUOC_MUA_NHIEU_NHAT_USER_BY_MONTH)
    public List<Map<String, Object>> top10SPDuocMuaNhieuNhatUserByMonth(int maNguoiBan);

    /*****
     * 
     * Dành cho nhân viên
     * 
     */
    // 4 truy vấn để lấy tổng số đánh giá sản phẩm, tổng số sản phẩm, tổng số thành
    // viên, tổng số đơn đặt hàng
    final String GET_4_DATA_THONG_KE = "SELECT " +
            "(SELECT COUNT(ma_danh_gia) FROM danh_gia_san_pham) AS so_danh_gia, " +
            "(SELECT COUNT(ma_san_pham) FROM san_pham) AS so_san_pham, " +
            "(SELECT COUNT(ma_quyen) FROM `tai_khoan` WHERE ma_quyen = 'KH') AS so_thanh_vien, " +
            "(SELECT COUNT(ma_dat_hang) FROM chi_tiet_dat_hang) AS so_don_dat_hang";

    @Select(GET_4_DATA_THONG_KE)
    public Map<String, Object> get4DataThongKe();

    // Dành cho Vẽ đồ thị tròn biểu diễn trạng thái của từng chi tiết đơn đặt hàng
    // theo tháng cụ thể
    final String GET_DATA_TRANG_THAI_DAT_HANG_CUSTOM = "SELECT COUNT(*) AS so_luong, ctdh.status " +
            "FROM chi_tiet_dat_hang ctdh JOIN dat_hang dh ON ctdh.ma_dat_hang = dh.ma_dat_hang " +
            "WHERE DATE(dh.ngay_dat) BETWEEN #{tuNgay} AND #{denNgay} GROUP BY STATUS;";

    @Select(GET_DATA_TRANG_THAI_DAT_HANG_CUSTOM)
    public List<Map<String, Object>> getDataTrangThaiDatHangCustom(
            @Param("tuNgay") Date tuNgay,
            @Param("denNgay") Date denNgay);

    // Dành cho Vẽ đồ thị tròn biểu diễn trạng thái của từng chi tiết đơn đặt hàng
    final String GET_DATA_TRANG_THAI_DAT_HANG = "SELECT COUNT(*) AS so_luong, status " +
            "FROM chi_tiet_dat_hang GROUP BY STATUS;";

    @Select(GET_DATA_TRANG_THAI_DAT_HANG)
    public List<Map<String, Object>> getDataTrangThaiDatHang();

    // Top 10 sản phẩm được mua nhiều nhất
    final String TOP_10_SP_DUOC_MUA_NHIEU_NHAT = "SELECT lsp.ten_loai_sp, sp.ten_san_pham, " +
            "COUNT(ctdh.ma_san_pham) as 'so_luot_mua', sp.gia FROM " +
            "loai_san_pham lsp JOIN san_pham sp ON lsp.ma_loai_sp = sp.ma_loai_san_pham " +
            "LEFT JOIN chi_tiet_dat_hang ctdh ON ctdh.ma_san_pham = sp.ma_san_pham " +
            "GROUP BY sp.ten_san_pham,lsp.ten_loai_sp " +
            "HAVING COUNT(ctdh.ma_san_pham) > 0 " +
            "ORDER BY COUNT(ctdh.ma_san_pham) DESC LIMIT 10";

    @Select(TOP_10_SP_DUOC_MUA_NHIEU_NHAT)
    public List<Map<String, Object>> top10SPDuocMuaNhieuNhat();
}
