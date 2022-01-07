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
                <h3 class="" id="tenSanPham"></h3>
                Cửa hàng <a href="" class="" id="nguoiDangSanPham"></a>
                <p class="tlt-description" id="moTaSanPham"></p>
                <h4 id="gia"></h4>
                <p class="tlt-description" id="status"></p>
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