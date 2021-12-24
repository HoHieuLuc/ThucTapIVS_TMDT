package com.tmdt.action;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.commons.io.FileUtils;
import org.mindrot.jbcrypt.BCrypt;


import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.utilities.JsonResponse;

import mybatis.mapper.*;
import com.tmdt.model.*;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
@InterceptorRef("loggedInStack")
public class RegisterAction extends ActionSupport  {

    // Regex vừa dùng kiểm tra đại số boolean, vừa dùng để in từng thông báo lỗi cụ
    // thể cho phía Client
    static final String USERNAME_REGEX = "^[A-Za-z0-9]{6,14}$";
    static final String EMAIL_REGEX = "^(.+)@(\\S+)$";
    static final String PHONE_REGEX = "^[0-9]{9,12}";

    private Date ngaySinh;
    private String ten;
    private String diaChi;
    private String gioiThieu;
    private static final long serialVersionUID = 1L;
    private int id;
    private int gioiTinh;
    private int soLanCanhCao;
    private int status;
    private String username;
    private String password;
    private String email;
    private String soDienThoai;
    private String facebookLink;
    private String twitterLink;
    private String trangCaNhan;
    private String maQuyen;
    private String xacNhanPassword;

    
    /* 
        Nó tự động thêm 3 tham số trong request, đó là:
        Ví dụ (1)
            File file biểu diễn file. Bạn có thể áp dụng các phương thức trên đối tượng này.
            String filename biểu diễn tên file.
            String contentType xác định kiểu nội dung của file.
    */
    // Chức năng upload ảnh, đừng đổi tên 3 cái này nhan, đổi nó lỗi :((
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.request = servletRequest;
    }

    // region getter and setter
    public String getXacNhanPassword() {
        return xacNhanPassword;
    }

    public void setXacNhanPassword(String xacNhanPassword) {
        this.xacNhanPassword = xacNhanPassword;
    }

    // Kiểm tra độ dài chuỗi có nằm trong khoảng từ min đến max
    public static boolean between(String variable, int minValueInclusive, int maxValueInclusive) {
        return variable.length() >= minValueInclusive && variable.length() <= maxValueInclusive;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getSoLanCanhCao() {
        return soLanCanhCao;
    }

    public void setSoLanCanhCao(int soLanCanhCao) {
        this.soLanCanhCao = soLanCanhCao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getTrangCaNhan() {
        return trangCaNhan;
    }

    public void setTrangCaNhan(String trangCaNhan) {
        this.trangCaNhan = trangCaNhan;
    }

    // end region getter setter

    // Validate All Field
    public boolean isValid() {
        return Pattern.matches(USERNAME_REGEX, username) && between(password, 8, 14)
                && Pattern.matches(EMAIL_REGEX, email) && between(ten, 10, 20)
                && between(facebookLink, 0, 30) && between(twitterLink, 0, 30)
                && Pattern.matches(PHONE_REGEX, soDienThoai)
                && (xacNhanPassword.equals(password));
    }

    // Tạo SQL_SESSION_FACTORY để chuẩn bị cho kết nối database
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    @Action(value = "/registerSubmit", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/register.jsp"),
    })
    public String registerSubmit() throws IOException {
        //Test upload ảnh trước khi vô luông isValid
        //String filePath = request.getSession().getServletContext().getRealPath("/").concat("userimages");

        //Tạm thời up ảnh vào đây
        //Vì code mình chạy nó build war file vô ổ C workspace, nên tui không biết lấy đường dẫn của thư mục project của mình
        String filePath = "D:/ImageUpload/avatar";
        System.out.println("Image Location:" + filePath);//quan sat server console de thay vi tri thuc su
        

        //Kiểm tra file có null hay ko, kiểm tra có đúng định dạng ảnh hay không
        if (this.userImage != null && this.userImageContentType.contains("image/"))
        {
            File fileToCreate = new File(filePath, this.userImageFileName);
            FileUtils.copyFile(this.userImage, fileToCreate);//sao chep hinh anh trong file moi
            System.out.println("Original File name " + userImageFileName);
            System.out.println("Content Type " + userImageContentType); 
        }
        else{
            System.out.println("File không hợp lệ");
        }
    

        if (isValid()) {
            // Ở đây insert vô database sau khi validate form ok
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // Tạo KhachHangMapper và TaiKhoanMapper
            KhachHangMapper khachHangMapper = sqlSession.getMapper(KhachHangMapper.class);
            TaiKhoanMapper taiKhoanMapper = sqlSession.getMapper(TaiKhoanMapper.class);

            // String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            // Hash password sang BCrypt:
            password = BCrypt.hashpw(password, BCrypt.gensalt(12));

            // Tạo đối tượng lấy dữ liệu TaiKhoan từ constructor
            // Lấy ngày hiện tại:
            LocalDate today = LocalDate.now();
            // Múi giờ mặc định
            ZoneId defaultZoneId = ZoneId.systemDefault();
            // Đổi ngày tạo tài khoản và ngày hết hạn sang SQL Date
            Date ngay_tao = Date.from(today.atStartOfDay(defaultZoneId).toInstant());

            //Thêm biến lưu avatar
            String avatar = "Null";
            //Kiểm tra lại, nếu có ảnh và ảnh hợp lệ thì lưu ảnh, không thì lưu tên ảnh là "null"
            if (this.userImage != null && this.userImageContentType.contains("image/"))
            {
                avatar = this.userImageFileName;
            }

            TaiKhoan taiKhoan = new TaiKhoan(gioiTinh, soLanCanhCao, status, username, password, email,
                    soDienThoai, "KH", avatar, ngay_tao, ngaySinh);

            // Thêm dữ liệu vào database,
            // Kiểm tra tài khoản mới có trùng username,email với tài khoản cũ
            try {
                taiKhoanMapper.insert(taiKhoan);
                // Khi tạo tài khoản thành công thì mới tạo thông tin khách hàng
                KhachHang khachHang = new KhachHang(taiKhoanMapper.getCurrentInsertId(username), 0, ten, diaChi,
                        gioiThieu);
                khachHangMapper.insert(khachHang);

                session.setAttribute("loggedIn", true);
                session.setAttribute("username", username);
                session.setAttribute("permission", "KH");
                int maKhachHang = khachHangMapper.getMaKh(taiKhoanMapper.getCurrentInsertId(username));
                session.setAttribute("maKhachHang", maKhachHang);

                // Flush database connection, batch script and close connection
                sqlSession.commit();
                return SUCCESS;
            } catch (PersistenceException e) {
                // System.out.println(e.getMessage());
                Map<String, Object> errors = new HashMap<String, Object>();
                if (e.getMessage().contains("username_UNIQUE")) {
                    errors.put("username", "Tài khoản đã tồn tại");
                }
                if (e.getMessage().contains("email_UNIQUE")) {
                    errors.put("email", "Email đã tồn tại");
                }
                System.out.println(e.getMessage());
                return JsonResponse.createJsonResponse(errors, 409, response);
            } finally {
                sqlSession.close();
            }
        } else {
            Map<String, Object> jsonObject = new HashMap<String, Object>();
            if (!Pattern.matches(USERNAME_REGEX, username)) {
                jsonObject.put("username",
                        "Username có tối thiểu 6 ký tự và tối đa 14 kí tự, ít nhất một chữ cái và một số, không có kí tự khoảng trắng ");
            }
            if (!between(password, 8, 14)) {
                jsonObject.put("password",
                        "Password có tối thiểu 8 ký tự và tối đa 14 kí tự, ít nhất một chữ cái và một số, một kí tự @ $ ! % * ? &");
            }
            if (!Pattern.matches(EMAIL_REGEX, email)) {
                jsonObject.put("email", "email không đúng định dạng");
            }
            if (!between(ten, 10, 20)) {
                jsonObject.put("ten", "Tên phải từ 10 đến 20 kí tự");
            }
            if (!between(facebookLink, 0, 30)) {
                jsonObject.put("facebookLink", "Facebook link không quá 30 kí tự");
            }
            if (!between(twitterLink, 0, 30)) {
                jsonObject.put("twitterLink", "Twitter link không quá 30 kí tự");
            }
            if (xacNhanPassword.equals(password)) {
                jsonObject.put("xac_nhan_password", "Mật khẩu nhập lại không khớp");
            }
            if (!Pattern.matches(PHONE_REGEX, soDienThoai)) {
                jsonObject.put("dien_thoai", "Số điện thoại phải từ 9 đến 12 số");
            }
            return JsonResponse.createJsonResponse(jsonObject, 400, response);
        }
    }

    //hiển thị trang đăng ký khách hàng
    @Action(value = "/register", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/register.jsp"),
    })
    public String viewRegisterCustomer() {
        return SUCCESS;
    }

}
