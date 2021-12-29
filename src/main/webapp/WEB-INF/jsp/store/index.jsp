<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<style>
	#avatar{
		width: 15rem;
		height: 15rem;
	}
</style>
<div class="container"> 
	<div class="row mb-5 bg-light rounded">
		<div class="col-md-3">
			<div class="text-center">
				<img src="https://thumbs.dreamstime.com/b/default-avatar-profile-icon-vector-social-media-user-portrait-176256935.jpg" alt="avatar" id="avatar" class="my-auto border border-3 border-white rounded-circle">
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
			<div class="input-group mb-3">
				<input type="text" class="form-control w-50" placeholder="Tìm 1 mặt hàng">
				<select class="form-select">
					<option>Sắp xếp</option>
					<option>aaaaaaaaaaa</option>
					<option>bbbbbbbbbbb</option>
					<option>ccccccccccc</option>
				</select>
				<button class="btn btn-outline-secondary" type="button">Tìm kiếm</button>
			</div>
			<div>
				các mặt hàng
			
			</div>
		</div>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script src='<c:url value="/js/store/store.js"/>'></script>


<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />