package mybatis.mapper;

import com.tmdt.model.ThongTinLienHe;

import org.apache.ibatis.annotations.Insert;

public interface ThongTinLienHeMapper {
    // Thêm tài khoản mới cho khách hàng
    final String INSERT_THONG_TIN_LH = "INSERT INTO `thong_tin_lien_he` (`ma_khach_hang`, `twitter_link`, `facebook_link`, `personal_link`) " + 
    " VALUES (#{maKhachHang}, #{twitterLink}, #{facebookLink}, #{personalLink}); ";
    @Insert(INSERT_THONG_TIN_LH)
    public void insertThongTinLienHe(ThongTinLienHe thongTinLienHe);
    //(int maKhachHang, String twitterLink, String facebookLink, String personalLink) 
}