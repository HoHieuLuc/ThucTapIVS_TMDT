<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
	nút 4 ô vuông
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<!-- AdminLTE App -->
<script src="https://cdn.jsdelivr.net/npm/admin-lte@3.1/dist/js/adminlte.min.js"></script>
<!-- Script xử lý thông báo -->
<script src='<c:url value="/js/listThongBao.js"/>'></script>
<!-- Script vẽ sơ đồ  -->
<script src="cdn.rawgit.com/rainabba/jquery-table2excel/1.1.0/dist/jquery.table2excel.min.js"></script>
<!-- Table 2 Excel -->

<!-- Cho nó nằm dưới Jquery -->
<script>
    $(document).ready(function() {
      // if $('#export') {

      // }
        $('#export').on('click', function(e){
            $("#table").table2excel({
                exclude: ".noExport",
                name: "Data",
                filename: "Workbook",
            });
        });
    });
</script>

</body>
</html>
