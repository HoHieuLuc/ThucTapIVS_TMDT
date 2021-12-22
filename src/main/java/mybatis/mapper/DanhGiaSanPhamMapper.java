package mybatis.mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;
import java.util.Map;

import com.tmdt.model.DanhGiaSanPham;


public interface DanhGiaSanPhamMapper {

    	// search student
	final String GET_ALL_DANH_GIA_SAN_PHAM = "select kh.ten,dgsp.so_sao,dgsp.noi_dung,dgsp.ngay_tao,dgsp.ngay_sua, dgsp.ma_san_pham from khach_hang kh inner join danh_gia_san_pham dgsp on kh.ma_khach_hang = dgsp.ma_khach_hang where dgsp.ma_san_pham = #{maSanPham};";
	@Select(GET_ALL_DANH_GIA_SAN_PHAM)
	public List<Map<String,Object>> getAll(String maSanPham);

	final String INSERT_INTO_DANH_GIA_SAN_PHAM = "INSERT INTO danh_gia_san_pham (`ma_khach_hang`, `so_sao`, `noi_dung`, `ma_san_pham`, `ngay_tao`, `ngay_sua`) VALUES (#{maKhachHang}, #{soSao}, #{noiDung}, #{maSanPham}, #{ngayTao}, #{ngaySua});";
	@Insert(INSERT_INTO_DANH_GIA_SAN_PHAM)
	public void insert(DanhGiaSanPham dgsp);
}
