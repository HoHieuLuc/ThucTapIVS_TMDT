package mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

import com.tmdt.model.*;


public interface KhachHangMapper {
    final String INSERT_KHACH_HANG = "INSERT INTO `khach_hang` (`ten`, `dia_chi`, `id_tai_khoan`, `tien_no`, `gioi_thieu`) VALUES (#{ten},#{diaChi},#{idTaiKhoan},#{tienNo},#{gioiThieu})";
    @Insert(INSERT_KHACH_HANG)
    @Options(useGeneratedKeys = true, keyProperty = "maKhachHang",keyColumn = "ma_khach_hang")
    public void insert(KhachHang khachHang);

    //Lấy mã khách hàng dựa theo id của tài khoản (id sẽ được lưu trong session)
    final String GET_MA_KHACH_HANG = "SELECT ma_khach_hang from `khach_hang` WHERE id_tai_khoan = #{id}";
    @Select(GET_MA_KHACH_HANG)
    public int getMaKh(int id);

    // lấy thông tin khách hàng dựa vào username
    final String GET_STORE_INFO = "SELECT * FROM khach_hang kh JOIN tai_khoan tk ON kh.id_tai_khoan = tk.id " + 
    "WHERE tk.username = #{username}";
    @Select(GET_STORE_INFO)
    public Map<String, Object> getStoreInfoByUsername(String username);
}