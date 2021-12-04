package com.thuctap.struts2_crud_mybatis.action;

import java.io.Reader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.thuctap.struts2_crud_mybatis.model.Student;

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

    @Action(value = "/student/index", results = { @Result(location = "/index.html") })
    public String listStudent() {
        return SUCCESS;
    }

    @Action(value = "/api/v1/student/list", results = { @Result(location = "/index.html") })
    public String viewStudents() throws IOException {

        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // Mở Session
        SqlSession session = sqlSessionFactory.openSession();

        // Tạo instance cho Interface StudentMapper (Chính là file lưu trữ các code truy
        // vấn sql bằng mybatis annotation)
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        // Lấy dữ liệu toàn bộ học sinh
        listStudents = studentMapper.getAll();

        // chuyển danh sách học sinh sang json
        Gson gson = new Gson();
        String json = gson.toJson(listStudents);

        // trả về kết quả là json
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = response.getWriter();
        pw.print(json);
        pw.flush();
        pw.close();

        // System.out.println(json);

        return SUCCESS;
    }

    /*  */
    // region getters setters
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

    // endregion

    // validate đơn giản
    public boolean isValid() {
        return name != null && name.length() > 0 && branch != null && branch.length() > 0
                && percentage > 0 && phone > 0 && email != null && email.length() > 0;
    }

    @Action(value = "/student/create", results = { @Result(location = "/create.html") })
    public String viewCreateStudent() {
        return SUCCESS;
    }

    @Action(value = "/api/v1/student/create", results = { @Result(location = "/index.html") })
    public String createStudent() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = response.getWriter();
        if (!isValid()) {
            response.setStatus(400);
            pw.print("{\"message\":\"Vui lòng nhập đầy đủ thông tin\"}");
            pw.flush();
            pw.close();
            return SUCCESS;
        }

        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        Gson gson = new Gson();

        // create student mapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        // insert student
        Student student = new Student(name, branch, percentage, phone, email);
        // Student student = gson.fromJson(jsonStudent, Student.class);
        // System.out.println(student.toString());
        String json = gson.toJson(student);

        pw.print(json);
        pw.flush();
        pw.close();

        // System.out.println(json);
        // System.out.println(student.toString());
        studentMapper.insert(student);
        session.commit();
        System.out.println("insert sucessfully");

        // close session
        session.close();

        return SUCCESS;
    }

    //

    // mai làm tiếp đoạn update
    // @Update("/api/v1/student/update/{id}")
    @Action(value = "/api/v1/student/update", results = { @Result(location = "/index.html") })
    public String updateStudent() throws IOException {
        Gson gson = new Gson();
        // System.out.println(getJsonStudent());
        // create student mapper

        // insert student
        Student student = new Student(getName(), getBranch(), getPercentage(), getPhone(), getEmail());
        // Student student = gson.fromJson(jsonStudent, Student.class);
        String json = gson.toJson(student);

        /*
         * HttpServletRequest request = ServletActionContext.getRequest();
         * System.out.println(request.getMethod());
         */
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = response.getWriter();
        pw.print(json);
        pw.flush();
        pw.close();

        System.out.println(json);
        System.out.println(student.toString());
        // System.out.println(student.toString());
        System.out.println("insert sucessfully");

        // TODO Auto-generated method stub
        return SUCCESS;
    }

    // đổi nhánh xong xài delete bị lỗi luôn
    // @Delete("/api/v1/student/delete/{id}")
    @Action(value = "/api/v1/student/delete/*", params = { "id", "{1}" }, results = {
            @Result(location = "/index.html") })
    public String deleteStudent() throws IOException {
        System.out.println(getId());
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        studentMapper.delete(getId());
        session.commit();
        session.close();
        return SUCCESS;
    }
}
