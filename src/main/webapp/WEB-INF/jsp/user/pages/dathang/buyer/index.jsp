<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />

<div class="d-flex justify-content-center fs-3">
    Đơn hàng của bạn
</div>
<div>
    <form class="searchForm input-group mb-3">
        <input name="search" type="text" class="form-control w-50" placeholder="Tìm kiếm">
        <select name="status" class="form-select">
            <option value="-1">Bị hủy</option>
            <option value="0" selected>Đang chờ</option>
            <option value="1">Đang vận chuyển</option>
            <option value="2">Đã nhận hàng</option>
        </select>
        <input type="submit" class="btn btn-outline-secondary" type="button" value="Tìm kiếm">
    </form>
</div>
<div class="mb-3">
    <table class="tlt-fixed-table table table-striped table-hover">
        <thead>
            <tr>
                <th>Tên sản phẩm</th>
                <th>Người bán</th>
                <th>Ngày đặt</th>
                <th>Số lượng</th>
                <th>Tổng tiền</th>
                <th>Tình trạng</th>
                <th><div class="text-center">Chức năng</div></th>
            </tr>
        </thead>
        <tbody id="datHangList"></tbody>
    </table>
    <div id="pagination" class="d-flex justify-content-center"></div>
</div>

<script>
    document.getElementById('aside-buyer-dat-hang').classList.add('active');
    document.getElementById('aside-menu-dat-hang').classList.add('menu-is-opening', 'menu-open');
</script>
<script src='<c:url value="/js/user/dathang/buyer/index.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
