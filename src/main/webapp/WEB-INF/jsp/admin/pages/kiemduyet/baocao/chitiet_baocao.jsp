<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />

    <div class="container-fluid">
                <div class="row">
                    <div class="col-4 border border-dark"><b>Mã Báo Cáo</b></div>
                    <div class="col-8 border border-dark"> <p class="" id="maBaoCao"></p></div>
                    <div class="col-4 border border-dark"><b>Username người gửi</b></div>
                    <div class="col-8 border border-dark"> <p class="" id="unameSender"></p></div>
                    <div class="col-4 border border-dark"><b>Username người bị cáo</b></div>
                    <div class="col-8 border border-dark"> <p class="" id="unameReceiver"></p></div>
                    <div class="col-4 border border-dark"><b>Nội dung báo cáo</b></div>
                    <div class="col-8 border border-dark" > 
                    <p class="" id="noiDung" style="max-height: 4rem;
                                                white-space: pre-line;
                                                overflow-y: scroll;
                                                display: block;"></p></div>
                    <div class="col-4 border border-dark"><b>Ngày tạo</b></div>
                    <div class="col-8 border border-dark"> <p class="" id="ngayTao"></p></div>
                    <div class="col-6 pt-3" id="chucNang">   
                </div>
    </div>



<script>
    document.getElementById('aside-kiem-duyet-bao-cao').classList.add('active');
    document.getElementById('aside-kiem-duyet').classList.add('menu-is-opening', 'menu-open');
</script>
    <!--Js chính của trang này -->
<script src='<c:url value="/js/admin/kiemduyet/chitiet_baocao.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />
