<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />
    <div class="container">
testlan6
      Bộ lọc: <select id="listSPByStatus">
        <option value="-1">Bị xóa</option>
        <option value="0">Trong kho</option>
        <option value="1">Chờ duyệt</option>
        <option value="2">Được duyệt</option>
      </select>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Ngày đăng</th>
            <th>Tên người bán</th>
            <th>Tên loại sản phẩm</th>
            <th class='d-flex justify-content-center'>Chức năng</th>
          </tr>
        </thead>
        <tbody id="listSanPham"></tbody>
      </table>
    </div>



    <script src='<c:url value="/js/admin/kiemduyet/listsanpham_bystatus.js"/>'></script>
    <jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />