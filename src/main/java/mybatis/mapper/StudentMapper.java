package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

@Mapper
public interface StudentMapper {

	// get all student
	final String GET_ALL_STUDENT = "SELECT * FROM STUDENT";

	@Select(GET_ALL_STUDENT)
	@Results(value = { @Result(property = "id", column = "ID"),
			@Result(property = "name", column = "NAME"),
			@Result(property = "branch", column = "BRANCH"),
			@Result(property = "percentage", column = "PERCENTAGE"),
			@Result(property = "phone", column = "PHONE"),
			@Result(property = "email", column = "EMAIL") })
	public List<Object> getAll();

	// get student by id
	final String GET_STUDENT_BY_ID = "SELECT * FROM STUDENT WHERE ID = #{id}";

	@Select(GET_STUDENT_BY_ID)
	public Object getById(int id);

	// inert student
	final String INSERT_STUDENT = "INSERT INTO STUDENT (NAME, BRANCH, PERCENTAGE, PHONE, EMAIL ) "
			+ "VALUES (#{name}, #{branch}, #{percentage}, #{phone}, #{email})";

	@Insert(INSERT_STUDENT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(Object student);

	// update student
	final String UPDATE_STUDENT = "UPDATE STUDENT SET EMAIL = #{email}, NAME = #{name}, "
			+ "BRANCH = #{branch}, PERCENTAGE = #{percentage}, PHONE = #{phone} WHERE ID = #{id}";

	@Update(UPDATE_STUDENT)
	public void update(Object student);

	// delete student by id
	final String DELETE_STUDENT_BY_ID = "DELETE from STUDENT WHERE ID = #{id}";

	@Delete(DELETE_STUDENT_BY_ID)
	public void delete(int id);

	// search student
	final String SEARCH_STUDENT = "SELECT * FROM STUDENT WHERE NAME LIKE CONCAT('%', #{search}, '%') OR EMAIL LIKE CONCAT('%', #{search}, '%')";

	@Select(SEARCH_STUDENT)
	public List<Object> search(String search);

	// đếm số lượng với điều kiện để phân trang
	final String COUNT_STUDENT = "SELECT COUNT(*) FROM STUDENT WHERE NAME LIKE CONCAT('%', #{search}, '%') OR EMAIL LIKE CONCAT('%', #{search}, '%')";
	@Select(COUNT_STUDENT)
	public int count(String search);

	// get student by page
	final String GET_STUDENT_BY_PAGE = "SELECT * FROM STUDENT WHERE NAME LIKE CONCAT('%', #{search}, '%') OR EMAIL LIKE CONCAT('%', #{search}, '%') LIMIT #{offset}, #{rowsPerPage}";
	@Select(GET_STUDENT_BY_PAGE)
	public List<Object> getByPage(@Param("search") String search, @Param("offset") int offset, @Param("rowsPerPage") int rowsPerPage);
}
