<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="vi">

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
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
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
        <form id="loginForm">
            
            <div class="w-50 mx-auto jumbotron">
                <h2 class="col-sm-12 form-title">Đăng nhập</h2>
                <div class="col-sm-12 form-group">
                    <label>Username</label>
                    <input type="text" class="form-control" name="username" id="zip" placeholder="Username" required>
                </div>
                <div class="col-sm-12 form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="password" id="zip" placeholder="Password" required>
                </div>
                <div class="col-sm-12 form-group">
                    <button class="btn btn-danger">Quên mật khẩu</button>
                    
                </div>
                <div class="col-sm-12 form-group">
                    <input type="submit" class="btn btn-primary">Đăng nhập</button>
                    
                </div>
            </div>
        </form>
        <p id="login_error"></p>
    </div>
</body>
<script src="js/khachHang/loginCustomer.js"></script>

</html>