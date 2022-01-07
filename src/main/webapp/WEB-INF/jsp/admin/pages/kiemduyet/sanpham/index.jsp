<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />
    <style>
    .inline-gallery-container {
        padding-bottom: 30%;
    }
    .lg-backdrop {
        background-color: white !important;
    }
    .lg-components{
        background-color: white !important;
    }
    .lg-icon{
        background-color: transparent !important;
        color: black !important;
    }
    .lg-thumb-outer{
        background-color: white !important;
    }
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/css/lightgallery-bundle.min.css" />
<div class="container mb-3">
    <!-- Chi tiết sản phẩm -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 border border-dark inline-gallery-container" id="anhSanPham">
            </div>
            <div class="col-md-6 position-relative">
                <div class="row">
                    <div class="col-4 border border-dark"><b>Tên Sản phẩm</b></div>
                    <div class="col-8 border border-dark"> <p class="" id="tenSanPham"></p></div>
                    <div class="col-4 border border-dark"><b>Người Đăng</b></div>
                    <div class="col-8 border border-dark"> <p class="" id="nguoiDangSanPham"></p></div>
                    <div class="col-4 border border-dark"><b>Mô Tả</b></div>
                    <div class="col-8 border border-dark" > 
                    <p class="" id="moTaSanPham" style="max-height: 4rem;
                                                white-space: pre-line;
                                                overflow-y: scroll;
                                                display: block;"></p></div>
                    <div class="col-4 border border-dark"><b>Giá</b></div>
                    <div class="col-8 border border-dark"> <p class="" id="gia"></p></div>
                    <div class="col-4 border border-dark"><b>Trạng Thái</b></div>
                    <div class="col-8 border border-dark"> <p class="" id="status"></p></div>
                    <div class="col-6 pt-3" id="chucNang">   
                    </div>
            </div>
        </div>
    </div>
</div>



<!--Js chính của trang này -->
    <script src='<c:url value="/js/admin/kiemduyet/chitietsanpham.js"/>'></script>
<!-- JS thư viện ảnh sideshow -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/lightgallery.min.js"></script>
<!-- lightgallery plugins -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/plugins/thumbnail/lg-thumbnail.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/plugins/zoom/lg-zoom.min.js"></script>
   
   
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />