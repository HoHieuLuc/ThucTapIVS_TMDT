<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Kiểm tra header cho từng đối tượng user hoặc admin --%>
<c:choose>
    <%-- nhân viên --%>
    <c:when test="${sessionScope.level > 0}">
        <jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />
    </c:when>
    <%-- khách hàng--%>
    <c:otherwise>
        <jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />
    </c:otherwise>
</c:choose>
                    
<div class="mb-3">
    <select name="status" class="form-select" id="thongbao-filter">
        <option value="0">Chưa đọc</option>
        <option value="-1" selected>Tất cả</option>
    </select>
</div>

<div id="thongbao-table">

</div>
<div id="pagination" class="d-flex justify-content-center"></div>


<script>
    document.getElementById('aside-thong-bao').classList.add('active');
</script>

<%-- Kiểm tra footer cho từng đối tượng user hoặc admin --%>
<c:choose>
    <%-- nhân viên --%>
    <c:when test="${sessionScope.level > 0}">
        <jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />
    </c:when>
    <%-- khách hàng--%>
    <c:otherwise>
        <jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
    </c:otherwise>
</c:choose>

