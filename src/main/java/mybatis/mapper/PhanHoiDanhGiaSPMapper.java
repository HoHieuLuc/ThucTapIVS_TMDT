package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PhanHoiDanhGiaSPMapper {

    // Thêm phản hồi cho đánh giá cụ thể
    final String THEM_PHAN_HOI_DANH_GIA_SAN_PHAM = "INSERT INTO `phan_hoi_danh_gia_sp` " +
            "VALUES (null, #{maDanhGia}, #{noiDung}, #{maKhachHang}, now(), null);";

    @Insert(THEM_PHAN_HOI_DANH_GIA_SAN_PHAM)
    public void themPhanHoiDanhGiaSP(@Param("maDanhGia") int maDanhGia, @Param("noiDung") String noiDung,
            @Param("maKhachHang") int maKhachHang);

    // Hiển thị các phản hồi của từng đánh giá sản phẩm
    final String PHAN_HOI_DANH_GIA_SP = "SELECT tk.avatar, tk.username, kh.ten, phdgsp.noi_dung, " +
            "phdgsp.ma_phan_hoi, " +
            "DATE_FORMAT(phdgsp.ngay_tao, '%d-%m-%Y lúc %T') AS ngay_tao, " +
            "DATE_FORMAT(phdgsp.ngay_sua, '%d-%m-%Y lúc %T') AS ngay_sua " +
            "FROM phan_hoi_danh_gia_sp phdgsp JOIN khach_hang kh on kh.ma_khach_hang = phdgsp.ma_khach_hang " +
            "JOIN tai_khoan tk on kh.id_tai_khoan = tk.id WHERE phdgsp.ma_danh_gia = #{maDanhGia};";

    @Select(PHAN_HOI_DANH_GIA_SP)
    public List<Map<String, Object>> getPhanHoiDGSP(int maDanhGia);

    // là phản hồi của khách hàng
    final String LA_PHAN_HOI_CUA_KHACH_HANG = "SELECT COUNT(*) FROM phan_hoi_danh_gia_sp " +
            "WHERE ma_phan_hoi = #{maPhanHoi} " +
            "AND ma_khach_hang = #{maKhachHang}";

    @Select(LA_PHAN_HOI_CUA_KHACH_HANG)
    public int laPhanHoiCuaKhachHang(@Param("maPhanHoi") int maPhanHoi,
            @Param("maKhachHang") int maKhachHang);

    // cập nhật phản hồi
    final String CAP_NHAT_PHAN_HOI = "UPDATE phan_hoi_danh_gia_sp SET noi_dung = #{noiDung}, " +
            "ngay_sua = now() WHERE ma_phan_hoi = #{maPhanHoi} AND ma_khach_hang = #{maKhachHang}";

    @Update(CAP_NHAT_PHAN_HOI)
    public int capNhatPhanHoi(@Param("maPhanHoi") int maPhanHoi, @Param("noiDung") String noiDung,
            @Param("maKhachHang") int maKhachHang);

    // xóa phản hồi
    final String XOA_PHAN_HOI = "DELETE FROM phan_hoi_danh_gia_sp WHERE ma_phan_hoi = #{maPhanHoi} " +
            "AND ma_khach_hang = #{maKhachHang}";

    @Delete(XOA_PHAN_HOI)
    public int xoaPhanHoi(@Param("maPhanHoi") int maPhanHoi, @Param("maKhachHang") int maKhachHang);
}