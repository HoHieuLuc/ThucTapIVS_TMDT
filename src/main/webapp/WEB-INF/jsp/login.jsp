<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./header.jsp" />
<form id="loginForm">
    Username: <input type="text" name="username" autocomplete="off" /><br />
    Password: <input type="password" name="password" /><br />
    <input type="submit" value="Login" />
    <div class="text-danger" id="login_error"></div>
</form>
<script src='<c:url value="/js/login.js"/>'></script>
<jsp:include page="./footer.jsp" />