package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface ThongBaoMapper {
    final String TAO_THONG_BAO = "INSERT INTO `thong_bao` (`ma_tb`, `id_nguoi_nhan`, `id_nguoi_gui`, `noi_dung`, `ngay_tao`, `status`) "
            +
            "VALUES (NULL, #{idNguoiNhan}, #{idNguoiGui}, #{noiDung}, now(), 0); ";

    @Insert(TAO_THONG_BAO)
    public void taoThongBao(
            @Param("idNguoiNhan") int idNguoiNhan,
            @Param("idNguoiGui") int idNguoiGui,
            @Param("noiDung") String noiDung);

    // Lấy tất cả thông báo của khách hàng có id
    final String GET_ALL_THONG_BAO = "SELECT tb.ma_tb, tb.noi_dung, " +
            "DATE_FORMAT(tb.ngay_tao, '%d-%m-%Y lúc %T') AS ngay_tao, " +
            "CASE " +
            "   WHEN (SELECT ma_quyen FROM tai_khoan WHERE id = tb.id_nguoi_gui  ) = 'admin' THEN 'Hệ thống' "
            +
            "    ELSE (SELECT ten FROM khach_hang kh JOIN tai_khoan tk ON kh.id_tai_khoan = tk.id WHERE tk.id = tb.id_nguoi_gui ) "
            +
            "END AS nguoi_gui, " +
            "tb.status " +
            "FROM thong_bao tb  WHERE `id_nguoi_nhan` = #{idNguoiNhan} " +
            "ORDER BY tb.ngay_tao DESC";

    @Select(GET_ALL_THONG_BAO)
    public List<Map<String, Object>> getAllThongBao(int idNguoiNhan);

    // Lấy tất cả thông báo chưa đọc của khách hàng có id
    final String GET_ALL_THONG_BAO_CHUA_DOC = "SELECT tb.ma_tb, tb.noi_dung, " +
            "DATE_FORMAT(tb.ngay_tao, '%d-%m-%Y lúc %T') AS ngay_tao, " +
            "CASE " +
            "   WHEN (SELECT ma_quyen FROM tai_khoan WHERE id = tb.id_nguoi_gui  ) = 'admin' THEN 'Hệ thống' "
            +
            "   ELSE (SELECT ten FROM khach_hang kh JOIN tai_khoan tk ON kh.id_tai_khoan = tk.id WHERE tk.id = tb.id_nguoi_gui ) "
            +
            "END AS nguoi_gui, " +
            "tb.status " +
            "FROM thong_bao tb  WHERE `id_nguoi_nhan` = #{idNguoiNhan} AND `status` = 0 " +
            "ORDER BY tb.ngay_tao DESC";

    @Select(GET_ALL_THONG_BAO_CHUA_DOC)
    public List<Map<String, Object>> getAllThongBaoChuaDocs(int idNguoiNhan);

    // Đánh dấu tất cả thông báo là đã đọc
    final String DANH_DAU_ALL_DA_DOC = "UPDATE thong_bao SET status = 1 WHERE id_nguoi_nhan = #{idNguoiNhan} AND status = 0;";

    @Update(DANH_DAU_ALL_DA_DOC)
    public int danhDauAllDaDoc(int idNguoiNhan);

    // Đếm số thông báo chưa đọc
    final String DEM_SO_THONG_BAO_CHUA_DOC = "SELECT COUNT(*) FROM `thong_bao` WHERE id_nguoi_nhan = #{idNguoiNhan} AND status = 0";

    @Select(DEM_SO_THONG_BAO_CHUA_DOC)
    public int demSoThongBaoChuaDoc(int idNguoiNhan);

    // Đánh dấu đã đọc cho từng thông báo cụ thể
    final String DANH_DAU_DA_DOC = "UPDATE thong_bao SET status = 1 WHERE ma_tb = #{maThongBao} AND id_nguoi_nhan = #{idNguoiNhan} AND status = 0;";

    @Update(DANH_DAU_DA_DOC)
    public int danhDauDaDoc(
            @Param("maThongBao") int maThongBao,
            @Param("idNguoiNhan") int idNguoiNhan);

    // Lấy tất cả thông báo của khách hàng có id
    final String GET_RECENTLY_THONG_BAO = "SELECT tb.ma_tb, tb.noi_dung, " +
            "DATE_FORMAT(tb.ngay_tao, '%d-%m-%Y lúc %T') AS ngay_tao, " +
            "CASE " +
            "   WHEN (SELECT ma_quyen FROM tai_khoan WHERE id = tb.id_nguoi_gui  ) = 'admin' THEN 'Hệ thống' "
            +
            "    ELSE (SELECT ten FROM khach_hang kh JOIN tai_khoan tk ON kh.id_tai_khoan = tk.id WHERE tk.id = tb.id_nguoi_gui ) "
            +
            "END AS nguoi_gui, " +
            "tb.status " +
            "FROM thong_bao tb  WHERE `id_nguoi_nhan` = #{idNguoiNhan} " +
            "ORDER BY tb.ngay_tao DESC LIMIT 5;";

    @Select(GET_RECENTLY_THONG_BAO)
    public List<Map<String, Object>> getRecentlyThongBao(int idNguoiNhan);

    // xóa thông báo
    final String DELETE_THONG_BAO = "DELETE FROM thong_bao " +
            "WHERE ma_tb = #{maThongBao} AND id_nguoi_nhan = #{idNguoiNhan}";

    @Delete(DELETE_THONG_BAO)
    public int deleteThongBao(
            @Param("maThongBao") int maThongBao,
            @Param("idNguoiNhan") int idNguoiNhan);
}
