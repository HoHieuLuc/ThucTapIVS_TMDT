package com.thuctap.struts2_crud_mybatis.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.errors.CustomError;
import com.thuctap.struts2_crud_mybatis.model.Student;

import mybatis.mapper.StudentMapper;

// tất cả các lỗi về input (nhập số nhưng lại nhập chữ,...) sẽ bị trả về lỗi 400 bad request
@Result(name = "input", location = "/index", type = "redirectAction", params = {
    "namespace", "/",
    "actionName", "bad-request"
})
@Namespace("/api/v1/student")
public class StudentAction extends ActionSupport {
    
    // Khởi tạo HttpSession
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private static final long serialVersionUID = 1L;
    private List<Student> listStudents;
    private String search;
    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    public List<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Actions({
            @Action(value = "/student/index", results = { @Result(location = "/index.html") }),
            @Action(value = "/student/create", results = { @Result(location = "/create.html") }),
            @Action(value = "/student/edit/*", results = { @Result(location = "/edit.html") }),
    })
    public String viewStudent() {
        return SUCCESS;
    }

    /* api */
    @Action(value = "list", results = { @Result(location = "/index.html") })
    public String getAllStudents() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        // Mở Session
        SqlSession sessionSQL = sqlSessionFactory.openSession();

        // Tạo instance cho Interface StudentMapper (Chính là file lưu trữ các code truy
        // vấn sql bằng mybatis annotation)
        StudentMapper studentMapper = sessionSQL.getMapper(StudentMapper.class);

        // Lấy dữ liệu sinh viên
        // System.out.println(search);
        if (session.getAttribute("userName").equals("admin"))
        {
            listStudents = studentMapper.search(search);
            // chuyển danh sách học sinh sang json
            Gson gson = new Gson();
            String json = gson.toJson(listStudents);

            // trả về kết quả là json
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(json);
            printWriter.flush();
            printWriter.close();

            // System.out.println(json);

           
        }
        
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

    @Action(value = "create", results = {
            @Result(name = "success", location = "/index.html"),
    })
    public String createStudent() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(201);
        PrintWriter printWriter = response.getWriter();
        if (!isValid()) {
            return CustomError.createCustomError("Vui lòng nhập đầy đủ thông tin", 400, response, printWriter);
        }

        SqlSession session = sqlSessionFactory.openSession();

        // create student mapper
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        // insert student
        Student student = new Student(name, branch, percentage, phone, email);
        Gson gson = new Gson();
        String json = gson.toJson(student);

        printWriter.print(json);
        printWriter.flush();
        printWriter.close();

        studentMapper.insert(student);
        session.commit();
        session.close();

        return SUCCESS;
    }

    // get student by id

    @Action(value = "*", params = { "id", "{1}" }, results = {
            @Result(location = "/index.html"),
    })
    public String getStudent() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter printWriter = response.getWriter();

        SqlSession session = sqlSessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        Student student = studentMapper.getById(id);
        if (student == null) {
            return CustomError.createCustomError("Không tồn tại sinh viên có ID " + id, 404, response, printWriter);
        }

        Gson gson = new Gson();
        String json = gson.toJson(student);

        printWriter.print(json);
        printWriter.flush();
        printWriter.close();

        return SUCCESS;
    }

    // edit student

    @Action(value = "/api/v1/student/edit/*", params = { "id", "{1}" }, results = {
            @Result(name = "success", location = "/index.html"),
    })
    public String editStudent() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter printWriter = response.getWriter();

        SqlSession session = sqlSessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        Student student = studentMapper.getById(id);
        if (student == null) {
            return CustomError.createCustomError("Không tồn tại sinh viên có ID " + id, 404, response, printWriter);
        }
        if (!isValid()) {
            return CustomError.createCustomError("Vui lòng nhập đầy đủ thông tin", 400, response, printWriter);
        }
        student.setName(name);
        student.setBranch(branch);
        student.setPercentage(percentage);
        student.setPhone(phone);
        student.setEmail(email);
        studentMapper.update(student);

        Gson gson = new Gson();
        String json = gson.toJson(student);

        printWriter.print(json);
        printWriter.flush();
        printWriter.close();

        session.commit();
        session.close();

        return SUCCESS;
    }

    // delete student

    @Action(value = "/api/v1/student/delete/*", params = { "id", "{1}" }, results = {
            @Result(location = "/index.html") })
    public String deleteStudent() {
        SqlSession session = sqlSessionFactory.openSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        studentMapper.delete(getId());
        session.commit();
        session.close();
        return SUCCESS;
    }
}
