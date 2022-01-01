package mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GioHangMapper {
    // Hàm lấy giỏ hàng của khách đang đăng nhập
     final String GET_GIO_HANG_BY_MA_KH = "SELECT gh.ma_san_pham,gh.so_luong " +
     " FROM gio_hang GH LEFT JOIN khach_hang kh " +
     " ON kh.ma_khach_hang = gh.ma_khach_hang WHERE kh.ma_khach_hang = #{maKhachHang} ";  
     @Select(GET_GIO_HANG_BY_MA_KH) 
     public void getGioHangByMaKH(int maKhachHang);

     //Hàm thêm sản phẩm vào giỏ hàng
      final String THEM_SP_VAO_GIO_HANG =  "INSERT INTO `gio_hang` (`ma_khach_hang`, `ma_san_pham`, `so_luong`) " +
      "VALUES (#{maKhacHang},#{maSanPham},'1')";
      @Insert(THEM_SP_VAO_GIO_HANG)
      public void themSP_GioHang(@Param("maKhachHang") int maKhachHang,@Param("maSanPham") String maSanPham);
}
