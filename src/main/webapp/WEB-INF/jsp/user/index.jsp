<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/jsp/user/include/header.jsp" />
    <!--Khung Giao diện Dashboard -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
          <div class="col-lg-4 col-6">
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
          <div class="col-lg-4 col-6">
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
          <div class="col-lg-4 col-6">
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
            <div>
              <div class="row g-3">
                <label class="col-auto col-form-label">Sơ đồ thống kê</label>
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
            <!-- Demo trải nghiệm sơ đồ pie -->
            <div class="card">
              <canvas id="pieChart" width="400" height="400"></canvas>
            </div>
          </section>
          <!-- /.Left col -->
          <!-- right col (We are only adding the ID to make the widgets sortable)-->
          <section class="col-lg-5 connectedSortable ui-sortable">
            <!--Số lượng đơn đặt hàng -->
            <div class="card">
              <div class="text-center fs-5">
                Số lượng đơn đặt hàng
              </div>
              <canvas class="soDonDatHangChart">

              </canvas>
            </div>
            <!-- Top Sản phẩm bán chạy -->
            <div class="card" style="display: block;">
              <div class="card-header bg-success">
                <h3 class="card-title">Top 10 sản phẩm bán chạy</h3>

                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                  <button type="button" class="btn btn-tool" data-card-widget="remove">
                    <i class="fas fa-times"></i>
                  </button>
                </div>
              </div>


              <div class="card-body p-0" style="display: block;">
                <ul class="products-list product-list-in-card pl-2 pr-2" id="topSanPhamBanChay">
                    <!-- Danh sách sản phẩm bán chạy -->
                  <!-- /.item -->
                </ul>
              </div>
            </div>
            <!-- Kết thúc top sản phẩm bán chạy -->
          </section>

        </div>
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>

    <script>
      document.getElementById('dashboard').classList.add('active');
    </script>
    <script src='<c:url value="/js/user/dashboard/thongke.js"/>'></script>
    <jsp:include page="/WEB-INF/jsp/user/include/footer.jsp" />