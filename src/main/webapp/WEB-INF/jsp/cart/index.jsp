<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<div class="mt-3">
	<jsp:include page="/WEB-INF/jsp/include/searchbar.jsp" />
	<div class="mainGioHang row">
		<div id="gioHangList" class="col-sm-12 col-md-9 col-lg-9">

        </div>
        <div class="col-sm-12 col-md-3 col-lg-3">
			<div class="container border border-1">
				<a href="./dathang" class="btn btn-primary btn-lg w-100 my-3">Tới trang đặt hàng</a>
				<div class="d-flex justify-content-between mb-3">
					<div>Số sản phẩm</div>
					<div id="tongSoSanPham">0</div>
				</div>
				<div class="d-flex justify-content-between mb-3">
					<div>Tổng tiền</div>
					<div id="tongTien">0</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src='<c:url value="/js/cart/cart.js"/>'></script>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />