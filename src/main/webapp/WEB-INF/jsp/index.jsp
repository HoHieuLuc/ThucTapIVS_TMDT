<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/jsp/include/header.jsp" />

  
      <div class="container-fluid bg-3 text-center">
        <h3>TRANG CHỦ</h3><br>
        <div class="row" id="listSanPham">

        </div>
        <script src='<c:url value="/js/product/sanpham-browser.js"/>'></script>
      </div>
  


    <jsp:include page="/WEB-INF/jsp/include/footer.jsp" />