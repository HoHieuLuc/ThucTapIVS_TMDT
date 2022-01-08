package mybatis.mapper;

import org.apache.ibatis.annotations.*;


public interface BaoCaoNguoiDungMapper {
    // Ghi chú: id_nguoi_gui = (int) session.getAttribute("maNguoiDung");

    final String GUI_BAO_CAO_NGUOI_DUNG = "INSERT INTO `bao_cao_nguoi_dung` (`ma_bao_cao`, `id_nguoi_nhan`, `id_nguoi_gui`, `noi_dung`, `status`, `ngay_tao`) "
            +
            "VALUES (NULL, #{idNguoiNhan}, #{idNguoiGui}, #{noiDung}, '0', now());";

    @Insert(GUI_BAO_CAO_NGUOI_DUNG)
    public int guiBaoCaoNguoiDung(
            @Param("idNguoiNhan") int idNguoiNhan,
            @Param("idNguoiGui") int idNguoiGui,
            @Param("noiDung") String noiDung      
     );

    // Lấy mã khách hàng được đánh giá dựa vào username của khách hàng đó (ở trang
    // store)
    // id_nguoi_nhan
    public final String GET_MA_NGUOI_DUNG_BI_BAO_CAO = "SELECT id FROM tai_khoan WHERE username = #{userName};";
            @Select(GET_MA_NGUOI_DUNG_BI_BAO_CAO)
            public int getMaNguoiDungBiBaoCao(String userName);

        //Chặn mình tự báo cáo chính mình sẽ làm bên action (so sánh mã người nhận = mã người gửi)
}
