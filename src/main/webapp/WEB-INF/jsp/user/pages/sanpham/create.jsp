<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />
<div class="container">
    <form id="them-san-pham-form" enctype="multipart/form-data">      
        <div class="row">
            <div class="mb-1">
                <label class="">Tên sản phẩm</label>
                <input type="text" class="form-control rounded" name="tenSanPham">
            </div>
            <div class="mb-1">
                <label class="">Mô tả</label>
                <textarea type="text" class="form-control rounded" name="moTa"></textarea>
            </div>
            <div class="mb-1">
                <label class="">Giá</label>
                <input type="text" class="form-control rounded" name="gia">
            </div>
            <div class="mb-1">
                <label class="">Loại sản phẩm</label>
                <select name="maLoaiSanPham" id="ma-loai-san-pham" class="form-control rounded">

                </select>
            </div>
            <div class="mb-1">
                <label class="">Số lượng</label>
                <input type="text" class="form-control rounded" name="soLuong">
            </div>
            <div class="mb-1">
                <label class="">Chọn ảnh đại diện cho sản phẩm</label>
                <input type="file" class="form-control rounded" name="anhSanPhams">
            </div>
            <div class="mb-1">
                <label class="">Chọn ảnh cho sản phẩm</label>
                <input type="file" class="form-control rounded" multiple="multiple" name="anhSanPhams">
            </div>
            
            <div class="col-sm-12 form-group mb-0">
                <input type="submit" class="btn btn-primary float-right" input="Thêm"/>
            </div>

        </div>
    </form>
</div>

<script src='<c:url value="/js/function.js"/>'></script>
<script src='<c:url value="/js/user/khohang/create.js"/>'></script>
<script>
    document.getElementById('aside-them-san-pham').classList.add('active');
    document.getElementById('aside-kho-hang').classList.add('menu-is-opening', 'menu-open');
</script>
<jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
