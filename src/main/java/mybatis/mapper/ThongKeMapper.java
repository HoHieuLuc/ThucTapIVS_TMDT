package mybatis.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ThongKeMapper {
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

        // 4 truy vấn để lấy tổng số đánh giá sản phẩm, tổng số sản phẩm, tổng số thành
        // viên, tổng số đơn đặt hàng
        final String GET_4_DATA_THONG_KE = "SELECT 	CASE WHEN (1=1) THEN " +
                        "(SELECT COUNT(ma_danh_gia) FROM danh_gia_san_pham) END AS so_danh_gia, " +
                        "	CASE WHEN (1=1) THEN " +
                        "(SELECT COUNT(ma_san_pham) FROM san_pham) END AS so_san_pham, " +
                        "    	CASE WHEN (1=1) THEN " +
                        "(SELECT COUNT(ma_quyen) FROM `tai_khoan`  WHERE ma_quyen = 'KH') END AS so_thanh_vien, " +
                        "   	CASE WHEN (1=1) THEN  " +
                        "(SELECT COUNT(ma_dat_hang) FROM dat_hang) END AS so_don_dat_hang; ";

        @Select(GET_4_DATA_THONG_KE)
        public Map<String, Object> get4DataThongKe();

        // Dành cho Vẽ đồ thị tròn biểu diễn trạng thái của từng chi tiết đơn đặt hàng
        // theo tháng cụ thể
        final String GET_DATA_TRANG_THAI_DAT_HANG_BY_MONTH = "SELECT 	CASE WHEN (1=1) THEN " +
                        "(SELECT COUNT(ma_san_pham) FROM chi_tiet_dat_hang ctdh JOIN dat_hang dh ON ctdh.ma_dat_hang = dh.ma_dat_hang WHERE ctdh.status = -1 AND MONTH(dh.ngay_dat) = #{thang}) END AS bi_huy, "
                        +
                        "	CASE WHEN (1=1) THEN " +
                        "(SELECT COUNT(ma_san_pham) FROM chi_tiet_dat_hang ctdh JOIN dat_hang dh ON ctdh.ma_dat_hang = dh.ma_dat_hang WHERE ctdh.status = 0 AND MONTH(dh.ngay_dat) = #{thang}) END AS dang_cho, "
                        +
                        "    	CASE WHEN (1=1) THEN  " +
                        "(SELECT COUNT(ma_san_pham) FROM chi_tiet_dat_hang ctdh JOIN dat_hang dh ON ctdh.ma_dat_hang = dh.ma_dat_hang WHERE ctdh.status = 1 AND MONTH(dh.ngay_dat) = #{thang}) END AS dang_van_chuyen, "
                        +
                        "   	CASE WHEN (1=1) THEN " +
                        "(SELECT COUNT(ma_san_pham) FROM chi_tiet_dat_hang ctdh JOIN dat_hang dh ON ctdh.ma_dat_hang = dh.ma_dat_hang WHERE ctdh.status = 2 AND MONTH(dh.ngay_dat) = #{thang}) END AS da_nhan_hang; ";

        @Select(GET_DATA_TRANG_THAI_DAT_HANG_BY_MONTH)
        public Map<String, Object> getDataTrangThaiDatHangByMonth(int Thang);

        // Dành cho Vẽ đồ thị tròn biểu diễn trạng thái của từng chi tiết đơn đặt hàng
        final String GET_DATA_TRANG_THAI_DAT_HANG = "SELECT 	CASE WHEN (1=1) THEN " +
                        "(SELECT COUNT(ma_san_pham) FROM chi_tiet_dat_hang WHERE status = -1) END AS bi_huy, " +
                        "	CASE WHEN (1=1) THEN " +
                        "(SELECT COUNT(ma_san_pham) FROM chi_tiet_dat_hang WHERE status = 0) END AS dang_cho, " +
                        "    	CASE WHEN (1=1) THEN  " +
                        "(SELECT COUNT(ma_san_pham) FROM chi_tiet_dat_hang WHERE status = 1) END AS dang_van_chuyen, " +
                        "   	CASE WHEN (1=1) THEN " +
                        "(SELECT COUNT(ma_san_pham) FROM chi_tiet_dat_hang WHERE status = 2) END AS da_nhan_hang; ";

        @Select(GET_DATA_TRANG_THAI_DAT_HANG)
        public Map<String, Object> getDataTrangThaiDatHang();

}
