<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />

<div class="container">
    <form id="formThemLoaiSP" enctype="multipart/form-data">      
        <div class="row">
            <div class="mb-1">
                <label class="">Tên loại sản phẩm</label>
                <input type="text" class="form-control rounded" name="tenLoaiSanPham">
            </div>
            <div class="mb-1">
                <label class="">Loại sản phẩm cha</label>
                <div class="row g-2" id="loaiSanPham">

                </div>
            </div>
            <div class="mb-1">
                <label class="">Chọn ảnh</label>
                <input type="file" class="form-control rounded" name="anhLoaiSanPham">
            </div>
            
            <div class="col-sm-12 form-group mb-0">
                <input type="submit" class="btn btn-primary float-right" input="Thêm"/>
            </div>

        </div>
    </form>
</div>

<script>
    document.getElementById('aside-lsp-them').classList.add('active');
    document.getElementById('aside-lsp').classList.add('menu-is-opening', 'menu-open');
</script>
<script src='<c:url value="/js/admin/loaisanpham/create.js"/>'></script>

<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />
