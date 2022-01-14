<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form class="tlt-main-search-form container input-group mb-3 mt-3">
    <label class="border text-center text-decoration-none fs-5 fw-bold border-0 form-control">
        <a class="text-decoration-none text-dark" href='<c:url value="/search"/>'>TMDT-TLT</a>
    </label>
    <input name="q" type="text" class="form-control w-50" placeholder="Tìm kiếm">
    <select name="cat" class="loaiSanPham tlt-overflow-ellipsis form-select">
        <option value="">Tất cả</option>
    </select>
    <input type="submit" class="btn btn-outline-secondary" type="button" value="Tìm kiếm">
</form>