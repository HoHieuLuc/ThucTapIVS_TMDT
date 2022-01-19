<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />
<script src="https://cdn.ckeditor.com/ckeditor5/31.1.0/classic/ckeditor.js"></script>

<form id="editSanPhamForm" enctype="multipart/form-data">      
    <div class="row">
        <div class="mb-1">
            <label class="">Tên sản phẩm</label>
            <input type="text" class="form-control rounded" name="tenSanPham">
        </div>
        <div class="mb-1">
            <label class="">Mô tả</label>
            <textarea type="text" style="height: 15em" class="form-control rounded" name="moTa"></textarea>
        </div>
        <div class="mb-1">
            <label class="">Giá</label>
            <input type="text" class="form-control rounded" name="gia">
        </div>
        <div class="mb-1">
            <label class="">Số lượng</label>
            <input type="text" class="form-control rounded" name="soLuong">
        </div>
    </div>
    <div class="mb-1">
        <input type="submit" class="btn btn-outline-dark" value="Sửa">
    </div>
</form>

<script src='<c:url value="/js/user/khohang/edit.js"/>'></script>
<script>
    document.getElementById('aside-san-pham').classList.add('active');
    document.getElementById('aside-kho-hang').classList.add('menu-is-opening', 'menu-open');
</script>
<jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
