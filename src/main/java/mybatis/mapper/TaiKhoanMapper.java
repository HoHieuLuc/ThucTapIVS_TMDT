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
import com.tmdt.khachhang.model.*;


public interface TaiKhoanMapper {

    // Thêm tài khoản mới cho khách hàng
    final String INSERT_TAI_KHOAN ="INSERT INTO `tai_khoan` (`username`, `password`, `email`, `ngay_tao`, `so_dien_thoai`, `ngay_sinh`, `gioi_tinh`, `so_lan_canh_cao`, `status`, `ma_quyen`) VALUES (#{username}, #{password}, #{email}, #{ngay_tao}, #{so_dien_thoai}, #{ngay_sinh}, #{gioi_tinh},#{so_lan_canh_cao},#{status},#{ma_quyen});";
    @Insert(INSERT_TAI_KHOAN)
    @Options(useGeneratedKeys = true,keyProperty ="id")
    public void insert(TaiKhoan taiKhoan);

    //Lấy id tài khoản vừa tạo
    final String MAX_INSERT_ID = "SELECT MAX(id) FROM `tai_khoan`;";
    @Select(MAX_INSERT_ID)
    public int getMax();

    final String GET_ACCOUNT_BY_USERNAME = "SELECT * FROM `tai_khoan` WHERE USERNAME = #{username} LIMIT 1";
    @Select(GET_ACCOUNT_BY_USERNAME)
	public TaiKhoan getByUsername(String username);
    
}
