package mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;
import java.util.Map;

import com.thuctap.struts2_crud_mybatis.model.*;

public interface KhoaStudentMapper {

     //final String GET_ALL_STUDENT = "select s.name, k.TenKhoa from student s inner join khoa k on s.idKHOA = k.id;";
    final String GET_ALL_STUDENT = "SELECT * FROM details.user_admin where username = #{a} and email = #{b};";

    @Select(GET_ALL_STUDENT)
	public List<Map<String,Object>> getAll(@Param("a") String a,@Param("b") String b);
     
}
