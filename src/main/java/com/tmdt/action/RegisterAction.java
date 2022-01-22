package com.tmdt.action;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.mindrot.jbcrypt.BCrypt;
import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;
import com.tmdt.utilities.JsonResponse;
import com.tmdt.utilities.ProjectPath;

import mybatis.mapper.*;
import com.tmdt.model.*;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
        "namespace", "/",
        "actionName", "bad-request"
})
@InterceptorRef("loggedInStack")
public class RegisterAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    // Regex vừa dùng kiểm tra đại số boolean, vừa dùng để in từng thông báo lỗi cụ
    // thể cho phía Client
    static final String USERNAME_REGEX = "^[A-Za-z0-9]{6,20}$";
    static final String PASSWORD_REGEX = "^[A-Z]{1}[A-Za-z0-9]{7,29}$";
    static final String EMAIL_REGEX = "^(.+)@(\\S+)$";
    static final String PHONE_REGEX = "^[0-9]{9,12}";
    //

    private Date ngaySinh;
    private String ten;
    private String diaChi;
    private String gioiThieu;
    private int gioiTinh;
    private String username;
    private String password;
    private String email;
    private String soDienThoai;
    private String facebookLink;
    private String twitterLink;
    private String trangCaNhan;
    private String xacNhanPassword;

    /*
     * Nó tự động thêm 3 tham số trong request, đó là:
     * Ví dụ (1)
     * File file biểu diễn file. Bạn có thể áp dụng các phương thức trên đối tượng
     * này.
     * String filename biểu diễn tên file.
     * String contentType xác định kiểu nội dung của file.
     */
    // Chức năng upload ảnh, đừng đổi tên 3 cái này nhan, đổi nó lỗi :((
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;

    // region getter and setter
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

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
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

    // endregion getter setter

    // Validate All Field
    public boolean isValid() {
        return Pattern.matches(USERNAME_REGEX, username) && Pattern.matches(PASSWORD_REGEX, password)
                && Pattern.matches(EMAIL_REGEX, email) && between(ten, 2, 50)
                && between(facebookLink, 0, 100) && between(twitterLink, 0, 100)
                && Pattern.matches(PHONE_REGEX, soDienThoai)
                && (xacNhanPassword.equals(password))
                && userImage != null
                && userImageContentType.contains("image/");
    }

    // Tạo SQL_SESSION_FACTORY để chuẩn bị cho kết nối database
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    //TODO: Xử lí đăng ký
    @Action(value = "/registerSubmit", results = {
            @Result(name = "success", location = "/index.html"),
    })
    public String registerSubmit() throws IOException {
        if (isValid()) {
            SqlSession sqlSession = sqlSessionFactory.openSession();

            KhachHangMapper khachHangMapper = sqlSession.getMapper(KhachHangMapper.class);
            TaiKhoanMapper taiKhoanMapper = sqlSession.getMapper(TaiKhoanMapper.class);

            password = BCrypt.hashpw(password, BCrypt.gensalt(12));

            // tránh trùng tên file
            String avatarFileName = System.currentTimeMillis() + "_" + userImageFileName;

            TaiKhoan taiKhoan = new TaiKhoan(gioiTinh, 0, 1, username, password, email,
                    soDienThoai, "KH", avatarFileName, ngaySinh);

            // Thêm dữ liệu vào database,
            // Kiểm tra tài khoản mới có trùng username,email với tài khoản cũ
            try {
                taiKhoanMapper.insertTaiKhoan(taiKhoan);
                int accountID = taiKhoanMapper.getTaiKhoanIdByUsername(username);
                // Khi tạo tài khoản thành công thì mới tạo thông tin khách hàng
                KhachHang khachHang = new KhachHang(accountID, 0, ten, diaChi,
                        gioiThieu);
                khachHangMapper.themKhachHang(khachHang);

                Map<String, Object> loginInfo = taiKhoanMapper.getKhLoginInfoByUsername(username);
                System.out.println(loginInfo);

                int maKhachHang = (int) loginInfo.get("maNguoiDung");

                // Có mã khách hàng, tiến hành tạo thông tin liên hệ
                ThongTinLienHeMapper thongTinLienHeMapper = sqlSession.getMapper(ThongTinLienHeMapper.class);
                ThongTinLienHe thongTinLienHe = new ThongTinLienHe(maKhachHang, trangCaNhan, twitterLink, facebookLink);
                thongTinLienHeMapper.insertThongTinLienHe(thongTinLienHe);

                // Flush database connection, batch script and close connection
                sqlSession.commit();

                String avatar = (String) loginInfo.get("avatar");
                //
                String filePath = session.getServletContext().getRealPath("/") + "images\\user\\";
                File fileToCreate = new File(filePath, avatarFileName);
                FileUtils.copyFile(this.userImage, fileToCreate);

                // lưu file vào thư mục project để khỏi mất khi compile lại
                String LocalPath = ProjectPath.getPath() + "\\images\\user\\";
                File test = new File(LocalPath, avatarFileName);
                FileUtils.copyFile(this.userImage, test);

                session.setAttribute("loggedIn", true);
                session.setAttribute("username", username);
                session.setAttribute("accountID", accountID);
                session.setAttribute("maNguoiDung", maKhachHang);
                session.setAttribute("ten", ten);
                session.setAttribute("level", 0);
                session.setAttribute("avatar", avatar);
                session.setAttribute("permission", "KH");
                // không phải lỗi nhưng xài luôn cho tiện
                return CustomError.createCustomError("Đăng ký thành công", 201, response);
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
                        "Username có tối thiểu 6 ký tự và tối đa 20 kí tự gồm chữ thường, chữ hoa và số");
            }
            if (!Pattern.matches(PASSWORD_REGEX, password)) {
                jsonObject.put("password",
                        "Password có tối thiểu 8 ký tự và tối đa 30 kí tự, bao gồm kí tự chữ cái và chữ số, kí tự đầu tiên bắt buộc viết hoa");
            }
            if (!Pattern.matches(EMAIL_REGEX, email)) {
                jsonObject.put("email", "email không đúng định dạng");
            }
            if (!between(ten, 2, 50)) {
                jsonObject.put("ten", "Tên phải từ 2 đến 50 kí tự");
            }
            if (!between(facebookLink, 0, 100)) {
                jsonObject.put("facebookLink", "Facebook link không quá 30 kí tự");
            }
            if (!between(twitterLink, 0, 100)) {
                jsonObject.put("twitterLink", "Twitter link không quá 30 kí tự");
            }
            if (!xacNhanPassword.equals(password)) {
                jsonObject.put("xac_nhan_password", "Mật khẩu nhập lại không khớp");
            }
            if (!Pattern.matches(PHONE_REGEX, soDienThoai)) {
                jsonObject.put("dien_thoai", "Số điện thoại phải từ 9 đến 12 số");
            }
            if (userImage == null || !userImageContentType.contains("image/")) {
                jsonObject.put("avatar", "Ảnh không hợp lệ");
            }
            return JsonResponse.createJsonResponse(jsonObject, 400, response);
        }
    }

    // trang đăng ký khách hàng
    @Action(value = "/register", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/register.jsp"),
    })
    public String viewRegister() {
        return SUCCESS;
    }

    @Action(value = "/checkUsername", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String checkUsername() throws IOException {
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        if (!Pattern.matches(USERNAME_REGEX, username)) {
            jsonRes.put("invalidInput", true);
            jsonRes.put("message",
                    "Username có tối thiểu 6 ký tự và tối đa 20 kí tự gồm chữ thường, chữ hoa và số");
            return JsonResponse.createJsonResponse(jsonRes, 200, response);
        }

        SqlSession sqlSession = sqlSessionFactory.openSession();

        TaiKhoanMapper taiKhoanMapper = sqlSession.getMapper(TaiKhoanMapper.class);

        int check = taiKhoanMapper.checkDuplicateUsername(username);

        if (check > 0) {
            jsonRes.put("message", "Tài khoản đã tồn tại");
            return JsonResponse.createJsonResponse(jsonRes, 409, response);
        } else {
            jsonRes.put("message", "Bạn có thể sử dụng tài khoản này");
            return JsonResponse.createJsonResponse(jsonRes, 200, response);
        }
    }
}
