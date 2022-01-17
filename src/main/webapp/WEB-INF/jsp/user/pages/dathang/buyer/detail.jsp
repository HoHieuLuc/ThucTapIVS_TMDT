<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />

<div class="d-flex justify-content-center fs-3">
    Chi tiết đơn đặt hàng
</div>

<div class="row mb-3">
    <div class="col-md-12 d-flex justify-content-center fs-4">
        Thông tin người bán
    </div>
    <div class="col-md-3">
        <div>
          <img src="" alt="avatar" class="avatar d-block img-fluid rounded-circle"/>
        </div>
    </div>
    <div class="col-md-9">
        <div class="row">
            <div class="col-md-8">
                <h3 class="tenNguoiBan"></h3>
            </div>
            <div class="col-md-12">
                <span class="fw-bold">Số diện thoại: </span> <span class="soDienThoai"></span>
            </div>
            <div class="col-md-12">
                <span class="fw-bold">Email:</span> <span class="email"></span>
            </div>
            <div class="col-md-12">
                <a href="" class="storeLink btn btn-link">Ghé thăm shop<i class="fas fa-external-link-alt"></i></a>
            </div>
        </div>
    </div>
</div>
<div class="row mb-3">
    <div class="col-2">
        <img src="" alt=""
            class="anhSanPham tlt-thumbnail rounded mx-auto d-block img-fluid"
            style="height:11rem; width:11rem; object-fit: scale-down;"
        >
    </div>
    <div class="col-1"></div>
    <div class="col-8">
        <div class="row">
            <div class="col-12">
                <a class="tenSanPham text-decoration-none text-dark fw-bold"></a>
            </div>
            <div class="col-4 fw-bold">Đơn giá</div>
            <div class="donGia col-8"></div>

            <div class="col-4 fw-bold">Ngày đặt</div>
            <div class="ngayDat col-8"></div>

            <div class="col-4 fw-bold">Số lượng đặt</div>
            <div class="soLuongDat col-8"></div>

            <div class="col-4 fw-bold">Thành tiền</div>
            <div class="tongTien col-8"></div>

            <div class="chucNang col-auto"></div>
        </div>
    </div>
</div> 

<script>
    document.getElementById('aside-buyer-dat-hang').classList.add('active');
    document.getElementById('aside-menu-dat-hang').classList.add('menu-is-opening', 'menu-open');
</script>
<script src='<c:url value="/js/user/dathang/buyer/detail.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
