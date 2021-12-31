package mybatis.mapper;

import com.tmdt.model.DanhGiaKhachHang;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface DanhGiaKhachHangMapper {
    public final String THEM_DANH_GIA_KHACH_HANG = "INSERT INTO `danh_gia_khach_hang` " +
     "(`ma_danh_gia`, `ma_kh_danh_gia`, `ma_kh_duoc_danh_gia`, `so_sao`, `ngay_tao`, `ngay_sua`) " +
     "VALUES (NULL, #{maKHDanhGia}, #{maKHDuocDanhGia}, #{soSao}, now(), null);";
     @Insert(THEM_DANH_GIA_KHACH_HANG)
     @Options(useGeneratedKeys = true,keyProperty = "maDanhGia")
     public void themDGKhachHang(DanhGiaKhachHang dgkh);
     
}
