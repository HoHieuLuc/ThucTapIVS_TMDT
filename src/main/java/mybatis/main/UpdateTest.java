package mybatis.main;

import java.io.IOException;
import java.io.Reader;

import com.lamminhthien.struts2_crud_mybatis.model.Student;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.StudentMapper;

public class UpdateTest {
    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        // create student mapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        // update student
        Student student = studentMapper.getById(12);
        student.setName("Jim Smith");
        student.setPhone(12399888);
        studentMapper.update(student);
        session.commit();
        System.out.println("update sucessfully");

        // close session
        session.close();
    }
}
