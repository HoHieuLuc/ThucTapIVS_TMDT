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
            <div class="col-md-6 border border-dark  inline-gallery-container" id="anhSanPham">
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-4">
                        <b>Tên Sản phẩm</b>
                    </div>
                    <div class="col-8">
                        <p id="tenSanPham"></p>
                    </div>
                    <div class="col-4">
                        <b>Người đăng</b></div>
                    <div class="col-8">
                        <p id="nguoiDangSanPham"></p>
                    </div>
                    <div class="col-4">
                        <b>Giá</b>
                    </div>
                    <div class="col-8">
                        <p id="gia"></p>
                    </div>
                    <div class="col-4">
                        <b>Trạng thái</b>
                    </div>
                    <div class="col-8">
                        <p id="status"></p>
                    </div>
                    <div class="col-6 pt-3" id="chucNang">   
                    </div>
                </div>
            </div>
            <h3>Mô tả:</h3>
            <div id="moTaSanPham" style="white-space: pre-line;" class="col-8">
            </div>
        </div>
    </div>
</div>


<script>
    document.getElementById('aside-kiem-duyet-san-pham').classList.add('active');
    document.getElementById('aside-kiem-duyet').classList.add('menu-is-opening', 'menu-open');
</script>

<!-- JS thư viện ảnh sideshow -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/lightgallery.min.js"></script>
<!-- lightgallery plugins -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/plugins/thumbnail/lg-thumbnail.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/plugins/zoom/lg-zoom.min.js"></script>
<!--Js chính của trang này -->
<script src='<c:url value="/js/admin/kiemduyet/sanpham/detail.js"/>'></script>
   
   
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />