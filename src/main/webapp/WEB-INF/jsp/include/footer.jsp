<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    </main>
    <hr>
    <!-- (thongbao.js), trigger hiển thị thông báo cho toàn bộ trang
   (index.js: chuyển hướng khi đăng nhập),
    (loaisanpham-browser.js) hiển danh mục loại sản phẩm dropdownmenu trên  navbar
-->
    <script src='<c:url value="/js/thongbao.js"/>'></script>
    <script src='<c:url value="/js/index.js"/>'></script>
    <script src='<c:url value="/js/product/loaisanpham-browser.js"/>'></script>

    <!-- Site footer -->
    <footer class="site-footer">
      <div class="container">
        <div class="row">
          <div class="col-sm-12 col-md-6">
            <h6>Thông tin về chúng tôi</h6>
            <p class="text-justify">Đây là sản phẩm demo của nhóm Thiện, Lực, Thư</p>
          </div>
        </div>
        <hr>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-sm-6 col-xs-12">
            <p class="copyright-text">Copyright &copy; 2021 All Rights Reserved by
              <a href="#">TMDT</a>.
            </p>
          </div>
        </div>
      </div>
    </footer>


    </body>

    </html>