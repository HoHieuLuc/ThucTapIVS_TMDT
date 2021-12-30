package mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface DanhMucSanPhamMapper {
    // lấy tất cả sản phẩm và 1 ảnh đầu tiên để làm ảnh đại diện cho từng sản phẩm
    final String GET_ALL_LOAI_SP = "SELECT ten_loai_sp from loai_san_pham;";
    @Select(GET_ALL_LOAI_SP)
    @Results(value = {
            @Result(property = "tenLoaiSP", column = "ten_loai_sp"),
    })
    public List<Map<String, Object>> getAllLoaiSP();
}
