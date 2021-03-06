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
<!-- Script Xuất table sang excel -->
<script src="https://cdn.rawgit.com/rainabba/jquery-table2excel/1.1.0/dist/jquery.table2excel.min.js"></script>
<!--Vì jquery load cuối cùng nên cái hàm này cũng để ở cuối luôn,ô yên tâm mấy trang  khác trong admin ko có lỗi -->
 <script>
   jQuery(document).ready(function () {

            $('#export-btn').on('click', function (e) {
                e.preventDefault();
                ResultsToTable();
            });

            function ResultsToTable() {
                $("table").table2excel({
                    exclude: ".noExl",
                    name: "Results",
                    filename: $("table").attr("filename")
                });
            }
        });
 </script>


</body>
</html>
