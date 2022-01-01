package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GioHangMapper {

    //Lấy danh sách mã người bán từ giỏ hàng của khách hàng 1
    final String SELLER_LIST = "SELECT sp.ma_khach_hang as 'seller_id' from gio_hang gh JOIN khach_hang kh on gh.ma_khach_hang = kh.ma_khach_hang join san_pham sp on sp.ma_san_pham = gh.ma_san_pham where gh.ma_khach_hang = #{maKhachHang};";
    @Select(SELLER_LIST)
    public List<Integer> getSellerList(int maKhachHang);

    //ví dụ mã người bán = {15,16,6} trong bảng sản phẩm , mã khách hàng người mua trong bảng giỏ hàng = {1}
    // Từ danh sách người bán, chạy vòng lặp in ra các sản phẩm vừa có trong giỏ hàng, vừa có mã người bán như trên
    final String GET_GH_INFO_BY_SELLER_ID = "SELECT sp.ten_san_pham,sp.gia,gh.so_luong,kh.ten as 'seller_name' FROM gio_hang gh JOIN san_pham sp on gh.ma_san_pham =sp.ma_san_pham JOIN khach_hang kh on sp.ma_khach_hang = kh.ma_khach_hang " +
    " WHERE gh.ma_khach_hang = #{maNguoiMua} and sp.ma_khach_hang = #{maNguoiBan};";
    @Select(GET_GH_INFO_BY_SELLER_ID)
    public List<Map<String,Object>> getGH_Info_By_Seller_ID(@Param("maNguoiBan") int maKhachHang, @Param("maNguoiMua") int maNguoiMua); 

    
    // Hàm lấy giỏ hàng của khách đang đăng nhập
     final String GET_GIO_HANG_BY_MA_KH = "SELECT gh.ma_san_pham,gh.so_luong " +
     " FROM gio_hang GH LEFT JOIN khach_hang kh " +
     " ON kh.ma_khach_hang = gh.ma_khach_hang WHERE kh.ma_khach_hang = #{maKhachHang} ";  
     @Select(GET_GIO_HANG_BY_MA_KH)
     public List<Map<String,Object>> getGioHangByMaKH(int maKhachHang);

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
