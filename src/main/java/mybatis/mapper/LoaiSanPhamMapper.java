package mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.tmdt.model.LoaiSanPham;

import org.apache.ibatis.annotations.*;

public interface LoaiSanPhamMapper {

    // lấy tất cả loại sản phẩm cấp cao nhất
    final String GET_ALL_LOAI_SAN_PHAM = "SELECT * FROM loai_san_pham WHERE ma_loai_cha IS NULL";

    @Select(GET_ALL_LOAI_SAN_PHAM)
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

    final String GET_SAN_PHAM_BY_LSP = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.mo_ta, sp.gia, sp.status, kh.ma_khach_hang, kh.ten, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, sp.so_luong_da_ban, asp.anh, AVG(dgsp.so_sao) AS xep_hang "
            +
            "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
            "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
            "JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
            "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham "
            + " WHERE sp.ma_loai_san_pham = #{maLoaiSP} " +
            "GROUP BY sp.ma_san_pham";

    @Select(GET_SAN_PHAM_BY_LSP)
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
    public List<Map<String, Object>> getAllSanPhamByLSP(int maLoaiSP);
}
