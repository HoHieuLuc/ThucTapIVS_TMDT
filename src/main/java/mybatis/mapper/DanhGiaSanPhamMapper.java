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

	    // Thêm tài khoản mới cho khách hàng
		// final String INSERT_TAI_KHOAN ="INSERT INTO `tai_khoan` (`username`, `password`, `email`, `ngay_tao`, `so_dien_thoai`, `ngay_sinh`, `gioi_tinh`, `so_lan_canh_cao`, `status`, `ma_quyen`,`avatar`) VALUES (#{userName}, #{password}, #{email}, #{ngayTao}, #{soDienThoai}, #{ngaySinh}, #{gioiTinh},#{soLanCanhCao},#{status},#{maQuyen},#{avatar});";
		// @Insert(INSERT_TAI_KHOAN)
		// @Options(useGeneratedKeys = true,keyProperty ="id")
		// public void insert(TaiKhoan taiKhoan);


		//Get all danh_gia_san_pham
	final String GET_ALL_DANH_GIA_SAN_PHAM = "select kh.ten,dgsp.so_sao,dgsp.noi_dung,dgsp.ngay_tao,dgsp.ngay_sua, dgsp.ma_san_pham from khach_hang kh inner join danh_gia_san_pham dgsp on kh.ma_khach_hang = dgsp.ma_khach_hang where dgsp.ma_san_pham = #{maSanPham};";
	@Select(GET_ALL_DANH_GIA_SAN_PHAM)
	public List<Map<String,Object>> getAll(String maSanPham);

	//Thêm đánh giá sản phẩm 
	final String THEM_DANH_GIA_SAN_PHAM = "INSERT INTO danh_gia_san_pham (`ma_khach_hang`, `so_sao`, `noi_dung`, `ma_san_pham`, `ngay_tao`, `ngay_sua`)" 
	+ "VALUES (#{maKhachHang}, #{soSao}, #{noiDung}, #{maSanPham}, #{ngayTao}, #{ngaySua});";
    @Insert(THEM_DANH_GIA_SAN_PHAM)
    @Options(useGeneratedKeys = true, keyProperty = "maDanhGia",keyColumn = "ma_danh_gia")
    public void themDGSP(DanhGiaSanPham dgsp);
}
