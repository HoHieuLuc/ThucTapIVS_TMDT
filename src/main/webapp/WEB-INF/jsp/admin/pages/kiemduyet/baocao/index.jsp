<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />

<div>
    <form class="searchForm input-group mb-3">
      <input name="search" type="text" class="form-control w-50" placeholder="Tìm kiếm">
      <select id="listBaoCaoByStatus" name="status" class="form-select">
        <option value="0" selected>Chưa duyệt</option>
        <option value="-1">Vi Phạm</option>
        <option value="1">Không vi phạm</option>
      </select>
      <input type="submit" class="btn btn-outline-secondary" value="Tìm kiếm">
    </form>
  </div>
<table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>Mã Báo Cáo</th>
                <th>Username người gửi</th>
                <th>Username người nhận</th>
                <th>Ngày tạo</th>
                <th>Nội dung</th>
                <th><div class="text-center">Chức năng</div></th>
            </tr>
        </thead>
        <tbody id="baocao-list">
                </tbody>
    </table>

<script>
    document.getElementById('aside-kiem-duyet-bao-cao').classList.add('active');
    document.getElementById('aside-kiem-duyet').classList.add('menu-is-opening', 'menu-open');

//Sẽ chuyển nó qua file js sau
const statusButtonDOM = document.querySelector('#listBaoCaoByStatus');
const listBaoCaoDOM = document.querySelector('#baocao-list');
//Bắt sự kiện thẻ select
statusButtonDOM.addEventListener('change',(event) => {
    const status = statusButtonDOM.value;
    const data = "Data from axios " + status;
    renderData(data);
});

//Render data
const renderData = (data) => {
    listBaoCaoDOM.innerHTML = data;
}

//Show list báo cáo lần đầu tiên
const showListBaoCao = () => {
    renderData("Data from axios " + statusButtonDOM.value);
}

showListBaoCao();

</script>
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />
