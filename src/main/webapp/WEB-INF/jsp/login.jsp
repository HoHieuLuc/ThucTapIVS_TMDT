<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<body>
    <div class="container">
        <form id="loginForm">
            <div class="container w-50 mx-auto shadow rounded">
                <h2 class="text-center">Đăng nhập</h2>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingUsername" name="username" placeholder="Username" required>
                    <label for="floatingUsername">Tài khoản</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password" required>
                    <label for="floatingPassword">Mật khẩu</label>
                </div>
                <p id="login_error" class="text-danger"></p>
                <div class="d-grid">
                    <input type="submit" class="btn btn-primary fs-5" value="Đăng nhập">
                </div>
                <div class="d-grid">
                    <button class="btn btn-link text-decoration-none fs-5">Quên mật khẩu?</button>
                </div>
            </div>
        </form>
    </div>
</body>
<script src='<c:url value="/js/login.js"/>'></script>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />