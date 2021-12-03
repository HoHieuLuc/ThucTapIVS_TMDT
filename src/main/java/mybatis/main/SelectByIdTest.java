package mybatis.main;

import java.io.IOException;
import java.io.Reader;

import com.thuctap.struts2_crud_mybatis.model.Student;

import mybatis.mapper.StudentMapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SelectByIdTest {
    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        // create student mapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        // get student by Id
        Student student = studentMapper.getById(12);
        System.out.println(student);

        // close session
        session.close();
    }
}
