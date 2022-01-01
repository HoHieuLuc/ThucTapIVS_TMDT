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
            "tk.username, tk.avatar, asp.anh, AVG(dgsp.so_sao) AS xep_hang " +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "RIGHT JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "WHERE sp.ma_san_pham = #{maSanPham} " +
            "GROUP BY sp.ma_san_pham ";

    @Select(SAN_PHAM_DETAIL)
    @Results(value = {
            @Result(property = "maSanPham", column = "ma_san_pham"),
            @Result(property = "tenSanPham", column = "ten_san_pham"),
            @Result(property = "tenKhachHang", column = "ten"),
            @Result(property = "moTa", column = "mo_ta"),
            @Result(property = "gia", column = "gia"),
            @Result(property = "status", column = "status"),
            @Result(property = "tenLoaiSanPham", column = "ten_loai_sp"),
            @Result(property = "soLuong", column = "so_luong"),
            @Result(property = "ngayDang", column = "ngay_dang"),
            @Result(property = "soLuongDaBan", column = "so_luong_da_ban"),
            @Result(property = "anhSanPham", column = "anh"),
            @Result(property = "xepHang", column = "xep_hang")
    })
    public Map<String, Object> getDetailSanPham(String maSanPham);

    /* ===================================== */
    /* dành cho trang cá nhân của khách hàng */
    /* ===================================== */
    // Lấy danh sách sản phẩm theo mã khách hàng để hiển thị kho hàng
    final String GET_SAN_PHAM_BY_MA_KH = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.mo_ta, sp.gia, sp.status, kh.ma_khach_hang, kh.ten, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, sp.so_luong_da_ban, asp.anh, AVG(dgsp.so_sao) AS xep_hang "
            +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "WHERE sp.ma_khach_hang = #{maKhachHang} " +
            "GROUP BY sp.ma_san_pham";

    @Select(GET_SAN_PHAM_BY_MA_KH)
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
    public List<Map<String, Object>> getAllSanPhamByMaKH(int maKhachHang);

    // Lấy sản phẩm theo mã khách hàng và mã sản phẩm để hiển thị chi tiết sản phẩm
    final String GET_SAN_PHAM_BY_MA_KH_AND_MA_SP = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.mo_ta, sp.gia, sp.status, kh.ma_khach_hang, kh.ten, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, sp.so_luong_da_ban, asp.anh, AVG(dgsp.so_sao) AS xep_hang "
            +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "WHERE sp.ma_khach_hang = #{maKhachHang} " +
            "AND sp.ma_san_pham = #{maSanPham} " +
            "GROUP BY sp.ma_san_pham";

    @Select(GET_SAN_PHAM_BY_MA_KH_AND_MA_SP)
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
            "sp.so_luong, sp.ngay_dang, asp.anh, AVG(dgsp.so_sao) AS xep_hang " +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
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

}
