package mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.tmdt.model.SanPham;

import org.apache.ibatis.annotations.*;

public interface SanPhamMapper {
    // lấy tất cả sản phẩm và 1 ảnh đầu tiên để làm ảnh đại diện cho từng sản phẩm
    final String GET_ALL_SANPHAM = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.mo_ta, sp.gia, sp.status, kh.ma_khach_hang, kh.ten, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, sp.so_luong_da_ban, asp.anh, AVG(dgsp.so_sao) AS xep_hang "
            +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "GROUP BY sp.ma_san_pham";

    @Select(GET_ALL_SANPHAM)
    @Results(value = {
            @Result(property = "maSanPham", column = "ma_san_pham"),
            @Result(property = "tenSanPham", column = "ten_san_pham"),
            @Result(property = "moTa", column = "mo_ta"),
            @Result(property = "gia", column = "gia"),
            @Result(property = "status", column = "status"),
            @Result(property = "maKhachHang", column = "ma_khach_hang"),
            @Result(property = "tenKhachHang", column = "ten"),
            @Result(property = "tenLoaiSanPham", column = "ten_loai_sp"),
            @Result(property = "soLuong", column = "so_luong"),
            @Result(property = "ngayDang", column = "ngay_dang"),
            @Result(property = "soLuongDaBan", column = "so_luong_da_ban"),
            @Result(property = "anhSanPham", column = "anh"),
            @Result(property = "xepHang", column = "xep_hang")
    })
    public List<Map<String, Object>> getAllSanPham();

    // Xem chi tiết sản phẩm
    final String SAN_PHAM_DETAIL = "SELECT sp.ma_san_pham, sp.ten_san_pham, kh.ten, " +
            "sp.mo_ta, sp.gia, sp.status, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, sp.so_luong_da_ban, " +
            "tk.username, tk.avatar, AVG(dgsp.so_sao) AS xep_hang, lsp.ma_loai_sp " +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "RIGHT JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "WHERE sp.ma_san_pham = #{maSanPham} " +
            "GROUP BY sp.ma_san_pham ";

    @Select(SAN_PHAM_DETAIL)
    public Map<String, Object> getDetailSanPham(String maSanPham);

    // đếm sản phẩm theo loại sản phẩm để phân trang
    final String COUNT_SAN_PHAM_BY_LSP = "SELECT COUNT(*) FROM san_pham " +
            "WHERE ma_loai_san_pham = #{maLoaiSP} AND " +
            "ten_san_pham LIKE CONCAT ('%', #{search}, '%')";

    @Select(COUNT_SAN_PHAM_BY_LSP)
    public int countSanPhamByLSP(@Param("maLoaiSP") int maLoaiSP, @Param("search") String search);

    // tìm kiếm sản phẩm theo loại sản phẩm
    final String GET_SAN_PHAM_BY_LSP = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.gia, " +
            "kh.ten, asp.anh, AVG(dgsp.so_sao) AS xep_hang " +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "LEFT JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "WHERE sp.ma_loai_san_pham = #{maLoaiSP} AND " +
            "sp.ten_san_pham LIKE CONCAT ('%', #{search}, '%') " +
            "GROUP BY sp.ma_san_pham " +
            "ORDER BY ${orderBy} ${order} " +
            "LIMIT #{offset}, #{rowsPerPage}";

    @Select(GET_SAN_PHAM_BY_LSP)
    public List<Map<String, Object>> getAllSanPhamByLSP(
            @Param("maLoaiSP") int maLoaiSP,
            @Param("search") String search,
            @Param("offset") int offset,
            @Param("rowsPerPage") int rowsPerPage,
            @Param("orderBy") String orderBy,
            @Param("order") String order);

    /* ===================================== */
    /* dành cho trang cá nhân của khách hàng */
    /* ===================================== */

    // đếm số lượng sản phẩm của khách hàng để phân trang
    final String COUNT_SAN_PHAM_BY_MA_KH = "SELECT COUNT(*) " +
            "FROM SAN_PHAM sp JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "WHERE sp.ma_khach_hang = #{maKhachHang} " +
            "AND sp.ten_san_pham LIKE CONCAT('%', #{search}, '%') " +
            "AND sp.status = #{status}";

    @Select(COUNT_SAN_PHAM_BY_MA_KH)
    public int countSanPhamByMaKh(@Param("maKhachHang") int maKhachHang,
            @Param("search") String search, @Param("status") int status);

    // Lấy danh sách sản phẩm theo mã khách hàng để hiển thị kho hàng
    final String GET_SAN_PHAM_BY_MA_KH = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.gia, " +
            "sp.status, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, " +
            "sp.so_luong_da_ban, AVG(dgsp.so_sao) AS xep_hang " +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "WHERE sp.ma_khach_hang = #{maKhachHang} " +
            "AND sp.ten_san_pham LIKE CONCAT('%', #{search}, '%') " +
            "AND sp.status = #{status} " +
            "GROUP BY sp.ma_san_pham " +
            "LIMIT #{offset}, #{rowsPerPage}";

    @Select(GET_SAN_PHAM_BY_MA_KH)
    public List<Map<String, Object>> getSanPhamByMaKH(
            @Param("maKhachHang") int maKhachHang,
            @Param("search") String search,
            @Param("status") int status,
            @Param("offset") int offset,
            @Param("rowsPerPage") int rowsPerPage);

    // Lấy sản phẩm theo mã khách hàng và mã sản phẩm để hiển thị chi tiết sản phẩm
    final String GET_SAN_PHAM_BY_MA_KH_AND_MA_SP = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.mo_ta, " +
            "sp.gia, sp.status, lsp.ma_loai_sp, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, " +
            "sp.so_luong_da_ban, AVG(dgsp.so_sao) AS xep_hang " +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "WHERE sp.ma_khach_hang = #{maKhachHang} " +
            "AND sp.ma_san_pham = #{maSanPham} " +
            "GROUP BY sp.ma_san_pham";

    @Select(GET_SAN_PHAM_BY_MA_KH_AND_MA_SP)
    public Map<String, Object> getSanPhamByMaKHAndMaSP(
            @Param("maKhachHang") int maKhachHang,
            @Param("maSanPham") String maSanPham);

    // Thêm sản phẩm vô kho
    final String ADD_SAN_PHAM = "INSERT INTO `san_pham`(`ma_san_pham`, `ma_khach_hang`, `ten_san_pham`, `mo_ta`, `gia`, `status`, `ma_loai_san_pham`, `so_luong`, `ngay_dang`, `so_luong_da_ban`) "
            +
            "VALUES (UUID(), #{maKhachHang}, #{tenSanPham}, #{moTa}, #{gia}, #{status}, #{maLoaiSanPham}, #{soLuong}, now(), #{soLuongDaBan})";

    @Insert(ADD_SAN_PHAM)
    @Options(useGeneratedKeys = true, keyProperty = "maSanPham")
    public void insertSanPham(SanPham sanPham);

    // lấy id từ sản phẩm vừa tạo để insert ảnh sản phẩm
    final String GET_ID_SAN_PHAM_BY_MA_KH_AND_TEN_SP = "SELECT ma_san_pham FROM san_pham " +
            "WHERE ma_khach_hang = #{maKhachHang} AND ten_san_pham = #{tenSanPham}" +
            "LIMIT 1";

    @Select(GET_ID_SAN_PHAM_BY_MA_KH_AND_TEN_SP)
    public String getIdSanPhamByMaKHAndTenSP(@Param("maKhachHang") int maKhachHang,
            @Param("tenSanPham") String tenSanPham);

    // đếm sản phẩm từ mã khách hàng và tên sản phẩm để kiểm tra trùng
    final String COUNT_SAN_PHAM_BY_MA_KH_AND_TEN_SP = "SELECT COUNT(*) FROM san_pham " +
            "WHERE ma_khach_hang = #{maKhachHang} AND ten_san_pham = #{tenSanPham}";

    @Select(COUNT_SAN_PHAM_BY_MA_KH_AND_TEN_SP)
    public int countSanPhamByMaKHAndTenSP(@Param("maKhachHang") int maKhachHang,
            @Param("tenSanPham") String tenSanPham);

    // sửa sản phẩm
    final String UPDATE_SP_INFO = "UPDATE `san_pham` SET `ten_san_pham`=#{tenSanPham},`mo_ta`=#{moTa},`gia`=#{gia}, " +
            "`status`=#{status},`ma_loai_san_pham`=#{maLoaiSanPham},`so_luong`=#{soLuong},`ngay_dang`=#{ngayDang} " +
            " WHERE `ma_san_pham` = #{maSanPham}  AND `ma_khach_hang`= #{maKhachHang} ;";

    @Update(UPDATE_SP_INFO)
    public int updateSanPham(SanPham sanpham);

    // lấy tình trạng của 1 sản phẩm
    final String GET_STATUS_FROM_SAN_PHAM = "SELECT status FROM san_pham WHERE ma_san_pham = #{maSanPham} " +
            "AND ma_khach_hang = #{maKhachHang}";

