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
import com.tmdt.khachhang.model.*;

import java.util.List;

public interface KhachHangMapper {
    final String INSERT_KHACH_HANG = "INSERT INTO `khach_hang` (`ten`, `dia_chi`, `id_tai_khoan`, `tien_no`, `gioi_thieu`) VALUES (#{ten},#{dia_chi},#{id_tai_khoan},#{tien_no},#{gioi_thieu})";
    @Insert(INSERT_KHACH_HANG)
    @Options(useGeneratedKeys = true, keyProperty = "ma_khach_hang")
    public void insert(KhachHang khachHang);
}
