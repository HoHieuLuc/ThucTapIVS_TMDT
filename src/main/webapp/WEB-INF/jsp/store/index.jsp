<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<style>
	.store-product-img {
		object-fit: cover;
		height: 10rem;
		width: 100%;
	}
	.store-product-img-link:hover {
		border: solid 1px gray;
	}
	#avatar{
		width: 15rem;
		height: 15rem;
	}
</style>
<div class="container"> 
	<div class="row mb-5 bg-light rounded">
		<div class="col-md-3">
			<div>
				<img 
					src="https://thumbs.dreamstime.com/b/default-avatar-profile-icon-vector-social-media-user-portrait-176256935.jpg" 
					alt="avatar" 
					id="avatar" 
					class="d-block img-fluid border border-3 border-white rounded-circle"
				>
			</div>
		</div>
		<div class="col-md-9">
			<div class="d-flex">
				<div id="link" class="ms-auto"></div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<h3 id="tenNguoiBan"></h3>
					<div class="row">
						<div class="col-md-6">Ngày tham gia: <span id="ngayThamGia"></span></div>
						<div class="col-md-6">Số điện thoại: <span id="soDienThoai"></span></div>
						<div class="col-md-12">Email: <span id="email"></span></div>
						<div class="col-md-12">Địa chỉ: <span id="diaChi"></span></div>
					</div>
					<div><span id="gioiThieu" class="fst-italic" style="white-space: pre-line;"></span></div>
					<p>Đánh giá: <span id="rating"></span></p>
				</div>
				<div class="col-md-4">
					<p class="fw-bold text-center">Tổng quan đánh giá sản phẩm:</p>
					<canvas id="ratingChart"></canvas>
				</div>
			</div>
		</div>
	</div>

	<div class="row mt-5">
		<div class="col-md-3">
			bộ lọc
		</div>
		<div class="col-md-9">
			<form class="searchForm input-group mb-3">
				<input id="search" type="text" class="form-control w-50" placeholder="Tìm 1 mặt hàng">
				<select id="orderBy" class="form-select">
					<option value="date">Ngày đăng</option>
					<option value="price">Giá</option>
					<option value="rating">Xếp hạng</option>
				</select>
				<select id="order" class="form-select">
					<option value="desc">Giảm dần</option>
					<option value="asc">Tăng dần</option>
				</select>
				<input type="submit" class="btn btn-outline-secondary" type="button" value="Tìm kiếm">
			</form>
			<div class="mb-2">
				Số mặt hàng mỗi trang: 
				<select class="rowsPerPage rounded">
					<option value="10" selected>10</option>
					<option value="20">20</option>
					<option value="30">30</option>
				</select>
			</div>
			<div class="productList container border border-1 rounded">
				
			</div>
			<div class="phanTrangProduct d-flex justify-content-center">

			</div>
		</div>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src='<c:url value="/js/function.js"/>'></script>
<script src='<c:url value="/js/pagination.js"/>'></script>
<script src='<c:url value="/js/store/store.js"/>'></script>


<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />