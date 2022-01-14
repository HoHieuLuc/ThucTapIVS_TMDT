<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<div class="row categoryDiv">
    <div class="col-md-3 col-lg-2 col-xl-2 d-none d-sm-none d-md-block">
        <h3 class="text-center">Phân loại</h3>
        <div id="sideCategory">

        </div>
    </div>

    <div class="col-12 col-sm-12 col-md-9 col-lg-10 col-xl-10">
        <form class="searchForm input-group mb-3 d-none">
            <input name="search" type="text" class="form-control w-50" placeholder="Tìm kiếm">
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
        <div id="mainCategory" class="row">

        </div>
        <div class="phanTrang d-flex justify-content-center"></div>
    </div>
</div>

<script src='<c:url value="/js/category/category.js"/>'></script>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />