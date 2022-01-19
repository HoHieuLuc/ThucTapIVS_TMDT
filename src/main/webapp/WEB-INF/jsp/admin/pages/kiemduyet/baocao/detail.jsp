<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />

<div class="container-fluid">
    <div class="row">
        <div class="col-4">
            <b>Mã Báo Cáo:</b>
        </div>
        <div class="col-8">
            <p id="maBaoCao"></p>
        </div>
        <div class="col-4">
            <b>Username người báo cáo:</b>
        </div>
        <div class="col-8">
            <p id="unameSender"></p>
        </div>
        <div class="col-4">
            <b>Username người bị báo cáo:</b>
        </div>
        <div class="col-8">
            <p id="unameReceiver"></p>
        </div>
        <div class="col-4"><b>Ngày tạo:</b></div>
        <div class="col-8"><p id="ngayTao"></p></div>
        <div class="col-4"><b>Trạng thái:</b></div>
        <div class="col-8"><p id="status"></p></div>
        <div class="col-12">
            <b>Nội dung:</b>
            <p id="noiDung" style="max-height: 10em; white-space: pre-line; overflow-y: scroll;">
            </p>
        </div>
        <div id="chucNang">
            
        </div>
    </div> 
</div>

<script>
    document.getElementById('aside-kiem-duyet-bao-cao').classList.add('active');
    document.getElementById('aside-kiem-duyet').classList.add('menu-is-opening', 'menu-open');
</script>
    <!--Js chính của trang này -->
<script src='<c:url value="/js/admin/kiemduyet/baocao/detail.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />
