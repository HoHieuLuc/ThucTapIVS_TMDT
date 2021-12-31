package mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.tmdt.model.LoaiSanPham;

import org.apache.ibatis.annotations.*;

public interface LoaiSanPhamMapper {
    
    final String GET_ALL_LOAI_SAN_PHAM = "SELECT * FROM loai_san_pham";
    @Select(GET_ALL_LOAI_SAN_PHAM)
    @Results({
        @Result(property = "maLoaiSanPham", column = "ma_loai_sp"),
        @Result(property = "tenLoaiSanPham", column = "ten_loai_sp")
    })
    public List<LoaiSanPham> getAllLoaiSanPham();

    final String GET_SAN_PHAM_BY_LSP = "SELECT sp.ma_san_pham, sp.ten_san_pham, sp.mo_ta, sp.gia, sp.status, kh.ma_khach_hang, kh.ten, lsp.ten_loai_sp, sp.so_luong, sp.ngay_dang, sp.so_luong_da_ban, asp.anh, AVG(dgsp.so_sao) AS xep_hang "
    +
    "FROM SAN_PHAM sp JOIN LOAI_SAN_PHAM lsp ON sp.MA_LOAI_SAN_PHAM = lsp.MA_LOAI_SP " +
    "JOIN khach_hang kh ON kh.ma_khach_hang = sp.ma_khach_hang " +
    "JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
    "LEFT JOIN danh_gia_san_pham dgsp ON dgsp.ma_san_pham = sp.ma_san_pham " + " WHERE sp.ma_loai_sp = #{maLoaiSP} " +
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
