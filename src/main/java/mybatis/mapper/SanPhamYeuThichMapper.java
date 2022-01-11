package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface SanPhamYeuThichMapper {
    // Thêm sản phẩm yêu thích
    final String THEM_SP_YEU_THICH = "INSERT INTO `san_pham_yeu_thich` (`ma_khach_hang`, `ma_san_pham`) " +
            "VALUES (#{maKhachHang}, #{maSanPham});";

    @Insert(THEM_SP_YEU_THICH)
    public int themSPYeuThich(
            @Param("maKhachHang") int maKhachHang,
            @Param("maSanPham") String maSanPham);

    // Lấy danh sách sản phẩm yêu thích
    final String GET_SP_YEU_THICH = "SELECT * FROM `san_pham_yeu_thich` WHERE ma_khach_hang = #{maKhachHang};";

    @Select(GET_SP_YEU_THICH)
    public List<Map<String, Object>> getSPYeuThich(int maKhachHang);

    // Xóa 1 sản phẩm trong `san_pham_yeu_thich`
    final String XOA_SP_YEU_THICH = "DELETE FROM `san_pham_yeu_thich` " +
            "WHERE `ma_khach_hang` = #{maKhachHang}  AND `ma_san_pham` = #{maSanPham};";

    @Delete(XOA_SP_YEU_THICH)
    public int xoaSPYeuThich(
            @Param("maKhachHang") int maKhachHang,
            @Param("maSanPham") String maSanPham);

}
