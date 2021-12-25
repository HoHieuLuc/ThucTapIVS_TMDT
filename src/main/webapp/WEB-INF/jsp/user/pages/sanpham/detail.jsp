<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../header.jsp" />
<div class="container">
    <div id="sanpham"></div>
</div>
<script src='<c:url value="/js/khachHang/khohang/sanpham.js"/>'></script>
<script>
    document.getElementById('aside-san-pham').classList.add('active');
    document.getElementById('aside-kho-hang').classList.add('menu-is-opening', 'menu-open');
</script>
<jsp:include page="../../footer.jsp" />