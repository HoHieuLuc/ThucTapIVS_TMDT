package mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

      //cập nhật số lượng sản phẩm trong giỏ hàng
      final String UPDATE_SO_LUONG_SP = "UPDATE `gio_hang` SET `ma_khach_hang` = #{maKhachHang}, `ma_san_pham` = #{maSanPham}, " +
      "`so_luong` = #{soLuong} " +
      "WHERE `gio_hang`.`ma_khach_hang` = #{maKhachHang} AND `gio_hang`.`ma_san_pham` = #{maSanPham};";
      @Update(UPDATE_SO_LUONG_SP)
      public void updateSoLuongSP(@Param("maKhachHang") int maKhachHang,@Param("maSanPham") String maSanPham,@Param("soLuong") int soLuong);


      //Xóa sản phẩm ra khỏi giỏ hàng
      final String DELETE_SP_GIO_HANG = "DELETE FROM `gio_hang` WHERE `gio_hang`.`ma_khach_hang` = #{maKhachHang} " +
      "AND `gio_hang`.`ma_san_pham` = #{maSanPham}";
      @Delete(DELETE_SP_GIO_HANG)
      public void deleteSP(@Param("maKhachHang") int maKhachHang,@Param("maSanPham") String maSanPham);
}
