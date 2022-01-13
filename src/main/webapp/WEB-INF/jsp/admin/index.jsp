<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <jsp:include page="/WEB-INF/jsp/admin/include/header.jsp" />
  <%-- loggedIn ${sessionScope.loggedIn}<br>
    username ${sessionScope.username}<br>
    tên ${sessionScope.ten}<br>
    accountID ${sessionScope.accountID}<br>
    permission ${sessionScope.permission}<br>
    mã người dùng ${sessionScope.maNguoiDung}<br>
    cấp độ ${sessionScope.level}<br>
    avatar ${sessionScope.avatar}<br> --%>

    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-info">
              <div class="inner">
                <h3 id="soDonHang">Loading...</h3>

                <p>Đơn Hàng</p>
              </div>
              <div class="icon">
                <i class="fas fa-shopping-cart"></i>
              </div>

            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-success">
              <div class="inner">
                <h3 id="soSanPham">Loading..</h3>

                <p>Sản phẩm</p>
              </div>
              <div class="icon">
                <i class="fas fa-shopping-bag"></i>
              </div>

            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-warning">
              <div class="inner">
                <h3 id="soNguoiDung">Loading...</h3>

                <p>Người Dùng</p>
              </div>
              <div class="icon">
                <i class="fas fa-users"></i>
              </div>

            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-primary">
              <div class="inner">
                <h3 id="soDanhGia">Loading...</h3>

                <p>Đánh Giá</p>
              </div>
              <div class="icon">
                <i class="fas fa-star"></i>
              </div>

            </div>
          </div>
          <!-- ./col -->
        </div>
        <!-- /.row -->
        <!-- Main row -->
        <div class="row">
          <!-- Left col -->
          <section class="col-lg-7 connectedSortable ui-sortable">
            <!-- Demo trải nghiệm sơ đồ pie -->
            <div class="card">
              <div>
                <div class="row g-3">
                  <div class="col-auto">
                    <select class="tuyChonThongKe form-select">
                      <option value="all" selected="">Toàn bộ thời gian</option>
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
              <canvas id="pieChart" width="400" height="400"></canvas>
            </div>
          </section>
          <!-- /.Left col -->
          <!-- right col (We are only adding the ID to make the widgets sortable)-->
          <section class="col-lg-5 connectedSortable ui-sortable">
            <!-- /.card -->
          </section>

        </div>
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>


    <script>
      document.getElementById('dashboard').classList.add('active');
    </script>
    <jsp:include page="/WEB-INF/jsp/admin/include/footer.jsp" />