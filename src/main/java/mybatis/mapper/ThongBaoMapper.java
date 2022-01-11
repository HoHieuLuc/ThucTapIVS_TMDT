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
    final String GET_ALL_THONG_BAO ="SELECT*FROM`thong_bao`WHERE`id_nguoi_nhan`=#{idNguoiNhan};";
    @Select(GET_ALL_THONG_BAO)
    public List<Map<String, Object>> getAllThongBao(int idNguoiNhan); 

    // Lấy tất cả thông báo chưa đọc của khách hàng có id 
    final String GET_ALL_THONG_BAO_CHUA_DOC =  "SELECT*FROM`thong_bao`WHERE`id_nguoi_nhan`=#{idNguoiNhan} AND`status`=0";
    @Select(GET_ALL_THONG_BAO_CHUA_DOC)
    public List<Map<String, Object>> getAllThongBaoChuaDocs(int idNguoiNhan);

    // Đánh dấu tất cả thông báo là đã đọc
    final String DANH_DAU_ALL_DA_DOC = "UPDATE thong_bao SET status = 1 WHERE id_nguoi_nhan = #{idNguoiNhan} AND status = 0;";
    @Update(DANH_DAU_ALL_DA_DOC)
    public int danhDauAllDaDoc(int idNguoiNhan);
   
}
