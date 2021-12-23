package mybatis.mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import com.tmdt.model.*;


public interface TaiKhoanMapper {

    // Thêm tài khoản mới cho khách hàng
    final String INSERT_TAI_KHOAN ="INSERT INTO `tai_khoan` (`username`, `password`, `email`, `ngay_tao`, `so_dien_thoai`, `ngay_sinh`, `gioi_tinh`, `so_lan_canh_cao`, `status`, `ma_quyen`,`avatar`) VALUES (#{userName}, #{password}, #{email}, #{ngayTao}, #{soDienThoai}, #{ngaySinh}, #{gioiTinh},#{soLanCanhCao},#{status},#{maQuyen},#{avatar});";
    @Insert(INSERT_TAI_KHOAN)
    @Options(useGeneratedKeys = true,keyProperty ="id")
    public void insert(TaiKhoan taiKhoan);

    //Lấy id tài khoản vừa tạo
    final String NEW_ID = "SELECT id FROM `tai_khoan` WHERE username=#{username};";
    @Select(NEW_ID)
    public int getCurrentInsertId(String username);



    final String GET_ACCOUNT_BY_USERNAME = "SELECT * FROM `tai_khoan` WHERE USERNAME = #{username} LIMIT 1";
    @Select(GET_ACCOUNT_BY_USERNAME)
    @Results({
        @Result(property = "id", column = "id", id = true),
        @Result(property = "userName", column = "username"),
        @Result(property = "password", column = "password"),
        @Result(property="email", column = "email"),
        @Result(property = "ngayTao", column = "ngay_tao"),
        @Result(property = "ngaySinh", column = "ngay_sinh"),
        @Result(property = "gioiTinh", column = "gioi_tinh"),
        @Result(property = "soLanCanhCao",column = "so_lan_canh_cao")
      })
	public TaiKhoan getByUsername(String username);
    
}
