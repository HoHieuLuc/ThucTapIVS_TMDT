package mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.Map;

import com.tmdt.model.*;

public interface TaiKhoanMapper {
    // Thêm tài khoản mới cho khách hàng
    final String INSERT_TAI_KHOAN = "INSERT INTO `tai_khoan` (`username`, `password`, `email`, `ngay_tao`, `so_dien_thoai`, `ngay_sinh`, `gioi_tinh`, `so_lan_canh_cao`, `status`, `ma_quyen`,`avatar`) VALUES (#{userName}, #{password}, #{email}, now(), #{soDienThoai}, #{ngaySinh}, #{gioiTinh},#{soLanCanhCao},#{status},#{maQuyen},#{avatar});";

    @Insert(INSERT_TAI_KHOAN)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TaiKhoan taiKhoan);

    // Lấy id tài khoản bằng username
    final String GET_ID_BY_USERNAME = "SELECT id FROM `tai_khoan` WHERE username=#{username};";

    @Select(GET_ID_BY_USERNAME)
    public int getIdByUsername(String username);

    final String GET_ACCOUNT_BY_USERNAME = "SELECT * FROM `tai_khoan` WHERE USERNAME = #{username} LIMIT 1";

    @Select(GET_ACCOUNT_BY_USERNAME)
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "userName", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "soDienThoai", column = "so_dien_thoai"),
            @Result(property = "ngaySinh", column = "ngay_sinh"),
            @Result(property = "gioiTinh", column = "gioi_tinh"),
            @Result(property = "soLanCanhCao", column = "so_lan_canh_cao"),
            @Result(property = "status", column = "status"),
            @Result(property = "maQuyen", column = "ma_quyen"),
            @Result(property = "avatar", column = "avatar")
    })
    public TaiKhoan getByUsername(String username);

    // lấy thông tin đăng nhập cho khách hàng
    final String GET_KH_LOGIN_INFO_BY_USERNAME = "SELECT tk.id, kh.ma_khach_hang, kh.ten, q.cap_do, tk.avatar "
            +
            "FROM tai_khoan tk JOIN khach_hang kh on kh.id_tai_khoan = tk.id " +
            "JOIN quyen q ON q.ma_quyen = tk.ma_quyen WHERE tk.username = #{username}";

    @Select(GET_KH_LOGIN_INFO_BY_USERNAME)
    @Results({
            @Result(property = "accountID", column = "id", id = true),
            @Result(property = "maNguoiDung", column = "ma_khach_hang"),
            @Result(property = "ten", column = "ten"),
            @Result(property = "level", column = "cap_do"),
            @Result(property = "avatar", column = "avatar")
    })
    public Map<String, Object> getKhLoginInfoByUsername(String username);

    // Lấy thông tin đăng nhập cho nhân viên
    final String GET_NV_LOGIN_INFO_BY_USERNAME = "SELECT tk.id, nv.ma_nhan_vien, nv.ten_nhan_vien, q.cap_do, tk.avatar "
            +
            "FROM tai_khoan tk JOIN nhan_vien nv on nv.id_tai_khoan = tk.id " +
            "JOIN quyen q ON q.ma_quyen = tk.ma_quyen WHERE tk.username = #{username}";

    @Select(GET_NV_LOGIN_INFO_BY_USERNAME)
    @Results({
            @Result(property = "accountID", column = "id", id = true),
            @Result(property = "maNguoiDung", column = "ma_nhan_vien"),
            @Result(property = "ten", column = "ten_nhan_vien"),
            @Result(property = "level", column = "cap_do"),
            @Result(property = "avatar", column = "avatar")
    })
    public Map<String, Object> getNvLoginInfoByUsername(String username);

}
