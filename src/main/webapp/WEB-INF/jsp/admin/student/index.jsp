<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp" />

<div>Danh sách sinh viên</div>
<a href='<c:url value="/admin/student/create"/>' class="btn btn-primary">Thêm mới</a>
<button class="btn btn-warning" id="btn-reload">Reload</button>
<div class="container">
    <div>
        <form id="search-form">
            <div class="form-group">
                <label for="">Tìm kiếm</label>
                <input type="text" class="form-control" name="search" id="search">
            </div>
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
        </form>
    </div>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Branch</th>
                <th>Percentage</th>
                <th>Phone</th>
                <th>Email</th>
                <th class='d-flex justify-content-center'>Chức năng</th>
            </tr>
        </thead>
        <tbody id="student-list"></tbody>
    </table>
    <div id="pagination" class="d-flex justify-content-center"></div>
</div>
<script src='<c:url value="/js/pagination.js"/>'></script>
<script src='<c:url value="/js/student/browser.js"/>'></script>
<jsp:include page="../footer.jsp" />