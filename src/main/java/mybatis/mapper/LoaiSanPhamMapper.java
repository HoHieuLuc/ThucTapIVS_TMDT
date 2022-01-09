package mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.tmdt.model.LoaiSanPham;

import org.apache.ibatis.annotations.*;

public interface LoaiSanPhamMapper {

    // lấy tất cả loại sản phẩm cấp cao nhất
    final String GET_ALL_LOAI_SAN_PHAM_CAP_CAO = "SELECT * FROM loai_san_pham WHERE ma_loai_cha IS NULL";

    @Select(GET_ALL_LOAI_SAN_PHAM_CAP_CAO)
    public List<Map<String, Object>> getAllLoaiSanPhamCapCao();

    // lấy tất cả loại sản phẩm con của 1 loại sản phẩm cha
    final String GET_ALL_LOAI_SAN_PHAM_CON = "SELECT * FROM loai_san_pham WHERE ma_loai_cha = #{maLoaiSanPham}";

    @Select(GET_ALL_LOAI_SAN_PHAM_CON)
    public List<Map<String, Object>> getAllLoaiSanPhamCon(@Param("maLoaiSanPham") int maLoaiSanPham);

    // kiểm tra xem loại sản phẩm có phải là loại sản phẩm cấp thấp nhất không
    // nếu là sản phẩm cấp thấp nhất thì mới loại sản phẩm đó mới hợp lệ để thêm
    final String IS_LOAI_SAN_PHAM_CAP_THAP = "SELECT COUNT(*) " +
            "FROM `loai_san_pham` lsp WHERE lsp.ma_loai_sp = #{maLoaiSanPham} AND NOT EXISTS " +
            "(SELECT * FROM loai_san_pham lsp_con WHERE lsp_con.ma_loai_cha = lsp.ma_loai_sp)";

    @Select(IS_LOAI_SAN_PHAM_CAP_THAP)
    public int isLoaiSanPhamCapThap(@Param("maLoaiSanPham") int maLoaiSanPham);

    // lấy loại sản phẩm có cùng cha
    final String GET_LOAI_SAN_PHAM_CUNG_CAP = "SELECT * FROM loai_san_pham WHERE ma_loai_cha = " +
            "(SELECT ma_loai_cha FROM loai_san_pham WHERE ma_loai_sp = #{maLoaiSanPham})";

    @Select(GET_LOAI_SAN_PHAM_CUNG_CAP)
    public List<Map<String, Object>> getLoaiSanPhamCungCha(@Param("maLoaiSanPham") int maLoaiSanPham);

    // lấy thông tin loại sản phẩm cha
    final String GET_LOAI_SAN_PHAM_CHA = "SELECT * FROM loai_san_pham WHERE ma_loai_sp = " +
            "(SELECT ma_loai_cha FROM loai_san_pham WHERE ma_loai_sp = #{maLoaiSanPham})";

    @Select(GET_LOAI_SAN_PHAM_CHA)
    public Map<String, Object> getLoaiSanPhamCha(@Param("maLoaiSanPham") int maLoaiSanPham);

    // lấy thông tin 1 loại sản phẩm
    final String GET_LOAI_SAN_PHAM = "SELECT * FROM loai_san_pham WHERE ma_loai_sp = #{maLoaiSanPham}";

    @Select(GET_LOAI_SAN_PHAM)
    public Map<String, Object> getLoaiSanPham(@Param("maLoaiSanPham") int maLoaiSanPham);

    // Mục đích hàm này là chỉ hiện những tên loại sản phẩm mà nó có ít nhất 1 sản
    // phẩm
    final String GET_TEN_LOAI_SAN_PHAM = "SELECT lsp.ma_loai_sp, lsp.ten_loai_sp FROM loai_san_pham lsp RIGHT JOIN " +
            "san_pham sp on  lsp.ma_loai_sp = sp.ma_loai_san_pham " +
            "GROUP BY lsp.ma_loai_sp ";

    @Select(GET_TEN_LOAI_SAN_PHAM)
    @Results({
            @Result(property = "maLoaiSanPham", column = "ma_loai_sp"),
            @Result(property = "tenLoaiSanPham", column = "ten_loai_sp")
    })
    public List<LoaiSanPham> getAllTenLoaiSanPham();

    // thêm loại sản phẩm
    final String ADD_LOAI_SAN_PHAM = "INSERT INTO loai_san_pham " +
            "VALUES (null, #{tenLoaiSanPham}, #{anh}, #{maLoaiCha})";

    @Insert(ADD_LOAI_SAN_PHAM)
    public int addLoaiSanPham(LoaiSanPham loaiSanPham);

    // đếm số loại sản phẩm để phân trang cho trang quản lý
    final String COUNT_LOAI_SAN_PHAM = "SELECT COUNT(*) " +
            "FROM `loai_san_pham` lsp_cha RIGHT JOIN loai_san_pham lsp_con ON lsp_con.ma_loai_cha = lsp_cha.ma_loai_sp "
            +
            "WHERE lsp_con.ten_loai_sp LIKE CONCAT('%', #{search}, '%') " +
            "OR lsp_cha.ten_loai_sp LIKE CONCAT('%', #{search}, '%')";

    @Select(COUNT_LOAI_SAN_PHAM)
    public int countLoaiSanPham(String search);

    // hiển thị danh sách loại sản phẩm cho trang quản lý
    final String GET_ALL_LOAI_SAN_PHAM = "SELECT lsp_con.ma_loai_sp, lsp_con.ten_loai_sp, lsp_con.anh, " +
            "lsp_cha.ma_loai_sp AS ma_loai_sp_cha , lsp_cha.ten_loai_sp AS ten_loai_sp_cha " +
            "FROM `loai_san_pham` lsp_cha RIGHT JOIN loai_san_pham lsp_con ON lsp_con.ma_loai_cha = lsp_cha.ma_loai_sp "
            +
            "WHERE lsp_con.ten_loai_sp LIKE CONCAT('%', #{search}, '%') " +
            "OR lsp_cha.ten_loai_sp LIKE CONCAT('%', #{search}, '%') " +
            "LIMIT #{offset}, #{rowsPerPage}";

    @Select(GET_ALL_LOAI_SAN_PHAM)
    public List<Map<String, Object>> getLoaiSanPhamChoAdmin(
            @Param("search") String search,
            @Param("offset") int offset,
            @Param("rowsPerPage") int rowsPerPage);

    // trang chủ
    // lấy loại sản phẩm phổ biến
    final String GET_POPULAR_CATEGORY = "SELECT lsp.ma_loai_sp, lsp.ten_loai_sp, lsp.anh " +
            "FROM loai_san_pham lsp JOIN san_pham sp ON lsp.ma_loai_sp = sp.ma_loai_san_pham " +
            "LEFT JOIN chi_tiet_dat_hang ctdh ON ctdh.ma_san_pham = sp.ma_san_pham " +
            "GROUP BY lsp.ma_loai_sp " +
            "ORDER BY COUNT(ctdh.ma_san_pham) DESC " +
            "LIMIT 6";

    @Select(GET_POPULAR_CATEGORY)
    public List<Map<String, Object>> getPopularCategory();

}
