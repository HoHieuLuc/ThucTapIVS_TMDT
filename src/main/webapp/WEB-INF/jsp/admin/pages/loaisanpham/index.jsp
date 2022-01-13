<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />

<div class="container">
    <div>
        <form class="searchForm input-group mb-3">
            <input name="search" type="text" class="form-control" placeholder="Tìm kiếm">
            <input type="submit" class="btn btn-outline-secondary" type="button" value="Tìm kiếm">
        </form>
    </div>
    <table class="tlt-fixed-table table table-striped table-hover">
        <thead>
            <tr>
                <th>Loại sản phẩm</th>
                <th>Loại sản phẩm cha</th>
            </tr>
        </thead>
        <tbody id="listLoaiSanPham"></tbody>
    </table>
    <div id="phanTrang" class="d-flex justify-content-center"></div>
</div>

<script>
    document.getElementById('aside-lsp-list').classList.add('active');
    document.getElementById('aside-lsp').classList.add('menu-is-opening', 'menu-open');
</script>
<script src='<c:url value="/js/admin/loaisanpham/index.js"/>'></script>

<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />