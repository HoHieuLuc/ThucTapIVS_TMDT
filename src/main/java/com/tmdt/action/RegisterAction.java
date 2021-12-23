package com.tmdt.action;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.mindrot.jbcrypt.BCrypt;


import java.util.Date;
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
public class RegisterAction extends ActionSupport{

    // Regex vừa dùng kiểm tra đại số boolean, vừa dùng để in từng thông báo lỗi cụ
    // thể cho phía Client
    static final String USERNAME_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,14}$";
    static final String EMAIL_REGEX = "^(.+)@(\\S+)$";
    // static final String PHONE_REGEX = "/((09|03|07|08|05)+([0-9]{8})\b)/g";
   
    private Date ngay_sinh;
    private String  ten, dia_chi,gioi_thieu;
    private static final long serialVersionUID = 1L;
    private int id;
    private int gioi_tinh,so_lan_canh_cao,status;
    private String username, password, email, so_dien_thoai,facebook_link,twitter_link,trang_ca_nhan,ma_quyen,xac_nhan_password;
     //region getter and setter
    public String getXac_nhan_password() {
        return xac_nhan_password;
    }


    public void setXac_nhan_password(String xac_nhan_password) {
        this.xac_nhan_password = xac_nhan_password;
    }


    //Kiểm tra độ dài chuỗi có nằm trong khoảng từ min đến max
    public static boolean between(String variable, int minValueInclusive, int maxValueInclusive) {
         return variable.length() >= minValueInclusive && variable.length() <= maxValueInclusive;
    }


    public Date getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getGioi_thieu() {
        return gioi_thieu;
    }

    public void setGioi_thieu(String gioi_thieu) {
        this.gioi_thieu = gioi_thieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa_quyen() {
        return ma_quyen;
    }

    public void setMa_quyen(String ma_quyen) {
        this.ma_quyen = ma_quyen;
    }

    public int getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(int gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public int getSo_lan_canh_cao() {
        return so_lan_canh_cao;
    }

    public void setSo_lan_canh_cao(int so_lan_canh_cao) {
        this.so_lan_canh_cao = so_lan_canh_cao;
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

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getFacebook_link() {
        return facebook_link;
    }

    public void setFacebook_link(String facebook_link) {
        this.facebook_link = facebook_link;
    }

    public String getTwitter_link() {
        return twitter_link;
    }

    public void setTwitter_link(String twitter_link) {
        this.twitter_link = twitter_link;
    }

    public String getTrang_ca_nhan() {
        return trang_ca_nhan;
    }

    public void setTrang_ca_nhan(String trang_ca_nhan) {
        this.trang_ca_nhan = trang_ca_nhan;
    }
    

    //end region getter setter


    //Validate All Field
    public boolean isValid() {
        return Pattern.matches(USERNAME_REGEX, username) && between(password,8,14)
                && Pattern.matches(EMAIL_REGEX, email) && between(ten,10,20) 
                 && between(facebook_link,0,30) && between(twitter_link,0,30) && (xac_nhan_password.equals(password));
    }
    
        // Tạo SQL_SESSION_FACTORY để chuẩn bị cho kết nối database
        SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

        // Respone hay dùng cho AJAX và JSON
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        @Action(value = "/registerSubmit", results = {
            @Result(name = "success", location = "/index.html"),
    })
    public String registerSubmit() throws Exception {
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
            //Múi giờ mặc định
	        ZoneId defaultZoneId = ZoneId.systemDefault();
            // Đổi ngày tạo tài khoản và ngày hết hạn sang SQL Date
            Date ngay_tao = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
            TaiKhoan taiKhoan = new TaiKhoan(gioi_tinh, so_lan_canh_cao, status, username, password, email, so_dien_thoai, "KH", "null", ngay_tao, ngay_sinh);
            

            // Thêm dữ liệu vào database,
            // Kiểm tra tài khoản mới có trùng username,email với tài khoản cũ
            try {
                taiKhoanMapper.insert(taiKhoan);
                //Khi tạo tài khoản thành công thì mới tạo thông tin khách hàng
                KhachHang khachHang = new KhachHang(taiKhoanMapper.getCurrentInsertId(username),0, ten, dia_chi, gioi_thieu);
                khachHangMapper.insert(khachHang);

                session.setAttribute("loggedIn", true);
                session.setAttribute("username", username);
                session.setAttribute("permission","KH");
                int maKhachHang = khachHangMapper.getMaKh(taiKhoanMapper.getCurrentInsertId(username));
                session.setAttribute("maKhachHang",maKhachHang);

               // Flush database connection, batch script and close connection
               sqlSession.commit();
               sqlSession.close();
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
            }
        } else {
            Map<String, Object> jsonObject = new HashMap<String, Object>();
            if (!Pattern.matches(USERNAME_REGEX, username)) {
                jsonObject.put("username",
                        "Username có tối thiểu 6 ký tự và tối đa 14 kí tự, ít nhất một chữ cái và một số, không có kí tự khoảng trắng ");
            }
            if (!between(password,8,14)) {
                jsonObject.put("password",
                        "Password có tối thiểu 8 ký tự và tối đa 14 kí tự, ít nhất một chữ cái và một số, một kí tự @ $ ! % * ? &");
            }
            if (!Pattern.matches(EMAIL_REGEX, email)){
                jsonObject.put("email","email không đúng định dạng");
            }
            if (!between(ten,10,20)){
                jsonObject.put("ten","Tên phải từ 10 đến 20 kí tự");
            }
            // if (!Pattern.matches(PHONE_REGEX,so_dien_thoai)){
            //     jsonObject.put("so_dien_thoai","Điện thoại phải là số điện thoại của các nhà mạng, có 10 chữ số");
            // }
            if (!between(facebook_link,0,30)){
                jsonObject.put("facebook_link","Facebook link không quá 30 kí tự");
            }
            if (!between(twitter_link,0,30)){
                jsonObject.put("twitter_link","Twitter link không quá 30 kí tự");
            }
            if (xac_nhan_password.equals(password))
            {
                jsonObject.put("xac_nhan_password","Mật khẩu nhập lại không khớp");
            }
            return JsonResponse.createJsonResponse(jsonObject, 400, response);
        }
    }

    // trang đăng ký khách hàng
        @Action(value = "/registerCustomer", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/registerCustomer.jsp"),
    })
    public String viewRegisterCustomer() {
        return SUCCESS;
    }
    
}
