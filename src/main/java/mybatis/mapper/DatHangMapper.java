package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface DatHangMapper {
    // To obtain the value immediately after an INSERT, use a SELECT query with the
    // LAST_INSERT_ID() function.
    // Tham khảo từ (https://dev.mysql.com/)

    final String THEM_DON_DH_MOI = "INSERT INTO `dat_hang`(`ma_dat_hang`, `ma_khach_hang`, `ngay_dat`, `tong_tien`, `tinh_trang`) "
            +
            "VALUES (NULL,#{maKhachHang},now(), " +
                        "(select sum(gh.so_luong*sp.gia) from gio_hang gh join san_pham sp " +
                                 /* Ràng buộc chỉ thêm khi sản phẩm đó có đủ số lượng --------- (Ràng buộc vào column `tong_tien`)  */
                        "on sp.ma_san_pham = gh.ma_san_pham where gh.ma_khach_hang = #{maKhachHang} AND gh.so_luong < sp.so_luong),0); " +
            "Select last_insert_id();";

    @Select(THEM_DON_DH_MOI)
    public int themDonDHMoi(
            @Param("maKhachHang") int maKhachHang);

    // Thêm chi tiết đặt hàng
    final String THEM_CHI_TIET_DH = "INSERT INTO `chi_tiet_dat_hang` (`ma_dat_hang`, `ma_san_pham`, `so_luong`) VALUES (#{maDatHang},#{maSanPham},#{soLuong}); "
            +
            "DELETE FROM `gio_hang` WHERE ma_khach_hang = #{maKhachHang} and ma_san_pham = #{maSanPham}; " +
            "update san_pham set so_luong = so_luong - #{soLuong}, so_luong_da_ban = so_luong_da_ban + #{soluong} where ma_san_pham = #{maSanPham};";
    @Insert(THEM_CHI_TIET_DH)
    public void themChiTietDh(
            @Param("maDatHang") int maDatHang,
            @Param("maSanPham") String maSanPham,
            @Param("maKhachHang") int maKhachHang,
            @Param("soLuong") int soLuong);

    // Lấy thông tin của giỏ hàng để làm chi tiết đặt hàng
    final String GET_GIO_HANG_BY_MA_KH = "select so_luong,ma_san_pham from gio_hang where ma_khach_hang = #{maKhachHang};";
    @Select(GET_GIO_HANG_BY_MA_KH)
    public List<Map<String, Object>> getGioHangByMaKH(
            @Param("maKhachHang") int maKhachHang
        );


   // Chỉ lấy những sản phẩm của một người mua cụ thể nào đó trong giỏ hàng        
    final String GET_GIO_HANG_BY_SELLER = "select gh.so_luong,gh.ma_san_pham from gio_hang gh " +
        "JOIN san_pham sp  ON gh.ma_san_pham = sp.ma_san_pham " +
        "JOIN khach_hang kh  on sp.ma_khach_hang = kh.ma_khach_hang " +
        "JOIN tai_khoan tk on kh.id_tai_khoan = tk.id " +
    "where gh.ma_khach_hang = #{maKhachHang} AND tk.username = #{usernameNguoiBan}; ";
    @Select(GET_GIO_HANG_BY_SELLER)
    public List<Map<String, Object>> getGioHangBySeller(
        @Param("maKhachHang") int maKhachHang,
        @Param("usernameNguoiBan") String usernameNguoiBan
    );

    
    // Thêm đặt hàng nhưng theo người bán nào đó (Pay for this seller )
    final String THEM_DON_DH_THEO_SELLER = "INSERT INTO `dat_hang`(`ma_dat_hang`, `ma_khach_hang`, `ngay_dat`, `tong_tien`, `tinh_trang`) "
            +
            "VALUES (NULL,#{maKhachHang},now(), " +
            "(select sum(gh.so_luong*sp.gia) from gio_hang gh join san_pham sp " +
            " on sp.ma_san_pham = gh.ma_san_pham " +
            "JOIN khach_hang kh on kh.ma_khach_hang = sp.ma_khach_hang JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "where gh.ma_khach_hang = #{maKhachHang} and tk.username = #{usernameNguoiBan} AND gh.so_luong < sp.so_luong),0); " +
            "Select last_insert_id();";
    @Select(THEM_DON_DH_THEO_SELLER)
    public int themDonDHTheoSeller(
            @Param("maKhachHang") int maKhachHang,
            @Param("usernameNguoiBan") String userNameNguoiBan
        );

}
