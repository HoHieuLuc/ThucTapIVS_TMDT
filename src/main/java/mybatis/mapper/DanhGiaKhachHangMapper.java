package mybatis.mapper;

import com.tmdt.model.DanhGiaKhachHang;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DanhGiaKhachHangMapper {
    public final String THEM_DANH_GIA_KHACH_HANG = "INSERT INTO `danh_gia_khach_hang` " +
            "(`ma_kh_danh_gia`, `ma_kh_duoc_danh_gia`, `noi_dung`, `so_sao`, `ngay_tao`, `ngay_sua`) " +
            "VALUES (#{maKHDanhGia}, #{maKHDuocDanhGia}, #{noiDung}, #{soSao}, now(), null);";

    @Insert(THEM_DANH_GIA_KHACH_HANG)
    @Options(useGeneratedKeys = true, keyProperty = "maDanhGia")
    public void themDGKhachHang(DanhGiaKhachHang dgkh);

    // Kiểm tra khách hàng đã đánh giá
    public final String KIEM_TRA_KH_DA_DANH_GIA = "SELECT COUNT(*) FROM `danh_gia_khach_hang` " +
            "WHERE ma_kh_danh_gia = #{maKHDanhGia} and ma_kh_duoc_danh_gia = #{maKHDuocDanhGia};";

    @Select(KIEM_TRA_KH_DA_DANH_GIA)
    public int kiemTraDanhGia(@Param("maKHDanhGia") int maKHDanhGia,
            @Param("maKHDuocDanhGia") int maKHDuocDanhGia);

    // Lấy mã khách hàng được đánh giá dựa vào username của khách hàng đó (ở trang
    // store)
    public final String GET_MA_KH_DUOC_DANH_GIA = "SELECT kh.ma_khach_hang FROM khach_hang kh " +
            "JOIN tai_khoan tk on tk.id = kh.id_tai_khoan WHERE tk.username = #{userName};";

    @Select(GET_MA_KH_DUOC_DANH_GIA)
    public int getMaKHDuocDanhGia(String userName);
}
