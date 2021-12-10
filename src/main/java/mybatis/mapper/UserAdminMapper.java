package mybatis.mapper;

import java.util.List;

import com.thuctap.struts2_crud_mybatis.model.UserAdmin;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserAdminMapper {
	// get all user_admin
	final String GET_ALL_USER_ADMIN = "SELECT * FROM USER_ADMIN";

	@Select(GET_ALL_USER_ADMIN)
	public List<UserAdmin> getAll();

	// get student by user_admin
	final String GET_USER_ADMIN_BY_ID = "SELECT * FROM USER_ADMIN WHERE ID = #{id}";

	@Select(GET_USER_ADMIN_BY_ID)
	public UserAdmin getById(int id);

	// inert user_admin
	final String INSERT_USER_ADMIN = "INSERT INTO USER_ADMIN (USERNAME, PASSWORD, EMAIL,DATE_CREATED,DATE_EXPIRED) "
			+ "VALUES (#{username}, #{password}, #{email},#{date_created},#{date_expired})";

	@Insert(INSERT_USER_ADMIN)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(UserAdmin userAdmin);

	// update user_admin
	// final String UPDATE_STUDENT = "UPDATE STUDENT SET EMAIL = #{email}, NAME =
	// #{name}, "
	// + "BRANCH = #{branch}, PERCENTAGE = #{percentage}, PHONE = #{phone} WHERE ID
	// = #{id}";

	// @Update(UPDATE_STUDENT)
	// public void update(UserAdmin student);

	// delete student by id
	final String DELETE_USER_ADMIN_BY_ID = "DELETE from USER_ADMIN WHERE ID = #{id}";

	@Delete(DELETE_USER_ADMIN_BY_ID)
	public void delete(int id);

	// search student
	// final String SEARCH_STUDENT = "SELECT * FROM STUDENT WHERE NAME LIKE
	// CONCAT('%', #{search}, '%') OR EMAIL LIKE CONCAT('%', #{search}, '%')";

	// @Select(SEARCH_STUDENT)
	// public List<UserAdmin> search(String search);

	// đếm số lượng với điều kiện để phân trang
	// final String COUNT_STUDENT = "SELECT COUNT(*) FROM STUDENT WHERE NAME LIKE
	// CONCAT('%', #{search}, '%') OR EMAIL LIKE CONCAT('%', #{search}, '%')";
	// @Select(COUNT_STUDENT)
	// public int count(String search);

	// get student by page
	// final String GET_STUDENT_BY_PAGE = "SELECT * FROM STUDENT WHERE NAME LIKE
	// CONCAT('%', #{search}, '%') OR EMAIL LIKE CONCAT('%', #{search}, '%') LIMIT
	// #{offset}, #{rowsPerPage}";
	// @Select(GET_STUDENT_BY_PAGE)
	// public List<UserAdmin> getByPage(@Param("search") String search,
	// @Param("offset") int offset, @Param("rowsPerPage") int rowsPerPage);
}
