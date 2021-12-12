<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./header.jsp" />
<form id="registerForm" action>
    Username: <input type="text" id="username" name="username" onkeyup="validateUNameFrontEnd()" /><br/>
    <div id="username_error"></div>
    Password: <input type="text" id="password" name="password" onkeyup="validatePasswordFrontEnd()" /><br/>
    <div id="password_error"></div>
    <!-- Ở backend có validate email lần nữa-->
    Email: <input type="email" id="email" name="email" /><br/>
    <div id="email_error"></div>
    <input type="button" value="Register" onclick="validateRegisterForm();"/>
</form>
<script src='<c:url value="/js/register.js"/>'></script>
<jsp:include page="./footer.jsp" />