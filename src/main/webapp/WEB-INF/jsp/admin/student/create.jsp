<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp" />

<h2>Add New Student To Database</h2>
<a href='<c:url value="/admin/student/"/>'>Quay lại</a>
<form name="student" id="create-student-form">
	Name: <input type="text" name="name">
	Branch: <input type="text" name="branch">
	Percentage: <input type="number" name="percentage">
	Phone: <input type="number" name="phone">
	Email: <input type="email" name="email">
	<input type="submit" value="Lưu vào database">
</form>
<div id="error-msg" class="text-danger"></div>

<script src='<c:url value="/js/student/create.js"/>'></script>
<jsp:include page="../footer.jsp" />