package mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface DatHangMapper {
    //To obtain the value immediately after an INSERT, use a SELECT query with the LAST_INSERT_ID() function.
    //Tham khảo từ (https://dev.mysql.com/)
    final String THEM_DON_DH_MOI = "INSERT INTO `dat_hang`( `ma_khach_hang`, `ngay_dat`, `tong_tien`, `tinh_trang`) " +
        "VALUES (#{maKhachHang},now(),#{tongTien},#{tinhTrang}); " +
    "INSERT INTO `chi_tiet_dat_hang`(`ma_dat_hang`, `ma_san_pham`, `so_luong`) " +
        "VALUES (LAST_INSERT_ID(),#{maSanPham},#{soLuong});";
    @Insert(THEM_DON_DH_MOI)
    public void themDonDHMoi(
        @Param("maKhachHang") int maKhachHang,
        @Param("tongTien") int tongTien,
        @Param("tinhTrang") int tinhTrang,
        @Param("maSanPham") String maSanPham,
        @Param("soLuong") int soLuong

    );

}
