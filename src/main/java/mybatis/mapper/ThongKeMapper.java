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
        @Param("denNgay") Date denNgay
    );
}
