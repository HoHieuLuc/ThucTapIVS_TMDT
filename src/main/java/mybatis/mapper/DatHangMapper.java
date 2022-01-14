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
            "AND gh.so_luong <= sp.so_luong)); " +
            "SELECT last_insert_id();";

    @Select(THEM_DON_DH_MOI)
    public int themDonDHMoi(
            @Param("maKhachHang") int maKhachHang);

    // Thêm chi tiết đặt hàng
    final String THEM_CHI_TIET_DH = "INSERT INTO chi_tiet_dat_hang " +
            "VALUES (#{maDatHang},#{maSanPham},#{soLuong}, 0, UPPER(LEFT(MD5(RAND()), 6))); " +
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
            "WHERE gh.so_luong <= sp.so_luong AND gh.ma_khach_hang = #{maKhachHang}";

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
            "AND gh.so_luong <= sp.so_luong)); " +
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

    // đếm các đơn đặt hàng được đặt để phân trang
    final String COUNT_DON_DAT_HANG_DUOC_DAT = "SELECT COUNT(DISTINCT dh.ma_dat_hang) " +
            "FROM dat_hang dh " +
            "JOIN chi_tiet_dat_hang ctdh ON ctdh.ma_dat_hang = dh.ma_dat_hang " +
            "JOIN khach_hang buyer ON buyer.ma_khach_hang = dh.ma_khach_hang " +
            "JOIN san_pham sp ON sp.ma_san_pham = ctdh.ma_san_pham " +
            "WHERE sp.ma_khach_hang = #{maKhachHang} " +
            "AND ctdh.status = #{status} " +
            "AND buyer.ten LIKE CONCAT('%', #{search}, '%')";

    @Select(COUNT_DON_DAT_HANG_DUOC_DAT)
    public int countDonDatHangDuocDat(
            @Param("maKhachHang") int maKhachHang,
            @Param("status") int status,
            @Param("search") String search);

    // lấy các đơn đặt hàng mà mình được đặt
    final String GET_DON_DAT_HANG_DUOC_DAT = "SELECT dh.ma_dat_hang, DATE(dh.ngay_dat) AS ngay_dat, " +
            "SUM(ctdh.so_luong * sp.gia) AS tong_tien, buyer.ten AS nguoi_mua, " +
            "ctdh.status " +
            "FROM dat_hang dh " +
            "JOIN khach_hang buyer ON buyer.ma_khach_hang = dh.ma_khach_hang " +
            "JOIN chi_tiet_dat_hang ctdh ON ctdh.ma_dat_hang = dh.ma_dat_hang " +
            "JOIN san_pham sp ON sp.ma_san_pham = ctdh.ma_san_pham " +
            "WHERE sp.ma_khach_hang = #{maKhachHang} " +
            "AND ctdh.status = #{status} " +
            "AND buyer.ten LIKE CONCAT('%', #{search}, '%') " +
            "GROUP BY dh.ma_dat_hang " +
            "ORDER BY dh.ngay_dat DESC " +
            "LIMIT #{offset}, #{rowsPerPage}";

    @Select(GET_DON_DAT_HANG_DUOC_DAT)
    public List<Map<String, Object>> getDonDatHangDuocDat(
            @Param("maKhachHang") int maKhachHang,
            @Param("status") int status,
            @Param("search") String search,
            @Param("offset") int offset,
            @Param("rowsPerPage") int rowsPerPage);

    // lấy chi tiết 1 phiếu đặt mà người bán được đặt
    final String GET_CHI_TIET_DON_DAT_HANG_DUOC_DAT = "SELECT ctdh.ma_dat_hang, ctdh.ma_san_pham, " +
            "ctdh.so_luong AS so_luong_dat, ctdh.status, ctdh.ma_nhan_hang, " +
            "sp.gia AS don_gia, " +
            "sp.ten_san_pham, (ctdh.so_luong * sp.gia) AS tong_tien, " +
            "sp.so_luong, asp.anh " +
            "FROM chi_tiet_dat_hang ctdh " +
            "JOIN san_pham sp ON sp.ma_san_pham = ctdh.ma_san_pham " +
            "JOIN khach_hang seller ON seller.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN anh_san_pham asp ON asp.ma_san_pham = sp.ma_san_pham " +
            "WHERE seller.ma_khach_hang = #{maNguoiBan} " +
            "AND ctdh.ma_dat_hang = #{maDatHang} " +
            "GROUP BY sp.ma_san_pham";

    @Select(GET_CHI_TIET_DON_DAT_HANG_DUOC_DAT)
    public List<Map<String, Object>> getChiTietDonDatHangDuocDat(
            @Param("maNguoiBan") int maNguoiBan,
            @Param("maDatHang") int maDatHang);

    // lấy thông tin người đặt hàng
    final String GET_THONG_TIN_NGUOI_DAT_HANG = "SELECT kh.ten, kh.dia_chi, tk.email, tk.so_dien_thoai, " +
            "DATE_FORMAT(dh.ngay_dat, '%d-%m-%Y %T') AS ngay_dat " +
            "FROM khach_hang kh " +
            "JOIN dat_hang dh ON dh.ma_khach_hang = kh.ma_khach_hang " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "WHERE dh.ma_dat_hang = #{maDatHang}";

    @Select(GET_THONG_TIN_NGUOI_DAT_HANG)
    public Map<String, Object> getThongTinNguoiDatHang(@Param("maDatHang") int maDatHang);

    // lấy tình trạng 1 chi tiết đơn đặt hàng của người bán hàng
    // để người bán không thể hủy vận chuyển đối với những đơn đã được giao xong
    final String GET_CURRENT_STATUS_CHI_TIET_DAT_HANG_SELLER = "SELECT ctdh.status " +
            "FROM chi_tiet_dat_hang ctdh " +
            "JOIN san_pham sp ON sp.ma_san_pham = ctdh.ma_san_pham " +
            "AND ctdh.ma_dat_hang = #{maDatHang} " +
            "AND ctdh.ma_san_pham = #{maSanPham} " +
            "AND sp.ma_khach_hang = #{maNguoiBan} ";

    @Select(GET_CURRENT_STATUS_CHI_TIET_DAT_HANG_SELLER)
    public Integer getCurrentStatusChiTietDatHangSeller(
            @Param("maDatHang") int maDatHang,
            @Param("maSanPham") String maSanPham,
            @Param("maNguoiBan") int maNguoiBan);

    // cập nhật tình trạng chi tiết đơn đặt hàng của người bán hàng
    final String CAP_NHAT_TINH_TRANG_CTDH_SELLER = "UPDATE chi_tiet_dat_hang ctdh " +
            "LEFT JOIN san_pham sp ON sp.ma_san_pham = ctdh.ma_san_pham " +
            "SET ctdh.status = #{status} WHERE ctdh.ma_dat_hang = #{maDatHang} " +
            "AND ctdh.ma_san_pham = #{maSanPham} " +
            "AND sp.ma_khach_hang = #{maNguoiBan}";

    @Update(CAP_NHAT_TINH_TRANG_CTDH_SELLER)
    public int capNhatTinhTrangChiTietDatHangNguoiBan(
            @Param("status") int status,
            @Param("maDatHang") int maDatHang,
            @Param("maSanPham") String maSanPham,
            @Param("maNguoiBan") int maNguoiBan);

    // đếm danh sách đơn đặt hàng cho người mua để phân trang
    final String COUNT_DON_DAT_HANG_CHO_NGUOI_MUA = "SELECT COUNT(*) " +
            "FROM chi_tiet_dat_hang ctdh JOIN san_pham sp ON sp.ma_san_pham = ctdh.ma_san_pham " +
            "JOIN dat_hang dh ON dh.ma_dat_hang = ctdh.ma_dat_hang " +
            "JOIN khach_hang seller ON seller.ma_khach_hang = sp.ma_khach_hang " +
            "WHERE dh.ma_khach_hang = #{maNguoiMua} " +
            "AND ctdh.status = #{status} " +
            "AND (sp.ten_san_pham LIKE CONCAT('%', #{search}, '%') " +
            "OR seller.ten LIKE CONCAT('%', #{search}, '%'))";

    @Select(COUNT_DON_DAT_HANG_CHO_NGUOI_MUA)
    public int countDonDatHangChoNguoiMua(
            @Param("maNguoiMua") int maNguoiMua,
            @Param("status") int status,
            @Param("search") String search);

    // lấy danh sách đơn đặt hàng cho người mua
    final String GET_DON_DAT_HANG_CHO_NGUOI_MUA = "SELECT sp.ten_san_pham, seller.ten, " +
            "ctdh.ma_dat_hang, ctdh.ma_san_pham, " +
            "DATE(dh.ngay_dat) AS ngay_dat, " +
            "ctdh.so_luong, (ctdh.so_luong * sp.gia) AS tong_tien, ctdh.status " +
            "FROM chi_tiet_dat_hang ctdh JOIN san_pham sp ON sp.ma_san_pham = ctdh.ma_san_pham " +
            "JOIN dat_hang dh ON dh.ma_dat_hang = ctdh.ma_dat_hang " +
            "JOIN khach_hang seller ON seller.ma_khach_hang = sp.ma_khach_hang " +
            "WHERE dh.ma_khach_hang = #{maNguoiMua} " +
            "AND ctdh.status = #{status} " +
            "AND (sp.ten_san_pham LIKE CONCAT('%', #{search}, '%') " +
            "OR seller.ten LIKE CONCAT('%', #{search}, '%')) " +
            "ORDER BY dh.ngay_dat DESC " +
            "LIMIT #{offset}, #{rowsPerPage}";

    @Select(GET_DON_DAT_HANG_CHO_NGUOI_MUA)
    public List<Map<String, Object>> getDonDatHangChoNguoiMua(
            @Param("maNguoiMua") int maNguoiMua,
            @Param("status") int status,
            @Param("search") String search,
            @Param("offset") int offset,
            @Param("rowsPerPage") int rowsPerPage);

    // lấy chi tiết 1 chi tiết đặt hàng
    final String GET_CHI_TIET_DON_DAT_HANG = "SELECT seller_acc.username, seller_acc.email, " +
            "seller_acc.so_dien_thoai, seller_acc.avatar, " +
            "seller.ten, ctdh.ma_dat_hang, ctdh.ma_san_pham, " +
            "ctdh.so_luong AS so_luong_dat, ctdh.status, " +
            "ctdh.so_luong * sp.gia AS tong_tien, " +
            "DATE_FORMAT(dh.ngay_dat, '%d-%m-%Y %T') AS ngay_dat, " +
            "sp.ten_san_pham, sp.mo_ta, sp.gia AS don_gia, asp.anh " +
            "FROM chi_tiet_dat_hang ctdh JOIN dat_hang dh " +
            "ON dh.ma_dat_hang = ctdh.ma_dat_hang " +
            "JOIN san_pham sp ON sp.ma_san_pham = ctdh.ma_san_pham " +
            "JOIN khach_hang seller ON seller.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN tai_khoan seller_acc ON seller_acc.id = seller.id_tai_khoan " +
            "LEFT JOIN anh_san_pham asp ON asp.ma_san_pham = sp.ma_san_pham " +
            "WHERE dh.ma_khach_hang = #{maNguoiMua} " +
            "AND dh.ma_dat_hang = #{maDatHang} " +
            "AND sp.ma_san_pham = #{maSanPham} " +
            "GROUP BY sp.ma_san_pham";

    @Select(GET_CHI_TIET_DON_DAT_HANG)
    public Map<String, Object> getChiTietDonDatHangChoNguoiMua(
            @Param("maNguoiMua") int maNguoiMua,
            @Param("maDatHang") int maDatHang,
            @Param("maSanPham") String maSanPham);

    // Cập nhật tình trạng đơn hàng dành cho người mua
    final String UPDATE_STATUS_DON_DAT_HANG_CHO_NGUOI_MUA = "UPDATE chi_tiet_dat_hang ctdh " +
            "LEFT JOIN dat_hang dh ON dh.ma_dat_hang = ctdh.ma_dat_hang " +
            "SET ctdh.status = #{status} " +
            "WHERE ctdh.ma_dat_hang = #{maDatHang} " +
            "AND ctdh.ma_san_pham = #{maSanPham} " +
            "AND dh.ma_khach_hang = #{maNguoiMua} ";

    @Update(UPDATE_STATUS_DON_DAT_HANG_CHO_NGUOI_MUA)
    public int capNhatTinhTrangChiTietDatHangNguoiMua(
            @Param("maNguoiMua") int maNguoiMua,
            @Param("maDatHang") int maDatHang,
            @Param("maSanPham") String maSanPham,
            @Param("status") int status);

    // lấy tình trạng hiện tại của 1 chi tiết đặt hàng
    final String GET_STATUS_AND_MA_NHAN_HANG_CTDH_BUYER = "SELECT ctdh.status, ctdh.ma_nhan_hang " +
            "FROM chi_tiet_dat_hang ctdh " +
            "JOIN dat_hang dh ON dh.ma_dat_hang = ctdh.ma_dat_hang " +
            "WHERE dh.ma_khach_hang = #{maNguoiMua} " +
            "AND dh.ma_dat_hang = #{maDatHang} " +
            "AND ctdh.ma_san_pham = #{maSanPham}";

    @Select(GET_STATUS_AND_MA_NHAN_HANG_CTDH_BUYER)
    public Map<String, Object> getStatusAndMaNhanHangCTDHNguoiMua(
            @Param("maNguoiMua") int maNguoiMua,
            @Param("maDatHang") int maDatHang,
            @Param("maSanPham") String maSanPham);
}
