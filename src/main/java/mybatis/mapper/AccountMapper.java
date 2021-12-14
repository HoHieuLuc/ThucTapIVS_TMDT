package mybatis.mapper;

import java.util.List;

import com.thuctap.struts2_crud_mybatis.model.Account;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface AccountMapper {
	// get all user_admin
	final String GET_ALL_ACCOUNTS = "SELECT * FROM USER_ADMIN";
	@Select(GET_ALL_ACCOUNTS)
	@Results(value = { @Result(property = "id", column = "ID"),
			@Result(property = "username", column = "NAME"),
			@Result(property = "password", column = "password"),
			@Result(property = "email", column = "email"),
			@Result(property = "dateCreated", column = "date_created"),
			@Result(property = "dateExpired", column = "date_expired") })
	public List<Account> getAll();

	// get user by id
	final String GET_ACCOUNT_BY_ID = "SELECT * FROM USER_ADMIN WHERE ID = #{id}";

	@Select(GET_ACCOUNT_BY_ID)
	public Account getById(int id);

	// insert
	final String INSERT_ACCOUNT = "INSERT INTO USER_ADMIN (USERNAME, PASSWORD, EMAIL, DATE_CREATED, DATE_EXPIRED) "
			+ "VALUES (#{username}, #{password}, #{email}, #{dateCreated}, #{dateExpired})";

	@Insert(INSERT_ACCOUNT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(Account account);

	// update
	// final String UPDATE_STUDENT = "UPDATE STUDENT SET EMAIL = #{email}, NAME =
	// #{name}, "
	// + "BRANCH = #{branch}, PERCENTAGE = #{percentage}, PHONE = #{phone} WHERE ID
	// = #{id}";

	// @Update(UPDATE_STUDENT)
	// public void update(UserAdmin student);

	// delete student by id
	final String DELETE_ACCOUNT = "DELETE from USER_ADMIN WHERE ID = #{id}";

	@Delete(DELETE_ACCOUNT)
	public void delete(int id);

	final String GET_ACCOUNT_BY_USERNAME = "SELECT * FROM USER_ADMIN WHERE USERNAME = #{username} LIMIT 1";

	@Select(GET_ACCOUNT_BY_USERNAME)
	public Account getByUsername(String username);
}
