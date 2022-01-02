package mybatis.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GioHangMapper {
//Lấy danh sách mã người bán từ giỏ hàng của khách hàng 1
final String SELLER_LIST = "SELECT tk.username,kh2.ten from gio_hang gh JOIN khach_hang kh on gh.ma_khach_hang = kh.ma_khach_hang join " +
" san_pham sp on sp.ma_san_pham = gh.ma_san_pham " +
" join khach_hang kh2  on kh2.ma_khach_hang = sp.ma_khach_hang " +
" JOIN tai_khoan tk on tk.id = kh2.id_tai_khoan  where gh.ma_khach_hang = #{maKhachHang} GROUP BY sp.ma_khach_hang;";
@Select(SELLER_LIST)
public List<Map<String,Object>> getSellerList(int maKhachHang);

//ví dụ mã người bán = {15,16,6} trong bảng sản phẩm , mã khách hàng người mua trong bảng giỏ hàng = {1}
// Từ danh sách người bán, chạy vòng lặp in ra các sản phẩm vừa có trong giỏ hàng, vừa có mã người bán như trên
final String GET_GH_INFO_BY_SELLER_ID = "SELECT sp.ten_san_pham,sp.gia as don_gia,sp.gia*gh.so_luong as tong_tien,sp.ma_san_pham,asp.anh,gh.so_luong " +
                " FROM gio_hang gh JOIN san_pham sp on gh.ma_san_pham =sp.ma_san_pham " +
                " JOIN khach_hang kh on sp.ma_khach_hang = kh.ma_khach_hang " +
                " JOIN anh_san_pham asp on asp.ma_san_pham = sp.ma_san_pham " +
                " JOIN khach_hang kh2 on sp.ma_khach_hang = kh2.ma_khach_hang " +
                " JOIN tai_khoan tk on kh2.id_tai_khoan = tk.id " +
                " WHERE gh.ma_khach_hang = #{maNguoiMua} and tk.username = #{userNameNguoiBan} GROUP by gh.ma_san_pham ";
@Select(GET_GH_INFO_BY_SELLER_ID)
public List<Map<String,Object>> getGH_Info_By_Seller_ID(@Param("maNguoiMua") int maNguoiMua,@Param("userNameNguoiBan") String userNaneNguoiBan); 


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
