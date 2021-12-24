package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface SanPhamMapper {
    // lấy tất cả sản phẩm và 1 ảnh đầu tiên để làm ảnh đại diện cho từng sản phẩm
    final String GET_ALL_SANPHAM = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.mo_ta, sp.gia, sp.status, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, sp.so_luong_da_ban, asp.anh, AVG(dgsp.so_sao) AS xep_hang " +
    "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " + 
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
        @Result(property = "tenLoaiSanPham", column = "ten_loai_sp"),
        @Result(property = "soLuong", column = "so_luong"),
        @Result(property = "ngayDang", column = "ngay_dang"),
        @Result(property = "soLuongDaBan", column = "so_luong_da_ban"),
        @Result(property = "anhSanPham", column = "anh"),
        @Result(property = "xepHang", column = "xep_hang")
    })
    public List<Map<String, Object>> getAllSanPham();

    //Xem chi tiết sản phẩm
    final String SAN_PHAM_DETAIL = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.mo_ta, sp.gia, sp.status, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, sp.so_luong_da_ban, asp.anh, AVG(dgsp.so_sao) AS xep_hang " +
    "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " + 
    "JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " + 
    "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " + 
     "WHERE sp.ma_san_pham = #{maSanPham}" + "GROUP BY sp.ma_san_pham";
     @Select(SAN_PHAM_DETAIL)
     @Results(value = {
        @Result(property = "maSanPham", column = "ma_san_pham"),
        @Result(property = "tenSanPham", column = "ten_san_pham"),
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
    public List<Map<String, Object>> getDetailSanPham(String maSanPham);
}
