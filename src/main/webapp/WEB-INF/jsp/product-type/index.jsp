<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <jsp:include page="/WEB-INF/jsp/include/header.jsp" />

            <!-- Trang này giống trang index gốc, nhưng mà hiển thị sản phẩm được lọc theo loại sản phẩm-->
            <div class="container-fluid bg-3 text-center">
                <!--Đổi tiêu đề này theo tên loại sản phẩm mình đang xem-->
                <h3 id="tenLoaiSP">ĐIỆN THOẠI</h3><br>
                <div class="row" id="listSanPham">

                </div>
                <script src='<c:url value="/js/product/loaisanpham-list.js"/>'></script>

            </div>


        <jsp:include page="/WEB-INF/jsp/include/footer.jsp" />