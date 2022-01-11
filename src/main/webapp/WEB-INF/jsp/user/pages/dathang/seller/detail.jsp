<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />

<div class="d-flex justify-content-center fs-3">
    Chi tiết đơn đặt hàng
</div>
<div class="thongTinNguoiDat row">
</div>
<hr>
<div class="chiTietDatHang">

</div>

<script>
    document.getElementById('aside-seller-dat-hang').classList.add('active');
    document.getElementById('aside-menu-dat-hang').classList.add('menu-is-opening', 'menu-open');
</script>
<script src='<c:url value="/js/user/dathang/seller/detail.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
