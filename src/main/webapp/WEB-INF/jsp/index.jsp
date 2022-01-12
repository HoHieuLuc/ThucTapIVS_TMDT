<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />


<div class="container-fluid">
    <c:choose>
        <c:when test="${sessionScope.level == 0 || sessionScope.loggedIn == null || !sessionScope.loggedIn}">
            <div class="mb-3" id="sanPhamVuaXem">
        
            </div>
        </c:when>
    </c:choose>
    
    <div class="mb-3">
        <div class="d-flex">
            <h4>Những loại mặt hàng phổ biến
                <i class="text-muted fas fa-grip-lines-vertical"></i>
                <a class="text-decoration-none text-dark" href='<c:url value="/category"/>'>
                    Xem tất cả
                    <i class="fas fa-angle-double-right"></i>
                </a>
            </h4>
        </div>
        <div id="loaiSanPhamPhoBien" class="row">

        </div>
    </div>

    <div class="mb-3">
        <div class="d-flex">
            <h4>Top người bán hàng
                <i class="text-muted fas fa-grip-lines-vertical"></i>
                <a class="text-decoration-none text-dark" href='<c:url value="/store"/>'>
                    Xem tất cả
                    <i class="fas fa-angle-double-right"></i>
                </a>
            </h4>
        </div>
        <div id="storeDuocDanhGiaCao" class="row">

        </div>
    </div>

    <div class="mb-3">
        <div class="d-flex">
            <h4>Sản phẩm mới
                <i class="text-muted fas fa-grip-lines-vertical"></i>
                <a class="text-decoration-none text-dark" href="#">
                    Xem tất cả
                    <i class="fas fa-angle-double-right"></i>
                </a>
            </h4>
        </div>
        <div id="sanPhamMoi" class="row">

        </div>
    </div>
</div>


<script src='<c:url value="/js/home.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />