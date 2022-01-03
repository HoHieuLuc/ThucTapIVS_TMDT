package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;


public interface DatHangMapper {
    //To obtain the value immediately after an INSERT, use a SELECT query with the LAST_INSERT_ID() function.
    //Tham khảo từ (https://dev.mysql.com/)
    final String THEM_DON_DH_MOI = "INSERT INTO `dat_hang`(`ma_dat_hang`, `ma_khach_hang`, `ngay_dat`, `tong_tien`, `tinh_trang`) "+ 
    "VALUES (NULL,#{maKhachHang},now(),(select sum(gh.so_luong*sp.gia) from gio_hang gh join san_pham sp " +
    "on sp.ma_san_pham = gh.ma_san_pham where gh.ma_khach_hang = #{maKhachHang}),0)";
    @Insert(THEM_DON_DH_MOI)
    public void themDonDHMoi(
        @Param("maKhachHang") int maKhachHang
    );

    //Lấy mã đặt hàng gần đây nhất của khách hàng để làm chi tiết đặt hàng
    final String GET_MA_DH_LATELY = "SELECT ma_dat_hang from dat_hang WHERE ma_khach_hang = #{maKhachHang} ORDER BY ma_dat_hang DESC limit 1;";
    @Select(GET_MA_DH_LATELY)
    public int getMaDHLatELY(int maKhachHang);

    //Thêm chi tiết đặt hàng
    final String THEM_CHI_TIET_DH ="INSERT INTO `chi_tiet_dat_hang`(`ma_dat_hang`, `ma_san_pham`, `so_luong`) VALUES (#{maDatHang},#{maSanPham},#{soLuong})";
    @Insert(THEM_CHI_TIET_DH)
    public void themChiTietDh(
        @Param("maDatHang") int maDatHang,
        @Param("maSanPham") String maSanPham,
        @Param("soLuong") int soLuong
    );

    //Lấy thông tin của giỏ hàng để làm chi tiết đặt hàng
    final String GET_GIO_HANG_BY_MA_KH = "select so_luong,ma_san_pham from gio_hang where ma_khach_hang = #{maKhachHang};";
    @Select(GET_GIO_HANG_BY_MA_KH)
    public List<Map<String, Object>> getGioHangByMaKH(
        @Param("maKhachHang") int maKhachHang
    );

}
