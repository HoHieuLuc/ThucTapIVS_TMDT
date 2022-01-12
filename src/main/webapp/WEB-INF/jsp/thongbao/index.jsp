<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />

<div>
    <form class="searchForm input-group mb-3">
        <input name="search" type="text" class="form-control w-50" placeholder="Tìm kiếm">
        <select name="status" class="form-select">
            <option value="0">Chưa đọc</option>
            <option value="-1">Tất cả</option>
        </select>
        <input type="submit" class="btn btn-outline-secondary" type="button" value="Tìm kiếm">
    </form>
</div>
<table class="tlt-fixed-table table table-striped table-hover">
    <thead>
        <tr>
            <th>Nội Dung</th>
            <th>Người Gửi</th>
            <th>Ngày Tạo</th>
            <th><div class="text-center">Chức năng</div></th>
        </tr>
    </thead>
    <tbody id="thongbao-table"></tbody>
</table>
<div id="pagination" class="d-flex justify-content-center"></div>

<script src='<c:url value="/js/listThongBao.js"/>'></script>
<script>
    document.getElementById('aside-san-pham').classList.add('active');
    document.getElementById('aside-kho-hang').classList.add('menu-is-opening', 'menu-open');
</script>
<jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
