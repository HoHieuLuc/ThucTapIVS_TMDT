package mybatis.mapper;

import org.apache.ibatis.annotations.*;

public interface ThongBaoMapper {
    final String TAO_THONG_BAO = "INSERT INTO `thong_bao` (`ma_tb`, `id_nguoi_nhan`, `id_nguoi_gui`, `noi_dung`, `ngay_tao`, `status`) " +
                "VALUES (NULL, #{idNguoiNhan}, #{idNguoiGui}, #{noiDung}, now(), 0); ";
    @Insert(TAO_THONG_BAO)
    public void taoThongBao(
        @Param("idNguoiNhan") int idNguoiNhan,
        @Param("idNguoiGui") int idNguoiGui,
        @Param("noiDung") String noiDung
    );
}
