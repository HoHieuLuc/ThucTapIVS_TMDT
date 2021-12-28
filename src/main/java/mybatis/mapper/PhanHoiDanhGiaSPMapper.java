package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PhanHoiDanhGiaSPMapper {

    //Thêm phản hồi cho đánh giá cụ thể
    final String THEM_PHAN_HOI_DANH_GIA_SAN_PHAM = "INSERT INTO `phan_hoi_danh_gia_sp` (`ma_danh_gia`, `noi_dung`, `ma_khach_hang`, `ngay_tao`, `ngay_sua`) "
            +
            " VALUES (#{maDanhGia}, #{noiDung}, #{maKhachHang},now(),null);";
    @Insert(THEM_PHAN_HOI_DANH_GIA_SAN_PHAM)
    public void themPhanHoiDanhGiaSP(@Param("maDanhGia") int maDanhGia, @Param("noiDung") String noiDung, @Param("maKhachHang") int maKhachHang);

    //Hiển thị các phản hồi của từng đánh giá sản phẩm
    final String PHAN_HOI_DANH_GIA_SP = "SELECT tk.avatar,tk.username,kh.ten, phdgsp.noi_dung, phdgsp.ngay_tao, phdgsp.ngay_sua " +
    "FROM phan_hoi_danh_gia_sp phdgsp JOIN khach_hang kh on kh.ma_khach_hang = phdgsp.ma_khach_hang  " + 
    "JOIN tai_khoan tk on kh.id_tai_khoan = tk.id WHERE phdgsp.ma_danh_gia = #{maDanhGia};";
    @Select(PHAN_HOI_DANH_GIA_SP)
    public List<Map<String, Object>> getPhanHoiDGSP(int maDanhGia);

    
}