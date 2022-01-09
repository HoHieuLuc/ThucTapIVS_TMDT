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
<table class="table table-striped table-hover" id="listBaoCaoTable">
        <thead>
            <tr>
                <th>Mã Báo Cáo</th>
                <th>Username người gửi</th>
                <th>Username người bị báo cáo</th>
                <th>Ngày tạo</th>
                <th>Nội dung</th>
                <th><div class="text-center">Chức năng</div></th>
            </tr>
        </thead>
        <tbody id="baocao-list">
                </tbody>
    </table>
    <!-- Modal Cung cấp các chức năng duyệt báo cáo -->
  <div class="modal fade show" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-modal="true" role="dialog" style="display: block; padding-left: 0px;">
                <div class="modal-dialog modal-dialog-centered">
                  <form id="formDanhGiaKhachHang" class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="staticBackdropLabel">
                        Chọn hình thức kiểm duyệt báo cáo
                      </h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <select class="form-select" size="5" aria-label="size 3" name="status">
                        <option value="-3">Khóa tài khoản</option>
                        <option value="-2">Cảnh cáo và gửi thông báo</option>
                        <option value="-1" selected="">Gửi thông báo nhắc nhở</option>
                        <option value="1">Bỏ qua</option>
                      </select>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Thoát
                      </button>
                      <input type="submit" class="btn btn-primary" value="Xác nhận">
                    </div>
                  </form>
                </div>
              </div>

<script>
    document.getElementById('aside-kiem-duyet-bao-cao').classList.add('active');
    document.getElementById('aside-kiem-duyet').classList.add('menu-is-opening', 'menu-open');
</script>
    <!--Js chính của trang này -->
<script src='<c:url value="/js/admin/kiemduyet/listbaocao_bystatus.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />
