<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />

    <!-- Các button tương tác với trang web -->
    <div class="btn-group" role="group" aria-label="Bộ lọc các nút xem danh sách sản phẩm">
    <button type="button" class="btn btn-danger" id="biXoa">Đã Xóa</button>
    <button type="button" class="btn btn-primary" id="trongKho">Trong kho</button>
    <button type="button" class="btn btn-warning" id="yeuCauDuyet">Chờ duyệt</button>
    <button type="button" class="btn btn-success" id="daDuyet">Đã duyệt</button>
    </div>


    <div id="listSanPham">
        Đang tải dữ liệu
    </div>


   <script src='<c:url value="/js/admin/kiemduyet/listsanpham_bystatus.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />
