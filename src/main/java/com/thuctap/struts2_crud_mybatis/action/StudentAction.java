package com.thuctap.struts2_crud_mybatis.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import com.opensymphony.xwork2.ActionSupport;
import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.errors.CustomError;
import com.thuctap.struts2_crud_mybatis.model.Student;
import com.thuctap.struts2_crud_mybatis.utilities.JsonResponse;

import mybatis.mapper.StudentMapper;

// để truy cập đc vào student thì người dùng phải đăng nhập
@InterceptorRef(value = "loginStack")
// tất cả các lỗi về input (nhập số nhưng lại nhập chữ,...) sẽ bị trả về lỗi 400 bad request
@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
@Namespace("/api/v1/student")
public class StudentAction extends ActionSupport {
    HttpServletResponse response = ServletActionContext.getResponse();
    // Khởi tạo HttpSession
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private static final long serialVersionUID = 1L;
    private String search;
    private int page;
    private int rowsPerPage = 5;
    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // region Getter and Setter
    public String getSearch() {
        return search == null ? "" : search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getPage() {
        return page > 0 ? page : 1;
    }

    public void setPage(int page) {
        this.page = page;
    }
    // endregion

    @Actions({
            @Action(value = "/admin/student/index", results = { @Result(location = "/WEB-INF/jsp/admin/student/index.jsp") }),
            @Action(value = "/admin/student/create", results = { @Result(location = "/WEB-INF/jsp/admin/student/create.jsp") }),
            @Action(value = "/admin/student/edit/*", results = { @Result(location = "/WEB-INF/jsp/admin/student/edit.jsp") }),
    })
    public String viewStudent() {
        return SUCCESS;
    }

    /* api */
    @Action(value = "list", results = { @Result(location = "/index.html") })
    public String getAllStudents() throws IOException {
        // Mở Session
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // Tạo instance cho Interface StudentMapper (Chính là file lưu trữ các code truy
        // vấn sql bằng mybatis annotation)
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        // Lấy dữ liệu sinh viên
        // System.out.println(search);
        int offset = (getPage() - 1) * rowsPerPage;
        int countStudent = studentMapper.count(getSearch());
        int pageCount = (int) Math.ceil(countStudent / (double) rowsPerPage);
        List<Student> listStudents = studentMapper.getByPage(getSearch(), offset, rowsPerPage);

        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("students", listStudents);
        jsonObject.put("pageCount", pageCount);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }

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
        if (!isValid()) {
            return CustomError.createCustomError("Vui lòng nhập đầy đủ thông tin", 400, response);
        }

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // create student mapper
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        // insert student
        Student student = new Student(name, branch, percentage, phone, email);
        studentMapper.insert(student);

        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("student", student);

        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 201, response);
    }

    // get student by id

    @Action(value = "*", params = { "id", "{1}" }, results = {
            @Result(location = "/index.html"),
    })
    public String getStudent() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = studentMapper.getById(id);

        if (student == null) {
            return CustomError.createCustomError("Không tồn tại sinh viên có ID " + id, 404, response);
        }
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("student", student);

        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }

    // edit student

    @Action(value = "/api/v1/student/edit/*", params = { "id", "{1}" }, results = {
            @Result(name = "success", location = "/index.html"),
    })
    public String editStudent() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = studentMapper.getById(id);
        if (student == null) {
            return CustomError.createCustomError("Không tồn tại sinh viên có ID " + id, 404, response);
        }
        if (!isValid()) {
            return CustomError.createCustomError("Vui lòng nhập đầy đủ thông tin", 400, response);
        }
        student.setName(name);
        student.setBranch(branch);
        student.setPercentage(percentage);
        student.setPhone(phone);
        student.setEmail(email);
        studentMapper.update(student);
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("student", student);

        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }

    // delete student
    @Action(value = "/api/v1/student/delete/*", params = { "id", "{1}" }, results = {
            @Result(location = "/index.html") })
    public String deleteStudent() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        studentMapper.delete(getId());
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("message", "Xóa sinh viên " + getId() + " thành công");
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonObject, 200, response);
    }
}
