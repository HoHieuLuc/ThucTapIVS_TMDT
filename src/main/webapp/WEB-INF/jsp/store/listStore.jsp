<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<!-- Style cho trang listStore-->
<style>

</style>
<div class="container-fluid bg-3 text-center">
    <h3>DANH SÁCH CỬA HÀNG</h3><br>
    <div class="row" id="storeList">

    </div>
    
</div>



<script src='<c:url value="/js/store/store-browser.js"/>'></script>


<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />