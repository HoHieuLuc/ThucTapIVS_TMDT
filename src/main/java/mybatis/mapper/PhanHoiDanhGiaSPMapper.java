package mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface PhanHoiDanhGiaSPMapper {

    final String THEM_PHAN_HOI_DANH_GIA_SAN_PHAM = "INSERT INTO `phan_hoi_danh_gia_sp` (`ma_danh_gia`, `noi_dung`, `ma_khach_hang`, `ngay_tao`, `ngay_sua`) "
            +
            " VALUES (#{maDanhGia}, #{noiDung}, #{maKhachHang},now(),null);";
    @Insert(THEM_PHAN_HOI_DANH_GIA_SAN_PHAM)
    public void themPhanHoiDanhGiaSP(@Param("maDanhGia") int maDanhGia, @Param("noiDung") String noiDung, @Param("maKhachHang") int maKhachHang);

}