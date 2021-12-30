<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<body>
<div class="container-fluid bg-3 text-center">    
  <h3>SẢN PHẨM MỚI</h3><br>
  <div class="row" id="listSanPham">

  </div>
  <script src='<c:url value="/js/product/sanpham-browser.js"/>'></script>
  <script src='<c:url value="/js/product/loaisanpham-browser.js"/>'></script>
  <script src='<c:url value="/js/store/store-browser.js"/>'></script>
</div>
</body>


<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />