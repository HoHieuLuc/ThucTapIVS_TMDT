package mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

import com.tmdt.model.*;

public interface KhachHangMapper {
    final String INSERT_KHACH_HANG = "INSERT INTO `khach_hang` (`ten`, `dia_chi`, `id_tai_khoan`, `tien_no`, `gioi_thieu`) VALUES (#{ten},#{diaChi},#{idTaiKhoan},#{tienNo},#{gioiThieu})";

    @Insert(INSERT_KHACH_HANG)
    @Options(useGeneratedKeys = true, keyProperty = "maKhachHang", keyColumn = "ma_khach_hang")
    public void insert(KhachHang khachHang);

    // Lấy mã khách hàng dựa theo id của tài khoản (id sẽ được lưu trong session)
    final String GET_MA_KHACH_HANG = "SELECT ma_khach_hang from `khach_hang` WHERE id_tai_khoan = #{id}";

    @Select(GET_MA_KHACH_HANG)
    public int getMaKh(int id);

    // lấy thông tin khách hàng cho trang store dựa vào username
    final String GET_STORE_INFO = "SELECT kh.ten, kh.dia_chi, kh.gioi_thieu, tk.email, tk.so_dien_thoai, " +
            "tk.avatar, ttlh.twitter_link, ttlh.facebook_link, ttlh.personal_link, AVG(dgkh.so_sao) AS rating " +
            "FROM khach_hang kh " +
            "JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
            "LEFT JOIN thong_tin_lien_he ttlh ON ttlh.ma_khach_hang = kh.ma_khach_hang " +
            "LEFT JOIN danh_gia_khach_hang dgkh ON dgkh.ma_kh_duoc_danh_gia = kh.ma_khach_hang " +
            "WHERE tk.username = #{username}";

    @Select(GET_STORE_INFO)
    public Map<String, Object> getStoreInfoByUsername(String username);
}