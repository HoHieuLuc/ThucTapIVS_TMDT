<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />
<button class="debug-button">reload</button>
<div class="container-fluid mt-3">
    <div>
        <div class="row g-3">
            <label class="col-auto col-form-label">Thống kê</label>
            <div class="col-auto">
                <select class="tuyChonThongKe form-select">
                    <option value="thang" selected>Trong tháng</option>
                    <option value="tuychinh">Tùy chỉnh</option>
                </select>
            </div>
            <form class="formThongKe col-auto d-none">
                <div class="row">
                    <label class="col-auto col-form-label">Từ</label>
                    <div class="col-auto">
                        <input name="tuNgay" type="date" class="form-control">
                    </div>
                    <label class="col-auto col-form-label">đến</label>
                    <div class="col-auto">
                        <input name="denNgay" type="date" class="form-control">
                    </div>
                    <div class="col-auto">
                        <input type="submit" class="form-control" value="Xác nhận">
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="container">
        <div class="text-center fs-5">
            Số lượng đơn đặt hàng
        </div>
        <canvas class="soDonDatHangChart">

        </canvas>
    </div>
</div>

<script>
    document.getElementById('dashboard').classList.add('active');
</script>
<script src='<c:url value="/js/user/dashboard/thongke.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />
