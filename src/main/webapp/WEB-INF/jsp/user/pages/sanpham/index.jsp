<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />
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
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Số lượng đã bán</th>
                <th>Ngày đăng</th>
                <th>Xếp hạng</th>
                <th>Tình trạng</th>
                <th class='d-flex justify-content-center'>Chức năng</th>
            </tr>
        </thead>
        <tbody id="sanpham-list"></tbody>
    </table>
    <div id="pagination" class="d-flex justify-content-center"></div>
</div>
<script src='<c:url value="/js/khachHang/khohang/index.js"/>'></script>
<script>
    document.getElementById('aside-san-pham').classList.add('active');
    document.getElementById('aside-kho-hang').classList.add('menu-is-opening', 'menu-open');
</script>
<jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
