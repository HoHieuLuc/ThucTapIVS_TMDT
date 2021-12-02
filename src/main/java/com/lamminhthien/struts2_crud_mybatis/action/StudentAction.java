package com.lamminhthien.struts2_crud_mybatis.action;

import java.io.Reader;
import java.util.List;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.struts2.convention.annotation.*;

import com.lamminhthien.struts2_crud_mybatis.model.Student;
import com.opensymphony.xwork2.ActionSupport;

import mybatis.mapper.StudentMapper;

public class StudentAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private List<Student> listStudents;

    public List<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }

    @Override
    @Action(value = "/index", results = { @Result(location = "/index.jsp") })
    public String execute() throws IOException {

        // Đọc file xml để khởi tạo kết nối đến mysql server và cấu hình interface
        // studentmapper
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // Mở Session
        SqlSession session = sqlSessionFactory.openSession();

        // Tạo instance cho Interface StudentMapper (Chính là file lưu trữ các code truy
        // vấn sql bằng mybatis annotation)
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        // Lấy dữ liệu toàn bộ học sinh
        listStudents = studentMapper.getAll();

        // Gửi kết quả truy vấn đến view
        setListStudents(listStudents);

        // ngắt kết nối
        session.close();

        return SUCCESS;
    }

    /*  */

    private int id;
    private String name;
    private String branch;
    private int percentage;
    private int phone;
    private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Action(value = "/CreateStudent", results = { @Result(location = "/index.html") })
	public String createStudent() throws Exception{
        System.out.println("it runs");
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        // create student mapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        // insert student
        Student student = new Student(getName(), getBranch(), getPercentage(), getPhone(), getEmail());
        System.out.println(student.toString());
        studentMapper.insert(student);
        session.commit();
        System.out.println("insert sucessfully");

        // close session
        session.close();
		
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	
	
}
