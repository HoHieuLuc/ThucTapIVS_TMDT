<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<style>
  .store-product-img {
    width: 13em;
    height: 13em;
    object-fit: contain;
  }
  .store-product-img-link:hover {
    border: solid 1px gray;
  }
  #avatar {
    object-fit: scale-down;
  }
</style>
<div class="container">
  <div class="row mb-5 bg-light rounded">
    <div class="col-md-3 d-flex">
      <div class="mx-auto my-auto">
        <img src="https://thumbs.dreamstime.com/b/default-avatar-profile-icon-vector-social-media-user-portrait-176256935.jpg" alt="avatar" id="avatar" class="img-fluid border border-3 border-white rounded-circle"/>
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
            <div class="col-md-6">
              Ngày tham gia: <span id="ngayThamGia"></span>
            </div>
            <div class="col-md-6">
              Số điện thoại: <span id="soDienThoai"></span>
            </div>
            <div class="col-md-12">Email: <span id="email"></span></div>
            <div class="col-md-12">Địa chỉ: <span id="diaChi"></span></div>
          </div>
          <div>
            <span id="gioiThieu" class="tlt-description fst-italic"></span>
          </div>
          <p>Đánh giá: <span id="rating"></span></p>
          <c:choose>
            <c:when test="${sessionScope.level == 0}">
              <!-- Báo cáo người dùng -->
              <button type="button" id="baoCaoButton" class="btn btn-danger">Báo Cáo</button>
              <div class="mb-3"></div>
              <div class="mb-3 d-none" id="formBaoCao">
                <label class="form-label">Nội dung báo cáo</label>
                <textarea class="form-control" rows="3" id="noiDungBaoCao"></textarea>
                <button type="submit" class="btn btn-primary">Gửi</button>
                <button class="btn btn-primary ms-2">Đóng</button>
              </div>
           </c:when>
          </c:choose>
        </div>
        <div class="col-md-4">
          <p class="fw-bold text-center">Tổng quan đánh giá sản phẩm:</p>
          <canvas id="ratingChart"></canvas>
        </div>
      </div>
    </div>
  </div>

  <div class="row mt-5">
    <div class="d-lg-block d-md-none col-lg-3">
      <div class="text-center fs-5">Bộ lọc</div>
      <form class="form-group">
        <p>Giá</p>
        <div class="d-flex">
          <input type="number" class="form-control" id="minPrice" placeholder="Từ" />
          <input type="number" class="form-control" id="maxPrice" placeholder="Đến" />
          <button type="button" class="btn btn-outline-secondary">
            <i class="fas fa-angle-double-right"></i>
          </button>
        </div>
      </form>
    </div>
    <div class="col-md-12 col-lg-9">
      <form class="searchForm input-group mb-3">
        <input name="search" id="search" type="text" class="form-control w-50" placeholder="Tìm 1 mặt hàng">
        <select name="orderBy" class="form-select">
          <option value="date">Ngày đăng</option>
          <option value="price">Giá</option>
          <option value="rating">Xếp hạng</option>
        </select>
        <select name="order" class="form-select">
          <option value="desc">Giảm dần</option>
          <option value="asc">Tăng dần</option>
        </select>
        <input type="submit" class="btn btn-outline-secondary" type="button" value="Tìm kiếm">
      </form>
      <div class="mb-2">
        Số mặt hàng mỗi trang:
        <select class="rowsPerPage rounded">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="10" selected>10</option>
          <option value="20">20</option>
          <option value="30">30</option>
        </select>
      </div>
      <div class="productList container border border-1 rounded"></div>
      <div class="phanTrangProduct d-flex justify-content-center"></div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src='<c:url value="/js/store/store.js"/>'></script>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
