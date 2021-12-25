package mybatis.mapper;

import com.tmdt.model.AnhSanPham;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface AnhSanPhamMapper {
    
    // thêm ảnh cho sản phẩm
    final String INSERT_ANH_SAN_PHAM = "INSERT INTO anh_san_pham (ma_san_pham, anh) VALUES (#{maSanPham}, #{anh})";
	@Insert(INSERT_ANH_SAN_PHAM)
    @Options(useGeneratedKeys = true, keyProperty = "maAnh")
    public void insertAnhSanPham(AnhSanPham anhSanPham);
}
