package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GioHangMapper {
    // Lấy danh sách mã người bán từ giỏ hàng của khách hàng 1
    final String SELLER_LIST = "SELECT tk.username,kh2.ten from gio_hang gh JOIN khach_hang kh on gh.ma_khach_hang = kh.ma_khach_hang join "
            +
            " san_pham sp on sp.ma_san_pham = gh.ma_san_pham " +
            " join khach_hang kh2  on kh2.ma_khach_hang = sp.ma_khach_hang " +
            " JOIN tai_khoan tk on tk.id = kh2.id_tai_khoan  where gh.ma_khach_hang = #{maKhachHang} GROUP BY sp.ma_khach_hang;";

    @Select(SELLER_LIST)
    public List<Map<String, Object>> getSellerList(int maKhachHang);

    // lấy info 1 người bán dùng khi khách hàng chỉ đặt hàng từ người này
    // xài List để bên kia dễ xử lý hơn
    final String GET_SELLER_INFO = "SELECT tk.username, kh.ten " +
            "FROM tai_khoan tk JOIN khach_hang kh on tk.id = kh.id_tai_khoan " +
            "WHERE tk.username = #{username}";

    @Select(GET_SELLER_INFO)
    public List<Map<String, Object>> getSellerInfo(String username);

    // lấy thông tin 1 người bán

    // ví dụ mã người bán = {15,16,6} trong bảng sản phẩm , mã khách hàng người mua
    // trong bảng giỏ hàng = {1}
    // Từ danh sách người bán, chạy vòng lặp in ra các sản phẩm vừa có trong giỏ
    // hàng, vừa có mã người bán như trên
    final String GET_GH_INFO_BY_SELLER_ID = "SELECT sp.ten_san_pham,sp.gia as don_gia,sp.gia*gh.so_luong as tong_tien,sp.ma_san_pham,asp.anh,gh.so_luong "
            +
            " FROM gio_hang gh JOIN san_pham sp on gh.ma_san_pham =sp.ma_san_pham " +
            " JOIN khach_hang kh on sp.ma_khach_hang = kh.ma_khach_hang " +
            " JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
            " JOIN khach_hang kh2 on sp.ma_khach_hang = kh2.ma_khach_hang " +
            " JOIN tai_khoan tk on kh2.id_tai_khoan = tk.id " +
            " WHERE gh.ma_khach_hang = #{maNguoiMua} and tk.username = #{userNameNguoiBan} GROUP by gh.ma_san_pham ";

    @Select(GET_GH_INFO_BY_SELLER_ID)
    public List<Map<String, Object>> getGioHangBySeller(@Param("maNguoiMua") int maNguoiMua,
            @Param("userNameNguoiBan") String userNaneNguoiBan);

    // Hàm lấy giỏ hàng của khách đang đăng nhập
    final String GET_GIO_HANG_BY_MA_KH = "SELECT gh.ma_san_pham,gh.so_luong " +
            " FROM gio_hang GH LEFT JOIN khach_hang kh " +
            " ON kh.ma_khach_hang = gh.ma_khach_hang WHERE kh.ma_khach_hang = #{maKhachHang} ";

    @Select(GET_GIO_HANG_BY_MA_KH)
    public List<Map<String, Object>> getGioHangByMaKH(int maKhachHang);

    // Hàm thêm sản phẩm vào giỏ hàng, nếu bị trùng, dùng hàm update số lượng lên 1
    final String THEM_SP_VAO_GIO_HANG = "INSERT INTO `gio_hang` (`ma_khach_hang`, `ma_san_pham`, `so_luong`) " +
            "VALUES (#{maKhachHang},#{maSanPham},'1')";

    @Insert(THEM_SP_VAO_GIO_HANG)
    public void themSanPhamVaoGioHang(@Param("maKhachHang") int maKhachHang, @Param("maSanPham") String maSanPham);

    // cập nhật số lượng sản phẩm trong giỏ hàng, khi người dùng bấm nút thêm giỏ
    // hàng nhiều lần
    final String INCREASE_SL_SP = "UPDATE `gio_hang` SET `ma_khach_hang` = #{maKhachHang}, `so_luong` = `so_luong` + 1 "
            +
            " WHERE `gio_hang`.`ma_khach_hang` = #{maKhachHang} AND `gio_hang`.`ma_san_pham` = #{maSanPham}";

    @Update(INCREASE_SL_SP)
    public int increaseSoLuongSP(@Param("maKhachHang") int maKhachHang, @Param("maSanPham") String maSanPham);

    // cập nhật số lượng sản phẩm trong -- trang giỏ hàng ---
    final String UPDATE_SO_LUONG_SP = "UPDATE `gio_hang` SET `ma_khach_hang` = #{maKhachHang}, `ma_san_pham` = #{maSanPham}, "
            +
            "`so_luong` = #{soLuong} " +
            "WHERE `gio_hang`.`ma_khach_hang` = #{maKhachHang} AND `gio_hang`.`ma_san_pham` = #{maSanPham};";

    @Update(UPDATE_SO_LUONG_SP)
    public int updateSoLuongSanPhamTrongGioHang(
            @Param("maKhachHang") int maKhachHang,
            @Param("maSanPham") String maSanPham,
            @Param("soLuong") int soLuong);

    // Xóa sản phẩm ra khỏi giỏ hàng
    final String DELETE_SP_GIO_HANG = "DELETE FROM `gio_hang` WHERE `gio_hang`.`ma_khach_hang` = #{maKhachHang} " +
            "AND `gio_hang`.`ma_san_pham` = #{maSanPham}";

    @Delete(DELETE_SP_GIO_HANG)
    public int deleteSanPhamTrongGioHang(@Param("maKhachHang") int maKhachHang, @Param("maSanPham") String maSanPham);

    /*
     * Trước khi cập nhật trong trang giỏ hàng, hoặc tăng số lượng khi click chuột
     * đặt hàng nhiều lần
     * Kiểm tra xem số lượng của giỏ hàng có lớn 0 và vượt quá `so_luong` trong
     * table `san_pham` hay không?
     */
    final String CHECK_SP_HET_HANG = "select count(*) " +
            "from gio_hang gh join san_pham sp on sp.ma_san_pham = gh.ma_san_pham " +
            "where gh.ma_khach_hang = #{maKhachHang} and gh.ma_san_pham = #{maSanPham} AND #{soLuong} > sp.so_luong;";

    @Select(CHECK_SP_HET_HANG)
    public int checkSPHetHang(
            @Param("maKhachHang") int maKhachHang,
            @Param("maSanPham") String maSanPham,
            @Param("soLuong") int soLuong);

    // Số lượng tối đa có thể đặt cho sản phẩm nào đó
    final String GET_SO_LUONG_SP_HIEN_CO = "SELECT so_luong from `san_pham` WHERE ma_san_pham = #{maSanPham}";

    @Select(GET_SO_LUONG_SP_HIEN_CO)
    public int getSoLuongSPHienCo(String maSanPham);

    // Lấy số lượng sản phẩm trong giỏ hàng theo mã sản phẩm
    // để kiểm tra xem số lượng sản phẩm trong giỏ hàng có lớn hơn số lượng tối đa
    // sử dụng khi insert bị trùng
    final String GET_SO_LUONG_SP_TRONG_GIO_HANG = "SELECT so_luong FROM `gio_hang` WHERE ma_khach_hang = #{maKhachHang} "
            +
            "AND ma_san_pham = #{maSanPham}";

    @Select(GET_SO_LUONG_SP_TRONG_GIO_HANG)
    public int getSoLuongSPTrongGioHang(@Param("maKhachHang") int maKhachHang, @Param("maSanPham") String maSanPham);

    // lấy mã khách hàng của 1 sản phẩm để kiểm tra không cho tự thêm vô giỏ sản
    // phẩm của mình
    final String GET_MA_KHACH_HANG_BY_MA_SP = "SELECT ma_khach_hang FROM `san_pham` WHERE ma_san_pham = #{maSanPham}";

    @Select(GET_MA_KHACH_HANG_BY_MA_SP)
    public int getMaKhachHangByMaSP(String maSanPham);

    // tính tổng tiền và tổng số lượng 1 giỏ hàng
    final String GET_TONG_GIO_HANG = "SELECT SUM(gh.so_luong) AS so_luong, " +
            "SUM(gh.so_luong * sp.gia) AS tong_tien FROM gio_hang gh " +
            "JOIN san_pham sp ON sp.ma_san_pham = gh.ma_san_pham " +
            "WHERE gh.ma_khach_hang = #{maKhachHang}";

    @Select(GET_TONG_GIO_HANG)
    public Map<String, Object> getTongTienVaSoLuongGioHang(@Param("maKhachHang") int maKhachHang);
}
