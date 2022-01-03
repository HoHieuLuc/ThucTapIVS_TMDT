package mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface DatHangMapper {
    //To obtain the value immediately after an INSERT, use a SELECT query with the LAST_INSERT_ID() function.
    //Tham khảo từ (https://dev.mysql.com/)
    final String THEM_DON_DH_MOI = "INSERT INTO `dat_hang`(`ma_dat_hang`, `ma_khach_hang`, `ngay_dat`, `tong_tien`, `tinh_trang`) "+ 
    "VALUES (NULL,#{maKhachHang},now(),(select sum(gh.so_luong*sp.gia) from gio_hang gh join san_pham sp on sp.ma_san_pham = gh.ma_san_pham where gh.ma_khach_hang = 1),0)";
    @Insert(THEM_DON_DH_MOI)
    public void themDonDHMoi(
        @Param("maKhachHang") int maKhachHang
    );

}
