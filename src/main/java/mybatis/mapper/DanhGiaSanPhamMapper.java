package mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

import com.tmdt.model.DanhGiaSanPham;

public interface DanhGiaSanPhamMapper {
	// Lấy tất cả đánh giá của sản phẩm, khi khách hàng chưa đăng nhập
	final String GET_ALL_DANH_GIA_SAN_PHAM = "SELECT kh.ten, dgsp.so_sao, dgsp.noi_dung, dgsp.ngay_tao, dgsp.ngay_sua, dgsp.ma_san_pham "
			+
			"FROM khach_hang kh INNER JOIN danh_gia_san_pham dgsp ON kh.ma_khach_hang = dgsp.ma_khach_hang " +
			"WHERE dgsp.ma_san_pham = #{maSanPham} ORDER BY dgsp.ngay_tao DESC";
	@Select(GET_ALL_DANH_GIA_SAN_PHAM)
	public List<Map<String, Object>> getAll(String maSanPham);

	// Lấy tất cả đánh giá sản phẩm ngoại trừ đánh giá của người dùng
	final String GET_ALL_DGSP_EXCEPT_OWN = "SELECT kh.ten, dgsp.so_sao, dgsp.noi_dung, dgsp.ngay_tao, dgsp.ngay_sua, dgsp.ma_san_pham "
			+
			"FROM khach_hang kh INNER JOIN danh_gia_san_pham dgsp ON kh.ma_khach_hang = dgsp.ma_khach_hang " +
			"WHERE dgsp.ma_san_pham = #{maSanPham} AND dgsp.ma_khach_hang != #{maKhachHang} " +
			"ORDER BY dgsp.ngay_tao DESC";
	@Select(GET_ALL_DGSP_EXCEPT_OWN)
	public List<Map<String, Object>> getAllExceptOwn(@Param("maSanPham") String maSanPham,
			@Param("maKhachHang") int maKhachHang);

	// Lấy lại đánh giá của khách hàng cho sản phẩm tương ứng
	final String GET_BY_MAKH_AND_MASP = " SELECT * from danh_gia_san_pham dgsp LEFT JOIN khach_hang kh ON kh.ma_khach_hang = dgsp.ma_khach_hang "
			+
			"WHERE dgsp.ma_san_pham = #{maSanPham} " +	
			"AND dgsp.ma_khach_hang = #{maKhachHang}";

	@Select(GET_BY_MAKH_AND_MASP)
	public Map<String, Object> getByMaKHandMaSP(@Param("maKhachHang") int maKhachHang,
			@Param("maSanPham") String maSanPham);

	// Thêm đánh giá sản phẩm
	final String THEM_DANH_GIA_SAN_PHAM = "INSERT INTO danh_gia_san_pham (`ma_khach_hang`, `so_sao`, `noi_dung`, `ma_san_pham`, `ngay_tao`, `ngay_sua`)"
			+ "VALUES (#{maKhachHang}, #{soSao}, #{noiDung}, #{maSanPham}, now(), now());";

	@Insert(THEM_DANH_GIA_SAN_PHAM)
	@Options(useGeneratedKeys = true, keyProperty = "maDanhGia", keyColumn = "ma_danh_gia")
	public void themDGSP(DanhGiaSanPham dgsp);

	// Cập nhật đánh giá sản phẩm
	final String UPDATE_DANH_GIA_SP = "UPDATE `danh_gia_san_pham` SET `so_sao` = #{soSao}, `noi_dung` = #{noiDung}, `ngay_sua` = now() "
			+
			"WHERE `ma_khach_hang` = #{maKhachHang} "
			+
			"AND `ma_san_pham` = #{maSanPham};";
	@Update(UPDATE_DANH_GIA_SP)
	public void updateDanhGiaSp(@Param("maSanPham") String maSanPham, @Param("maKhachHang") int maKhachHang,
			@Param("noiDung") String noiDung, @Param("soSao") int soSao);

}
