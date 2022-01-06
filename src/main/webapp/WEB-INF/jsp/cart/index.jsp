<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

<div class="container mt-3">
	<div class="row">
		<div id="gioHangList" class="col-9">

        </div>
        <div class="col-3">
			<div class="container border border-1">
				thanh toán
			</div>
		</div>
	</div>
</div>

<script src='<c:url value="/js/cart/cart.js"/>'></script>

<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />