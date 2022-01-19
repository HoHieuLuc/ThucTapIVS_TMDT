package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface BaoCaoNguoiDungMapper {
    // Ghi chú: id_nguoi_gui = (int) session.getAttribute("maNguoiDung");

    final String GUI_BAO_CAO_NGUOI_DUNG = "INSERT INTO bao_cao_nguoi_dung " +
            "VALUES (NULL, #{idNguoiNhan}, #{idNguoiGui}, #{noiDung}, 0, now());";

    @Insert(GUI_BAO_CAO_NGUOI_DUNG)
    public int guiBaoCaoNguoiDung(
            @Param("idNguoiNhan") int idNguoiNhan,
            @Param("idNguoiGui") int idNguoiGui,
            @Param("noiDung") String noiDung);

    // đến các báo cáo theo trạng thái để phân trang
    final String COUNT_BAO_CAO_BY_STATUS = "SELECT COUNT(*) FROM bao_cao_nguoi_dung bcnd " +
            "LEFT JOIN tai_khoan tk1 ON tk1.id = bcnd.id_nguoi_nhan " +
            "LEFT JOIN tai_khoan tk2 ON tk2.id = bcnd.id_nguoi_gui " +
            "WHERE bcnd.status = #{status} " +
            "AND (tk1.username LIKE CONCAT('%', #{search}, '%') " +
            "OR tk2.username LIKE CONCAT('%', #{search}, '%'))";

    @Select(COUNT_BAO_CAO_BY_STATUS)
    public int countBaoCaoByStatus(@Param("status") int status, @Param("search") String search);

    // Hiển thị danh sách các báo cáo theo trạng thái
    final String LIST_BAO_CAO_BY_STATUS = "SELECT bcnd.ma_bao_cao,tk2.username as 'unameSender', " +
            "tk1.username as 'unameReceiver', bcnd.noi_dung, " +
            "DATE_FORMAT(bcnd.ngay_tao, '%d-%m-%Y lúc %T') AS ngay_tao " +
            "FROM bao_cao_nguoi_dung bcnd LEFT JOIN tai_khoan tk1 ON tk1.id = bcnd.id_nguoi_nhan " +
            "LEFT JOIN tai_khoan tk2 ON tk2.id = bcnd.id_nguoi_gui " +
            "WHERE bcnd.status = #{status} " +
            "AND (tk1.username LIKE CONCAT('%', #{search}, '%') " +
            "OR tk2.username LIKE CONCAT('%', #{search}, '%')) " +
            "ORDER BY bcnd.ngay_tao DESC "+
            "LIMIT #{offset}, #{rowsPerPage}";

    @Select(LIST_BAO_CAO_BY_STATUS)
    public List<Map<String, Object>> listBaoCaoByStatus(@Param("status") int status,
            @Param("search") String search,
            @Param("offset") int offset,
            @Param("rowsPerPage") int rowsPerPage);

    // Cập nhật trạng thái cho báo cáo
    final String UPDATE_BAO_CAO_STATUS = "UPDATE bao_cao_nguoi_dung SET status = #{status} WHERE bao_cao_nguoi_dung.ma_bao_cao = #{maBaoCao};";

    @Update(UPDATE_BAO_CAO_STATUS)
    public int updateBaoCaoStatus(
            @Param("status") int status,
            @Param("maBaoCao") int maBaoCao);

    // Tăng số lần cảnh báo cho tài khoản bị cảnh báo ngay khi status được cập nhật
    // sang -1
    final String TANG_SO_LAN_CANH_BAO = "UPDATE tai_khoan SET so_lan_canh_cao = so_lan_canh_cao + #{number} WHERE tai_khoan.id = #{idNguoiNhan};";

    // get báo cáo
    final String GET_BAO_CAO = "SELECT * FROM bao_cao_nguoi_dung " +
            "WHERE ma_bao_cao = #{maBaoCao}";

    @Select(GET_BAO_CAO)
    public Map<String, Object> getBaoCao(@Param("maBaoCao") int maBaoCao);

    @Update(TANG_SO_LAN_CANH_BAO)
    // public int tangSoLanCanhBao(int idNguoiNhan);
    public int tangSoLanCanhBao(
            @Param("idNguoiNhan") int idNguoiNhan,
            @Param("number") int number);

    // Hiển thị chi tiết báo cáo cụ thể
    final String DETAIL_BAO_CAO = "SELECT bcnd.ma_bao_cao, tk2.username as 'unameSender', " +
            "tk1.username as 'unameReceiver', bcnd.noi_dung, bcnd.status, " +
            "DATE_FORMAT(bcnd.ngay_tao, '%d-%m-%Y lúc %T') AS ngay_tao " +
            "FROM bao_cao_nguoi_dung bcnd LEFT JOIN tai_khoan tk1 ON tk1.id = bcnd.id_nguoi_nhan " +
            "LEFT JOIN tai_khoan tk2 ON tk2.id = bcnd.id_nguoi_gui WHERE bcnd.ma_bao_cao = #{maBaoCao};";

    @Select(DETAIL_BAO_CAO)
    public Map<String, Object> detailBaoCao(int maBaoCao);

    // Lấy số lần cảnh cáo để nhắc nhở người bị báo cáo
    final String GET_SO_LAN_CANH_CAO = "SELECT so_lan_canh_cao FROM tai_khoan WHERE username = #{userName}";

    @Select(GET_SO_LAN_CANH_CAO)
    public int getSoLanCanhCao(String userName);

    // Khóa tài khoản người vi phạm nặng
    final String KHOA_TAI_KHOAN = "UPDATE tai_khoan SET so_lan_canh_cao = 3 WHERE tai_khoan.id = #{idNguoiNhan};";

    @Select(KHOA_TAI_KHOAN)
    public int khoaTaiKhoan(int idNguoiNhan);

}