    @Select(GET_STATUS_FROM_SAN_PHAM)
    public Integer getStatusFromSanPham(@Param("maSanPham") String maSanPham,
            @Param("maKhachHang") int maKhachHang);

    /* ==================== */
    /* dành cho trang store */
    /* ==================== */
    // đếm số lượng sản phẩm của 1 người dùng
    // nhưng không có limit offset để đếm tất cả dòng trả về
    // từ đó tính được số trang
    final String COUNT_SAN_PHAM_BY_USERNAME = "SELECT COUNT(*) " +
            "FROM SAN_PHAM sp " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "WHERE tk.username = #{username} " +
            "AND sp.ten_san_pham LIKE CONCAT('%', #{search}, '%')";

    @Select(COUNT_SAN_PHAM_BY_USERNAME)
    public int countSanPhamByUsername(@Param("username") String username, @Param("search") String search);

    // lấy danh sách sản phẩm theo username cho trang store
    final String GET_SAN_PHAM_BY_USERNAME = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.gia, lsp.ten_loai_sp, " +
            "lsp.ma_loai_sp, sp.so_luong, sp.ngay_dang, asp.anh, AVG(dgsp.so_sao) AS xep_hang " +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "LEFT JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "WHERE tk.username = #{username} " +
            "AND sp.ten_san_pham LIKE CONCAT('%', #{search}, '%') " +
            "GROUP BY sp.ma_san_pham " +
            "ORDER BY ${orderBy} ${order} " +
            "LIMIT #{offset}, #{rowsPerPage}";

    @Select(GET_SAN_PHAM_BY_USERNAME)
    public List<Map<String, Object>> getSanPhamByUsername(
            @Param("username") String username,
            @Param("search") String search,
            @Param("orderBy") String orderBy,
            @Param("order") String order,
            @Param("offset") int offset,
            @Param("rowsPerPage") int rowsPerPage);

    /* ==================== */
    /* dành cho trang admin */
    /* ==================== */

    // đếm số sản phẩm theo 1 status và search để phân trang
    final String COUNT_SP_BY_STATUS = "SELECT COUNT(*) FROM san_pham sp " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "WHERE sp.status = #{status} AND " +
            "(sp.ten_san_pham LIKE CONCAT('%', #{search}, '%') OR kh.ten LIKE CONCAT('%', #{search}, '%'))";

    @Select(COUNT_SP_BY_STATUS)
    public int countSanPhamByStatus(@Param("status") int status, @Param("search") String search);

    // Lấy danh sách các sản phẩm theo status và search nào đó
    final String GET_SP_BY_STATUS = "SELECT kh.ten,sp.ma_san_pham,sp.ten_san_pham,sp.mo_ta,sp.gia,lsp.ten_loai_sp, " +
            "sp.so_luong,sp.ngay_dang " +
            "FROM san_pham SP " +
            "RIGHT JOIN khach_hang KH ON SP.ma_khach_hang = KH.ma_khach_hang " +
            "RIGHT JOIN loai_san_pham LSP ON SP.ma_loai_san_pham = LSP.ma_loai_sp " +
            "WHERE SP.status = #{status} AND " +
            "(sp.ten_san_pham LIKE CONCAT('%', #{search}, '%') " +
            "OR kh.ten LIKE CONCAT('%', #{search}, '%')) " +
            "LIMIT #{offset}, #{rowsPerPage}";

    @Select(GET_SP_BY_STATUS)
    public List<Map<String, Object>> getSP_ByStatus(
            @Param("status") int status,
            @Param("offset") int offset,
            @Param("rowsPerPage") int rowsPerPage,
            @Param("search") String search);

    // Thay đổi trạng thái Sản phẩm (Duyệt = 1, Ẩn = 0)
    final String UPDATE_SP_STATUS = "UPDATE `san_pham` SET `status` = #{status} WHERE `san_pham`.`ma_san_pham` = #{maSanPham};";

    @Update(UPDATE_SP_STATUS)
    public int updateSP_Status(
            @Param("status") int status,
            @Param("maSanPham") String maSanPham);

    // trang chủ
    // lấy sản phẩm mới
    final String GET_NEWEST_PRODUCTS = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.gia, asp.anh " +
            "FROM san_pham sp LEFT JOIN anh_san_pham asp ON asp.ma_san_pham = sp.ma_san_pham " +
            "GROUP BY sp.ma_san_pham " +
            "ORDER BY ngay_dang DESC " +
            "LIMIT 12";

    @Select(GET_NEWEST_PRODUCTS)
    public List<Map<String, Object>> getNewestProducts();
}
