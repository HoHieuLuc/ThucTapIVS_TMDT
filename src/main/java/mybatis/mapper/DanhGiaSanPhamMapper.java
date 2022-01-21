package mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

import com.tmdt.model.DanhGiaSanPham;

public interface DanhGiaSanPhamMapper {
	// đếm tất cả đánh giá để phân trang
	final String COUNT_ALL_DANH_GIA_SAN_PHAM = "SELECT COUNT(*) from danh_gia_san_pham " +
			"WHERE ma_san_pham = #{maSanPham}";

	@Select(COUNT_ALL_DANH_GIA_SAN_PHAM)
	int countAllDanhGiaSanPham(@Param("maSanPham") String maSanPham);

	// Lấy tất cả đánh giá của sản phẩm, khi khách hàng chưa đăng nhập
	final String GET_ALL_DANH_GIA_SAN_PHAM = "SELECT COUNT(phdgsp.ma_phan_hoi) as so_phan_hoi, kh.ten, " +
			"dgsp.so_sao,dgsp.ma_danh_gia, dgsp.noi_dung, " +
			"DATE_FORMAT(dgsp.ngay_tao, '%d-%m-%Y lúc %T') AS ngay_tao, " +
			"DATE_FORMAT(dgsp.ngay_sua, '%d-%m-%Y lúc %T') AS ngay_sua, " +
			"dgsp.ma_san_pham, tk.username, tk.avatar " +
			"FROM khach_hang kh LEFT JOIN danh_gia_san_pham dgsp ON kh.ma_khach_hang = dgsp.ma_khach_hang " +
			"JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
			"LEFT JOIN phan_hoi_danh_gia_sp phdgsp ON phdgsp.ma_danh_gia = dgsp.ma_danh_gia " +
			"WHERE dgsp.ma_san_pham = #{maSanPham} " +
			"GROUP BY dgsp.ma_danh_gia " +
			"ORDER BY FIELD(tk.username, #{username}) DESC, ${orderBy} ${order} " +
			"LIMIT #{offset}, #{rowsPerPage};";

	@Select(GET_ALL_DANH_GIA_SAN_PHAM)
	public List<Map<String, Object>> getAllDanhGiaSanPham(
			@Param("maSanPham") String maSanPham,
			@Param("offset") int offset,
			@Param("rowsPerPage") int rowsPerPage,
			@Param("username") String username,
			@Param("orderBy") String orderBy,
			@Param("order") String order);

	// Lấy đánh giá của khách hàng cho sản phẩm tương ứng
	final String GET_DANH_GIA_BY_MAKH_AND_MASP = "SELECT COUNT(*) FROM danh_gia_san_pham " +
			"WHERE ma_san_pham = #{maSanPham} " +
			"AND ma_khach_hang = #{maKhachHang}";

	@Select(GET_DANH_GIA_BY_MAKH_AND_MASP)
	public int getDanhGiaSanPhamByMaKHandMaSP(@Param("maKhachHang") int maKhachHang,
			@Param("maSanPham") String maSanPham);

	// lấy tất cả đánh giá sản phẩm và đưa đánh giá của người dùng lên đầu
	final String GET_ALL_DGSP_ORDER_BY_CURRENT_USER = "SELECT COUNT(phdgsp.ma_phan_hoi) as so_phan_hoi, " +
			"kh.ten, kh.ma_khach_hang, " +
			"dgsp.so_sao, dgsp.ma_danh_gia, dgsp.noi_dung, " +
			"DATE_FORMAT(dgsp.ngay_tao, '%d-%m-%Y lúc %T') AS ngay_tao, " +
			"DATE_FORMAT(dgsp.ngay_sua, '%d-%m-%Y lúc %T') AS ngay_sua, " +
			"dgsp.ma_san_pham, tk.username, tk.avatar " +
			"FROM khach_hang kh LEFT JOIN danh_gia_san_pham dgsp ON kh.ma_khach_hang = dgsp.ma_khach_hang " +
			"JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan " +
			"LEFT JOIN phan_hoi_danh_gia_sp phdgsp ON phdgsp.ma_danh_gia = dgsp.ma_danh_gia " +
			"WHERE dgsp.ma_san_pham = #{maSanPham} " +
			"GROUP BY dgsp.ma_danh_gia " +
			"ORDER BY FIELD(kh.ma_khach_hang, #{maKhachHang}) DESC, dgsp.ngay_tao DESC " +
			"LIMIT #{limit}";

	@Select(GET_ALL_DGSP_ORDER_BY_CURRENT_USER)
	public List<Map<String, Object>> getAllDanhGiaSanPhamOrderByCurrentUser(
			@Param("maSanPham") String maSanPham,
			@Param("maKhachHang") int maKhachHang,
			@Param("limit") int limit);

	// Tìm xem đánh giá có phải là đánh giá của khách hàng hay không
	// từ đó suy ra được action có thể có
	final String LA_DANH_GIA_CUA_KHACH_HANG = "SELECT COUNT(*) FROM danh_gia_san_pham " +
			"WHERE ma_danh_gia = #{maDanhGia} " +
			"AND ma_khach_hang = #{maKhachHang}";

	@Select(LA_DANH_GIA_CUA_KHACH_HANG)
	public int laDanhGiaCuaKhachHang(@Param("maDanhGia") int maDanhGia, @Param("maKhachHang") int maKhachHang);

	// Thêm đánh giá sản phẩm
	final String THEM_DANH_GIA_SAN_PHAM = "INSERT INTO danh_gia_san_pham (ma_khach_hang, so_sao, noi_dung, ma_san_pham, ngay_tao, ngay_sua)"
			+ "VALUES (#{maKhachHang}, #{soSao}, #{noiDung}, #{maSanPham}, now(), NULL);";

	@Insert(THEM_DANH_GIA_SAN_PHAM)
	@Options(useGeneratedKeys = true, keyProperty = "maDanhGia", keyColumn = "ma_danh_gia")
	public void themDGSP(DanhGiaSanPham dgsp);

	// Cập nhật đánh giá sản phẩm
	final String UPDATE_DANH_GIA_SP = "UPDATE danh_gia_san_pham SET so_sao = #{soSao}, " +
			"noi_dung = #{noiDung}, ngay_sua = now() " +
			"WHERE ma_khach_hang = #{maKhachHang} " +
			"AND ma_san_pham = #{maSanPham};";

	@Update(UPDATE_DANH_GIA_SP)
	public void updateDanhGiaSp(@Param("maSanPham") String maSanPham, @Param("maKhachHang") int maKhachHang,
			@Param("noiDung") String noiDung, @Param("soSao") int soSao);

	// Lẫy mã khách hàng của sản phẩm mình đang xem
	final String GET_MAKH_FROM_MASP = "SELECT ma_khach_hang from san_pham WHERE ma_san_pham = #{maSanPham}";

	@Select(GET_MAKH_FROM_MASP)
	public int getMaKHFromMaSP(String maSanPham);

	// xóa 1 đánh giá sản phẩm
	final String DELETE_DGSP = "DELETE FROM danh_gia_san_pham " +
			"WHERE ma_danh_gia = #{maDanhGia} AND ma_khach_hang = #{maKhachHang}";

	@Delete(DELETE_DGSP)
	public int xoaDanhGiaSanPham(@Param("maDanhGia") int maDanhGia, @Param("maKhachHang") int maKhachHang);
}
