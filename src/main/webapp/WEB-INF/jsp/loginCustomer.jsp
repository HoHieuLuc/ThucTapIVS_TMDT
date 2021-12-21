<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./header.jsp" />

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
                <p id="login_error"></p>
            </div>
        </form>
        <p id="login_error"></p>
    </div>
</body>
<script src='<c:url value="/js/khachHang/loginCustomer.js"/>'></script>

<jsp:include page="./footer.jsp" />