<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/css/lightgallery-bundle.min.css" integrity="sha512-91yJwfiGTCo9TM74ZzlAIAN4Eh5EWHpQJUfvo/XhpH6lzQtiRFkFRW1W+JSg4ch4XW3/xzh+dY4TOw/ILpavQA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<div class="row mb-3">
    <div class="col-6 inline-gallery-container" id="anhSanPham">
    </div>
    <div class="col-6">
        <div>
            Tên sản phẩm: <span id="ten"></span>
        </div>
        <div>
            Loại sản phẩm: <span id="loaiSanPham"></span>
        </div>
        <div>
            Ngày đăng: <span id="ngayDang"></span>
        </div>
        <div>
            Giá: <span id="gia"></span>
        </div>
        <div>
            Xếp hạng: <span id="xepHang"></span>
        </div>
        <div>
            Số lượng: <span id="soLuong"></span>
        </div>
        <div>
            Số lượng đã bán: <span id="soLuongDaBan"></span>
        </div>
        <div>
            Tình trạng: <span id="tinhTrang"></span>
        </div>
    </div>
    <div class="col-12">
        <h3>Mô tả:</h3>
        <div id="moTa" class="tlt-description"></div>
    </div>
    <div class="col-12">
        <div class="d-flex justify-content-center">
            <div class="d-flex w-50 gap-2" id="chucNang">


            </div>
        </div>
    </div>
</div>


<script>
    document.getElementById('aside-san-pham').classList.add('active');
    document.getElementById('aside-kho-hang').classList.add('menu-is-opening', 'menu-open');
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/lightgallery.min.js" integrity="sha512-0JtOysnU+g+PMlwAGKzc0vFjiDb0p9/OhzFib1lbuHn6BCdO+RSyxDN53UndKL8pNjlDhD0PIEsoVwl0i8W4lQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- lightgallery plugins -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/plugins/thumbnail/lg-thumbnail.min.js" integrity="sha512-Rr8hj+MAzJfjZ1cLsOq0PRKDS6xdEzl5ZcPSte0Emt8orLpPhU4PE7hitAZAm08d2TZK1Un0EWheK/n16inGEA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/plugins/zoom/lg-zoom.min.js" integrity="sha512-UZbEchOpPFjh+sg/r2ly17+8K6uTlye0Dfox3+GOHjNguTxeOGKuKYeE/hNSdSbZ4+Uq0RHpfbK+QeaJw7eXRQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src='<c:url value="/js/user/khohang/sanpham.js"/>'></script>

<jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
