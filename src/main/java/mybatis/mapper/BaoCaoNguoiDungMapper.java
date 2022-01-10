package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface BaoCaoNguoiDungMapper {
        // Ghi chú: id_nguoi_gui = (int) session.getAttribute("maNguoiDung");

        final String GUI_BAO_CAO_NGUOI_DUNG = "INSERT INTO `bao_cao_nguoi_dung` (`ma_bao_cao`, `id_nguoi_nhan`, `id_nguoi_gui`, `noi_dung`, `status`, `ngay_tao`) "
                        +
                        "VALUES (NULL, #{idNguoiNhan}, #{idNguoiGui}, #{noiDung}, 0, now());";

        @Insert(GUI_BAO_CAO_NGUOI_DUNG)
        public int guiBaoCaoNguoiDung(
                        @Param("idNguoiNhan") int idNguoiNhan,
                        @Param("idNguoiGui") int idNguoiGui,
                        @Param("noiDung") String noiDung);

        // Hiển thị danh sách các báo cáo theo trạng thái
        final String LIST_BAO_CAO_BY_STATUS = "SELECT bcnd.ma_bao_cao,tk2.username as 'unameSender',tk1.username as 'unameReceiver',bcnd.ngay_tao,bcnd.noi_dung "
                        +
                        "FROM bao_cao_nguoi_dung bcnd LEFT JOIN tai_khoan tk1 ON tk1.id = bcnd.id_nguoi_nhan " +
                        "LEFT JOIN tai_khoan tk2 ON tk2.id = bcnd.id_nguoi_gui WHERE bcnd.status = #{status};";

        @Select(LIST_BAO_CAO_BY_STATUS)
        public List<Map<String, Object>> listBaoCaoByStatus(int status);

        // Cập nhật trạng thái cho báo cáo
        final String UPDATE_BAO_CAO_STATUS = "UPDATE `bao_cao_nguoi_dung` SET `status` = #{status} WHERE `bao_cao_nguoi_dung`.`ma_bao_cao` = #{maBaoCao};";

        @Update(UPDATE_BAO_CAO_STATUS)
        public int updateBaoCaoStatus(
                        @Param("status") int status,
                        @Param("maBaoCao") int maBaoCao);

        // Tăng số lần cảnh báo cho tài khoản bị cảnh báo ngay khi status được cập nhật
        // sang -1
        final String TANG_SO_LAN_CANH_BAO = "UPDATE `tai_khoan` SET `so_lan_canh_cao` = `so_lan_canh_cao` + #{number} WHERE `tai_khoan`.`id` = #{idNguoiNhan};";

        @Update(TANG_SO_LAN_CANH_BAO)
        //public int tangSoLanCanhBao(int idNguoiNhan);
        public int tangSoLanCanhBao(
                @Param("idNguoiNhan") int idNguoiNhan,
                @Param("number") int number
        );

        // Hiển thị chi tiết báo cáo cụ thể
        final String DETAIL_BAO_CAO = "SELECT bcnd.ma_bao_cao,tk2.username as 'unameSender',tk1.username as 'unameReceiver',bcnd.ngay_tao,bcnd.noi_dung,bcnd.status "
                        +
                        "FROM bao_cao_nguoi_dung bcnd LEFT JOIN tai_khoan tk1 ON tk1.id = bcnd.id_nguoi_nhan " +
                        "LEFT JOIN tai_khoan tk2 ON tk2.id = bcnd.id_nguoi_gui WHERE bcnd.ma_bao_cao = #{maBaoCao};";

        @Select(DETAIL_BAO_CAO)
        public Map<String, Object> detaiBaoCao(int maBaoCao);

        //Lấy số lần cảnh cáo để nhắc nhở người bị báo cáo
        final String GET_SO_LAN_CANH_CAO = "SELECT so_lan_canh_cao FROM tai_khoan WHERE username = #{userName}";
        @Select(GET_SO_LAN_CANH_CAO)
        public int getSoLanCanhCao(String userName);

}
