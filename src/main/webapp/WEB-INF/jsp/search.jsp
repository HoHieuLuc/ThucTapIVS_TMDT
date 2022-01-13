<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />


<div class="container-fluid">
    <jsp:include page="/WEB-INF/jsp/include/searchbar.jsp" />
    <div class="row">
        <div class="col-md-3 col-lg-3 col-xl-2 d-lg-block d-xl-block d-md-none d-sm-none d-none">
            <div class="text-center fs-5">Bộ lọc</div>
            <form class="boLocForm form-group">
                <p>Giá</p>
                <div class="d-flex mb-1">
                    <input type="text" class="form-control" name="minPrice" placeholder="Từ" />
                    <input type="text" class="form-control" name="maxPrice" placeholder="Đến" />
                </div>
                <button type="button" class="chinh-gia btn w-100 btn-outline-secondary">
                    <i class="fas fa-angle-double-right"></i>
                </button>
                <label>Ngày đăng</label>
                <div class="form-check">
                    <label class="form-check-label">
                        <input class="order-check-input form-check-input" value="date-desc" name="order" type="radio">
                        Mới nhất
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                        <input class="order-check-input form-check-input" value="date-asc" name="order" type="radio">
                        Cũ nhất
                    </label>
                </div>
                <label>Xếp hạng</label>
                <div class="form-check">
                    <label class="form-check-label">
                        <input class="order-check-input form-check-input" value="rating-desc" name="order" type="radio">
                        Đánh giá cao nhất
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                        <input class="order-check-input form-check-input" value="rating-asc" name="order" type="radio">
                        Đánh giá thấp nhất
                    </label>
                </div>
                <button type="button" class="reset-form btn w-100 btn-outline-secondary">
                    Reset <i class="fas fa-undo"></i>
                </button>
            </form>
        </div>
        <div class="col-md-9 col-lg-9 col-xl-10">
            <div class="sanPhamBrowser row">

            </div>
            <div class="phanTrangSearchSanPham d-flex justify-content-center"></div>
        </div>
    </div>
</div>


<script src='<c:url value="/js/product/sanpham-browser.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />