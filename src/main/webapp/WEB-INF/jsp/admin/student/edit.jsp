<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp" />

<h2>Sửa thông tin sinh viên</h2>
<button class="btn btn-link back-btn">Quay lại</button>
<form name="student" id="edit-student-form" class="container">
    <div class="mb-3">
        <label for="" class="form-label">ID</label>
        <input type="text" class="form-control" name="id" id="id" disabled>
    </div>
    <div class="mb-3">
        <label for="" class="form-label">Name</label>
        <input type="text" class="form-control" name="name" id="name">
    </div>
    <div class="mb-3">
        <label for="" class="form-label">Branch</label>
        <input type="text" class="form-control" name="branch" id="branch">
    </div>
    <div class="mb-3">
        <label for="" class="form-label">Percentage</label>
        <input type="number" class="form-control" name="percentage" id="percentage">
    </div>
    <div class="mb-3">
        <label for="" class="form-label">Email</label>
        <input type="email" class="form-control" name="email" id="email">
    </div>
    <div class="mb-3">
        <label for="" class="form-label">Phone</label>
        <input type="number" class="form-control" name="phone" id="phone">
    </div>
    <input type="submit" class="btn btn-primary edit-btn" value="Sửa">
</form>


<div id="form-alert" class=""></div>




<script src='<c:url value="/js/student/edit.js"/>'></script>
<jsp:include page="../footer.jsp" />