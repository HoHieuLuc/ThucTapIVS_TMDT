<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />
<div class="container-fluid">
  <div>
    <form class="searchForm input-group mb-3">
      <input name="search" type="text" class="form-control w-50" placeholder="Tìm kiếm">
      <select id="listSPByStatus" name="status" class="form-select">
        <option value="-1">Bị xóa</option>
        <option value="0">Trong kho</option>
        <option value="1" selected>Đang chờ duyệt</option>
        <option value="2">Đang bán</option>
      </select>
      <input type="submit" class="btn btn-outline-secondary" type="button" value="Tìm kiếm">
    </form>
    <div class="row mb-3">
        <div class="col-md-2">
          <a class="btn btn-primary" id="export-btn">Xuất Excel</a>
        </div>
    </div>
  </div>
  <table class="tlt-fixed-table table table-striped table-hover" id="myTable" filename="Danh sách kiểm duyệt sản phẩm.xls">
    <thead>
      <tr>
        <th>
          Tên sản phẩm
          <button class="sort-btn sort-ten-desc border border-0 bg-transparent" style="font-size:0.9em">
            <i class="sort-icon fas fa-angle-up"></i>
          </button>
        </th>
        <th>
          Giá
          <button class="sort-btn sort-gia-desc border border-0 bg-transparent" style="font-size:0.9em">
            <i class="sort-icon fas fa-angle-up"></i>
          </button>
        </th>
        <th>Số lượng</th>
        <th>Ngày đăng</th>
        <th>Tên người bán</th>
        <th>Loại</th>
        <th class="noExl">
          <div class="text-center">Chức năng</div>
        </th>
      </tr>
    </thead>
    <tbody id="listSanPham"></tbody>
  </table>
  <div id="phanTrangSanPham" class="d-flex justify-content-center"></div>
</div>

<script>
  document.getElementById('aside-kiem-duyet-san-pham').classList.add('active');
  document.getElementById('aside-kiem-duyet').classList.add('menu-is-opening', 'menu-open');
</script>

<script src='<c:url value="/js/admin/kiemduyet/sanpham/index.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />