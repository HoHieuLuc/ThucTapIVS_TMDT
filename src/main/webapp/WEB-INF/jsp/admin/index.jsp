<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />
loggedIn ${sessionScope.loggedIn}<br>
username ${sessionScope.username}<br>
tên ${sessionScope.ten}<br>
accountID ${sessionScope.accountID}<br>
permission ${sessionScope.permission}<br>
mã người dùng ${sessionScope.maNguoiDung}<br>
cấp độ ${sessionScope.level}<br>
avatar ${sessionScope.avatar}<br>
Dashboard
<script>
    document.getElementById('dashboard').classList.add('active');
</script>
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />