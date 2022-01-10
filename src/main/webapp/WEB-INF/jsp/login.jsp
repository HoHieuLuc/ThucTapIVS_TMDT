<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<form id="loginForm" class="row">
    <div class="col-sm-12 col-md-2 col-lg-3"></div>
    <div class="col-sm-12 col-md-8 col-lg-6 container mx-auto shadow rounded">
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
    <div class="col-sm-12 col-md-2 col-lg-3"></div>
</form>

<script src='<c:url value="/js/login.js"/>'></script>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />