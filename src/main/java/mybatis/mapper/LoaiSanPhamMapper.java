package mybatis.mapper;

import java.util.List;

import com.tmdt.model.LoaiSanPham;

import org.apache.ibatis.annotations.*;

public interface LoaiSanPhamMapper {
    
    final String GET_ALL_LOAI_SAN_PHAM = "SELECT * FROM loai_san_pham";
    @Select(GET_ALL_LOAI_SAN_PHAM)
    @Results({
        @Result(property = "maLoaiSanPham", column = "ma_loai_sp"),
        @Result(property = "tenLoaiSanPham", column = "ten_loai_sp")
    })
    public List<LoaiSanPham> getAllLoaiSanPham();
}
