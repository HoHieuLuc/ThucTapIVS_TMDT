<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!--Axios jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        label {
            font-weight: 600;
            color: #555;
        }

        body {
            background: rgb(34,193,195);
            background: linear-gradient(0deg, rgba(34,193,195,1) 0%, rgba(253,187,45,1) 100%);

        }
    </style>
</head>

<body>
    <div class="container">
        <form id="registerForm"> 
            
            <div class="row jumbotron">
                <h2 class="col-sm-6 form-title">Đăng ký làm thành viên của sàn thương mại điện tử TLT</h2>
                <div class="col-sm-6 form-group">
                    <label>Họ và tên</label>
                    <input type="text" class="form-control" name="ten" 
                        placeholder="Nhập họ và tên" required>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Địa chỉ</label>
                    <input type="text" class="form-control" name="dia_chi"  placeholder="Nhập địa chỉ."
                        required>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Giới thiệu bản thân</label>
                    <input type="text" class="form-control" name="gioi_thieu"  placeholder="Viết giới thiệu về bản thân (Tối đa 255 kí tự)"
                        required>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Số điện thoại</label>
                    <input type="text" class="form-control" name="so_dien_thoai"  placeholder="Số điện thoại"
                        required>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Twitter</label>
                    <input type="text" class="form-control" name="twitter_link" 
                        placeholder="">
                </div>
                <div class="col-sm-6 form-group">
                    <label>Facebook</label>
                    <input type="text" class="form-control" name="facebook_link" 
                        placeholder="">
                </div>
                <div class="col-sm-6 form-group">
                    <label>Trang cá nhân</label>
                    <input type="text" class="form-control" name="trang_ca_nhan"  placeholder="Số điện thoại"
                        required>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Email</label>
                    <input type="email" class="form-control" name="email" 
                        placeholder="Nhập lại địa chỉ email" required>
                </div>
                <div class="col-sm-3 form-group">
                    <label>Username</label>
                    <input type="text" class="form-control" name="username"  placeholder="Username" required>
                </div>
                <div class="col-sm-3 form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="password"  placeholder="Password" required>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Ngày sinh</label>
                    <input type="Date" name="ngay_sinh" class="form-control"  placeholder="" required>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Giới tính</label>
                    <select  class="form-control browser-default custom-select">
                        <option name="gioi_tinh" value="0">Nam</option>
                        <option name="gioi_tinh" value="1">Nữ</option>
                    </select>
                </div>
                <div class="col-sm-6 form-group">
                    <label>Xác nhận mật khẩu</label>
                    <input type="password" name="xac_nhan_password" class="form-control" 
                        placeholder="Nhập lại mật khẩu" required>
                </div>
                <div class="col-sm-12">
                    <input type="checkbox" class="form-check d-inline"  required><label 
                        class="form-check-label">&nbsp;Tôi đồng ý với tất cả điều khoản.
                    </label>
                </div>

                <div class="col-sm-12 form-group mb-0">
                    <input type="submit" class="btn btn-primary float-right">Đăng ký</button>
                </div>

            </div>
        </form>
        <script src='<c:url value="js/khachHang/registerCustomer.js"/>'></script>
    </div>
   
</body>
</html>