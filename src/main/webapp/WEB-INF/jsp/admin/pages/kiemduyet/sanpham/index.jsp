<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />
    <div class="container">

     Bộ lọc: <select id="listSPByStatus" >
        <option value="-1">Bị xóa</option>
        <option value="0">Trong kho</option>
        <option value="1">Chờ duyệt</option>
        <option value="2">Được duyệt</option>
      </select>

      <div id="listSanPham">
        Đang tải dữ liệu
      </div>
    </div>



    <script src='<c:url value="/js/admin/kiemduyet/listsanpham_bystatus.js"/>'></script>
    <jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />