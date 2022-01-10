package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface DatHangMapper {
    // To obtain the value immediately after an INSERT, use a SELECT query with the
    // LAST_INSERT_ID() function.
    // Tham khảo từ (https://dev.mysql.com/)

    final String THEM_DON_DH_MOI = "INSERT INTO dat_hang " +
            "VALUES (NULL, #{maKhachHang}, now(), " +
            "(SELECT SUM(gh.so_luong * sp.gia) FROM gio_hang gh JOIN san_pham sp " +
            /*
             * Ràng buộc chỉ thêm khi sản phẩm đó có đủ số lượng --------- (Ràng buộc vào
             * column `tong_tien`)
             */
            "ON sp.ma_san_pham = gh.ma_san_pham WHERE gh.ma_khach_hang = #{maKhachHang} " +
            "AND gh.so_luong <= sp.so_luong), 0); " +
            "SELECT last_insert_id();";

    @Select(THEM_DON_DH_MOI)
    public int themDonDHMoi(
            @Param("maKhachHang") int maKhachHang);

    // Thêm chi tiết đặt hàng
    final String THEM_CHI_TIET_DH = "INSERT INTO chi_tiet_dat_hang "+
    "VALUES (#{maDatHang},#{maSanPham},#{soLuong}); " +
    "DELETE FROM `gio_hang` WHERE ma_khach_hang = #{maKhachHang} and ma_san_pham = #{maSanPham}";

    @Insert(THEM_CHI_TIET_DH)
    public void themChiTietDatHang(
            @Param("maKhachHang") int maKhachHang,
            @Param("maDatHang") int maDatHang,
            @Param("maSanPham") String maSanPham,
            @Param("soLuong") int soLuong);

    // Lấy thông tin của giỏ hàng để làm chi tiết đặt hàng
    final String GET_GIO_HANG_BY_MA_KH = "SELECT gh.ma_san_pham, gh.so_luong " +
            "FROM gio_hang gh JOIN san_pham sp ON sp.ma_san_pham = gh.ma_san_pham " +
            "WHERE gh.so_luong <= sp.so_luong";

    @Select(GET_GIO_HANG_BY_MA_KH)
    public List<Map<String, Object>> getGioHangByMaKH(@Param("maKhachHang") int maKhachHang);

    // Chỉ lấy những sản phẩm của một người mua cụ thể nào đó trong giỏ hàng
    final String GET_GIO_HANG_BY_SELLER = "SELECT gh.so_luong, gh.ma_san_pham FROM gio_hang gh " +
            "JOIN san_pham sp  ON gh.ma_san_pham = sp.ma_san_pham " +
            "JOIN khach_hang kh ON sp.ma_khach_hang = kh.ma_khach_hang " +
            "JOIN tai_khoan tk ON kh.id_tai_khoan = tk.id " +
            "WHERE gh.ma_khach_hang = #{maKhachHang} " +
            "AND gh.so_luong <= sp.so_luong " +
            "AND tk.username = #{usernameNguoiBan}; ";

    @Select(GET_GIO_HANG_BY_SELLER)
    public List<Map<String, Object>> getGioHangBySeller(
            @Param("maKhachHang") int maKhachHang,
            @Param("usernameNguoiBan") String usernameNguoiBan);

    // Thêm đặt hàng nhưng theo người bán nào đó (Pay for this seller )
    final String THEM_DON_DH_THEO_SELLER = "INSERT INTO `dat_hang` " +
            "VALUES (NULL, #{maKhachHang}, NOW(), " +
            "(SELECT SUM(gh.so_luong * sp.gia) FROM gio_hang gh JOIN san_pham sp " +
            "ON sp.ma_san_pham = gh.ma_san_pham " +
            "JOIN khach_hang kh on kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "WHERE gh.ma_khach_hang = #{maKhachHang} AND tk.username = #{usernameNguoiBan} " +
            "AND gh.so_luong <= sp.so_luong), 0); " +
            "SELECT last_insert_id();";

    @Select(THEM_DON_DH_THEO_SELLER)
    public int themDonDHTheoSeller(
            @Param("maKhachHang") int maKhachHang,
            @Param("usernameNguoiBan") String usernameNguoiBan);

    /*
     * Có lỗi chỗ tổng tiền, thì catch là chạy câu lệnh in ra sản phẩm mình muốn đặt
     * mua
     * mà không đủ số lượng
     * 
     * Cần thông báo số lượng còn cho những sản phẩm mà khách hàng đặt lố trong giỏ
     * hàng
     * Cần ô tham mưu chỗ này, tui phân vân có nên làm lệnh này để chặn ở chỗ thêm
     * giỏ hàng không
     */
    final String CHECK_SP_HET_HANG = "select sp.so_luong as 'so_luong_hien_co',sp.ten_san_pham,gh.so_luong as 'so_luong_can_mua' "
            +
            "from gio_hang gh join san_pham sp on sp.ma_san_pham = gh.ma_san_pham " +
            "where gh.ma_khach_hang = #{maKhachHang} AND gh.so_luong > sp.so_luong; ";

    @Select(CHECK_SP_HET_HANG)
    public List<Map<String, Object>> checkSPHetHang(int maKhachHang);

}
