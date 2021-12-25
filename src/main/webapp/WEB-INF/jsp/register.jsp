<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./header.jsp" />

<body>
    <div class="container">
    <!--  -->
        <form id="registerForm" enctype="multipart/form-data"> 
            
            <div class="row jumbotron">
                <h2 class="col-sm-6 form-title">Đăng ký làm thành viên của sàn thương mại điện tử TLT</h2>
                <div class="col-sm-6 form-group">
                    <label>Họ và tên</label>
                    <input type="text" class="form-control" name="ten" 
                        placeholder="Nhập họ và tên (Bắt buộc)">
                    <p id="ten_error" class="text-danger"></p>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Địa chỉ</label>
                    <input type="text" class="form-control" name="diaChi"  placeholder="Nhập địa chỉ.">
                </div>
                <div class="col-sm-6 form-group">
                    <label>Giới thiệu bản thân</label>
                    <textarea type="text" class="form-control" name="gioiThieu"  placeholder="Viết giới thiệu về bản thân (Tối đa 255 kí tự)"></textarea>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Số điện thoại</label>
                    <input type="text" class="form-control" name="soDienThoai"  placeholder="Số điện thoại (Bắt buộc)">
                    <p id="phone_error" class="text-danger"></p>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Twitter</label>
                    <input type="text" class="form-control" name="twitterLink" 
                        placeholder="">
                    <p id="twitter_error" class="text-danger"></p>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Facebook</label>
                    <input type="text" class="form-control" name="facebookLink" 
                        placeholder="">
                    <p id="facebook_error" class="text-danger"></p>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Trang cá nhân</label>
                    <input type="text" class="form-control" name="trangCaNhan"  placeholder="Có thể là tiktok, zalo, kênh youtube, web cá nhân">
                </div>
                <div class="col-sm-6 form-group">
                    <label>Email</label>
                    <input type="email" class="form-control" name="email" 
                        placeholder="Nhập địa chỉ email (Bắt buộc)" >
                    <p id="email_error" class="text-danger"></p>
                </div>
                <div class="col-sm-3 form-group">
                    <label>Username</label>
                    <input type="text" class="form-control" name="username"  placeholder="Username (Bắt buộc)" >
                    <p id="username_error"></p>
                </div>
                <div class="col-sm-3 form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="password"  placeholder="Password (Bắt buộc)" >
                    <p id="password_error" class="text-danger"></p>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Ngày sinh</label>
                    <input type="Date" name="ngaySinh" class="form-control" required>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Giới tính</label>
                    <select name="gioiTinh" class="form-control browser-default custom-select">
                        <option value="0">Nam</option>
                        <option value="1">Nữ</option>
                    </select>
                </div>
                <!-- Upload ảnh, đừng đổi tên userImage nhan, đổi nó lỗi  -->
                <div class="col-sm-6 form-group">
                    <input type="file" id="userImage" name="userImage">
                </div>
                <!-- Upload ảnh -->
                <div class="col-sm-6 form-group">
                    <label>Xác nhận mật khẩu</label>
                    <input type="password" name="xacNhanPassword" class="form-control" 
                        placeholder="Nhập lại mật khẩu">
                    <p id="retype_password" class="text-danger"></p>
                </div>
                <div class="col-sm-12">
                    <input type="checkbox" class="form-check d-inline"  ><label 
                        class="form-check-label">&nbsp;Tôi đồng ý với tất cả điều khoản.
                    </label>
                </div>

                <div class="col-sm-12 form-group mb-0">
                    <input type="submit" class="btn btn-primary float-right" value="Đăng ký">
                </div>

            </div>
        </form>
        <script src='<c:url value="/js/register.js"/>'></script>
    </div>
   
</body>
<jsp:include page="./footer.jsp" />