<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Thương mại điện tử TLT</title>
      <!-- Bootstrap and Fontawesome css-->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer">

      <%-- js Axios,JQuery,Popper,Bootstrap js--%>
      <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
      <script>
        const baseURL = <c:url value="/" />;
      </script>
    </head>

    <body>
      <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 99999">
        <div id="toastThongBao" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
          <div class="toast-header text-white" id="toastHeader">
            <strong class="me-auto">Thông báo</strong>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
          </div>
          <div class="toast-body" id="toastNoiDung">

          </div>
        </div>
      </div>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">
            <img src='<c:url value="/images/brand_logo.png" />' alt="" width="50" height="25" class="d-inline-block align-text-top">
          </a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href='<c:url value="/" />'>Trang chủ</a>
              </li>
              <!-- Chỗ này sẽ vào trang list grid 4 ô {(avatar store (avatar người dùng), tên store (tên người dùng/ tên nhãn hiệu)
                  , trung bình số sao đánh giá
                ),(điều kiện câu select ("Chỉ những store có từ 1 sản phẩm trở lên") ) }  -->
              <li class="nav-item">
                <a class="nav-link" href='<c:url value="/store" />'>Cửa hàng</a>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                  aria-expanded="false">
                  Danh mục sản phẩm
                </a>
                <ul class="dropdown-menu" id="danhMucSP">
                  <!--- Chỗ này để hiện ra các danh mục bằng câu select-->
                  <li><a class="dropdown-item" href="#">Điện thoại</a></li>
                </ul>
              </li>
            </ul>
            <form class="d-flex me-auto">
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <ul class="navbar-nav mb-2 mb-lg-0">
              <c:choose>
                <c:when test="${sessionScope.loggedIn != null && sessionScope.loggedIn == true}">
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      <img src='<c:url value="/images/user/${sessionScope.avatar}"/>' style="width:25px; height: 25px;" class="rounded-circle" alt="User Image">
                      Xin chào ${sessionScope.ten}
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="navbarDropdownMenuLink">
                      <c:choose>
                        <%-- nhân viên --%>
                        <c:when test="${sessionScope.level > 0}">
                          <li>
                            <a class="dropdown-item" href='<c:url value="/admin"/>'>Trang quản lý</a>
                          </li>
                        </c:when>
                        <%-- khách hàng --%>
                        <c:otherwise>
                          <li>
                            <a class="dropdown-item" href='<c:url value="/user"/>'>Trang cá nhân</a>
                          </li>
                        </c:otherwise>
                      </c:choose>
                      <li>
                        <hr class="dropdown-divider">
                      </li>
                      <li>
                        <a class="dropdown-item" id="logout" href=''>Đăng xuất</a>
                      </li>
                    </ul>
                  </li>
                  
                </c:when>
                <c:otherwise>
                  <li class="nav-item">
                    <a class="nav-link" id="login" href=''>Đăng nhập</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" id="register" href=''>Đăng ký</a>
                  </li>
                </c:otherwise>
              </c:choose>
            </ul>
          </div>
        </div>
      </nav>

      <hr>

      </div>
      </nav>



      <!-- Đừng thêm gì dưới đoạn này -->
      <main role="main" class="container">