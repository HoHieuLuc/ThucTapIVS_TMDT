package mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

import com.tmdt.model.*;

public interface KhachHangMapper {
    final String INSERT_KHACH_HANG = "INSERT INTO `khach_hang` (`ten`, `dia_chi`, `id_tai_khoan`, `tien_no`, `gioi_thieu`) VALUES (#{ten},#{diaChi},#{idTaiKhoan},#{tienNo},#{gioiThieu})";

    @Insert(INSERT_KHACH_HANG)
    @Options(useGeneratedKeys = true, keyProperty = "maKhachHang", keyColumn = "ma_khach_hang")
    public void themKhachHang(KhachHang khachHang);

    // Lấy mã khách hàng dựa theo id của tài khoản (id sẽ được lưu trong session)
    final String GET_MA_KHACH_HANG = "SELECT ma_khach_hang from `khach_hang` WHERE id_tai_khoan = #{id}";

    @Select(GET_MA_KHACH_HANG)
    public int getMaKhachHangFromKhachHangTable(int id);

    /* ============================ */
    /* store */
    /* ============================ */
    // lấy thông tin khách hàng cho trang store dựa vào username
    final String GET_STORE_INFO = "SELECT kh.ma_khach_hang,kh.ten, kh.dia_chi, kh.gioi_thieu, " +
            "tk.email, tk.so_dien_thoai, tk.avatar, tk.ngay_tao, " +
            "ttlh.twitter_link, ttlh.facebook_link, ttlh.personal_link, " +
            "AVG(dgsp.so_sao) AS rating, COUNT(dgsp.ma_danh_gia) AS so_danh_gia " +
            "FROM khach_hang kh " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "LEFT JOIN thong_tin_lien_he ttlh ON ttlh.ma_khach_hang = kh.ma_khach_hang " +
            "LEFT JOIN san_pham sp ON sp.ma_khach_hang = kh.ma_khach_hang " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "WHERE tk.username = #{username}";

    @Select(GET_STORE_INFO)
    public Map<String, Object> getStoreInfoByUsername(String username);

    // lấy số sao đánh giá sản phẩm ứng với từng số sao
    final String GET_PRODUCT_RATING = "SELECT dgsp.so_sao, COUNT(*) as so_luong " +
            "FROM danh_gia_san_pham dgsp JOIN san_pham sp ON sp.ma_san_pham = dgsp.ma_san_pham " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "WHERE tk.username = #{username} " +
            "GROUP BY dgsp.so_sao " +
            "ORDER BY dgsp.so_sao DESC";

    @Select(GET_PRODUCT_RATING)
    public List<Map<Integer, Integer>> getProductRating(String username);

    // Lấy avatar, mã khách hàng, tên khách hàng để làm trang danh sách store
    final String GET_LIST_STORE = "SELECT TK.avatar,TK.username,KH.ten from khach_hang kh " +
            "LEFT JOIN tai_khoan tk on kh.id_tai_khoan = tk.id " +
            "WHERE tk.id NOT  IN (SELECT id_tai_khoan FROM nhan_vien) " +
            "GROUP BY username;";

    @Select(GET_LIST_STORE)
    public List<Map<String, Object>> getListStore();

    // lấy top 6 cửa hàng đánh giá cao nhất
    final String GET_TOP_STORE = "SELECT tk.username, kh.ten, tk.avatar, AVG(dgsp.so_sao) AS xep_hang " +
            "FROM khach_hang kh JOIN san_pham sp ON sp.ma_khach_hang = kh.ma_khach_hang " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " +
            "LEFT JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "GROUP BY kh.ma_khach_hang HAVING AVG(dgsp.so_sao) > 0 " +
            "ORDER BY xep_hang DESC LIMIT 6";

    @Select(GET_TOP_STORE)
    public List<Map<String, Object>> getTopStore();
}