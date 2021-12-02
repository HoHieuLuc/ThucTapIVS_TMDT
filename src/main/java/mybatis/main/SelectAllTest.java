package mybatis.main;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.lamminhthien.struts2_crud_mybatis.model.Student;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.StudentMapper;

public class SelectAllTest {
    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        // create student mapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        // show list student
        List<Student> listStudents = studentMapper.getAll();
        for (Student student : listStudents) {
            System.out.println(student.toString());
        }
        
        // close session
        session.close();
    }
}
