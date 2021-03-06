<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />

<div>
    <form class="searchForm input-group mb-3">
      <input name="search" type="text" class="form-control w-50" placeholder="Tìm kiếm">
      <select name="status" class="form-select">
        <option value="-2">Vi phạm nặng nhất</option>
        <option value="-1">Cảnh cáo</option>
        <option value="0" selected>Chưa duyệt</option>
        <option value="1">Nhắc nhở</option>
        <option value="2">Không vi phạm</option>
      </select>
      <input type="submit" class="btn btn-outline-secondary" value="Tìm kiếm">
    </form>
    <div class="row mb-3">
        <div class="col-md-2">
            <a class="btn btn-primary" id="export-btn">Xuất Excel</a>
        </div>    
    </div>
  </div>

<table class="tlt-fixed-table table table-striped table-hover" id="listBaoCaoTable"  filename="Danh sách kiểm duyệt báo cáo.xls">
    <thead>
        <tr>
            <th>Username người gửi</th>
            <th>Username người bị báo cáo</th>
            <th>Ngày tạo</th>
            <th class="noExl"><div class="text-center">Chức năng</div></th>
        </tr>
    </thead>
    <tbody id="baocao-list">
    </tbody>
</table>
<div class="phanTrangBaoCao d-flex justify-content-center"></div>

<script>
    document.getElementById('aside-kiem-duyet-bao-cao').classList.add('active');
    document.getElementById('aside-kiem-duyet').classList.add('menu-is-opening', 'menu-open');
</script>
    <!--Js chính của trang này -->
<script src='<c:url value="/js/admin/kiemduyet/baocao/index.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />
