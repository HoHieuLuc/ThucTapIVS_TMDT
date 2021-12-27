package mybatis.mapper;

import java.util.List;

import com.tmdt.model.AnhSanPham;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface AnhSanPhamMapper {
    
    // thêm ảnh cho sản phẩm
    final String INSERT_ANH_SAN_PHAM = "INSERT INTO anh_san_pham (ma_san_pham, anh) VALUES (#{maSanPham}, #{anh})";
	@Insert(INSERT_ANH_SAN_PHAM)
    @Options(useGeneratedKeys = true, keyProperty = "maAnh")
    public void insertAnhSanPham(AnhSanPham anhSanPham);

    // lấy ảnh cho 1 sản phẩm
    final String SELECT_ANH_SAN_PHAM = "SELECT anh FROM anh_san_pham WHERE ma_san_pham = #{maSanPham}";
    @Select(SELECT_ANH_SAN_PHAM)
    public List<String> getAnhSanPham(String maSanPham);
}
