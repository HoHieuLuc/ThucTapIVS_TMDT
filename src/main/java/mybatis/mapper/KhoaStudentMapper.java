package mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

import com.thuctap.struts2_crud_mybatis.model.*;

public interface KhoaStudentMapper {

     final String GET_ALL_STUDENT = "select s.name, k.TenKhoa from student s inner join khoa k on s.idKHOA = k.id;";

    @Select(GET_ALL_STUDENT)
	@Results(value = { @Result(property = "idStudent", column = "ID"),
			@Result(property = "name", column = "NAME"),
			@Result(property = "TenKhoa", column = "TenKhoa"),
            @Result(property = "idKhoa", column = "idKhoa")
        })
	public List<KhoaStudent> getAll();
     
}
