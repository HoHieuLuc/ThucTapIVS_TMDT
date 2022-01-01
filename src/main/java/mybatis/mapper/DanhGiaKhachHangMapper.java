package mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.tmdt.model.DanhGiaKhachHang;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface DanhGiaKhachHangMapper {
    public final String THEM_DANH_GIA_KHACH_HANG = "INSERT INTO `danh_gia_khach_hang` " +
            "(`ma_danh_gia`, `ma_kh_danh_gia`, `ma_kh_duoc_danh_gia`, `so_sao`, `ngay_tao`, `ngay_sua`) " +
            "VALUES (NULL, #{maKHDanhGia}, #{maKHDuocDanhGia}, #{soSao}, now(), null);";

    @Insert(THEM_DANH_GIA_KHACH_HANG)
    @Options(useGeneratedKeys = true, keyProperty = "maDanhGia")
    public void themDGKhachHang(DanhGiaKhachHang dgkh);

    // Kiểm tra khách hàng đã đánh giá
    public final String KIEM_TRA_KH_DA_DANH_GIA = "SELECT * FROM `danh_gia_khach_hang` " +
            "WHERE ma_kh_danh_gia = #{maKHDanhGia} and ma_kh_duoc_danh_gia = #{maKHDuocDanhGia};";
    @Select(KIEM_TRA_KH_DA_DANH_GIA)
    public List<Map<String,Object>> kiemTraDanhGia();

}
