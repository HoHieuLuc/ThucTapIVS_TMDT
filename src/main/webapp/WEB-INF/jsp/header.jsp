<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Home</title>
      <!-- Bootstrap and Fontawesome css-->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
      <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css">
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
      Đây là header
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">Navbar</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href='<c:url value="/" />'>Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                  aria-expanded="false">
                  Dropdown
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li><a class="dropdown-item" href="#">Action</a></li>
                  <li><a class="dropdown-item" href="#">Another action</a></li>
                  <li>
                    <hr class="dropdown-divider">
                  </li>
                  <li><a class="dropdown-item" href="#">Something else here</a></li>
                </ul>
              </li>
              <c:choose>
                <c:when test="${sessionScope.loggedIn != null && sessionScope.loggedIn == true}">
                  <li class="nav-item">
                    <a class="nav-link disabled">Xin chào ${sessionScope.ten}</a>
                  </li>
                  <c:choose>
                    <c:when test="${sessionScope.level > 0}">
                      <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/admin"/>'>Trang quản lý</a>
                      </li>
                    </c:when>
                    <c:otherwise>
                      <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/user"/>'>Trang cá nhân</a>
                      </li>
                    </c:otherwise>
                  </c:choose>
                  <li class="nav-item">
                    <a class="nav-link" href='<c:url value="/logout"/>'>Đăng xuất</a>
                  </li>
                </c:when>
                <c:otherwise>
                  <li class="nav-item">
                    <a class="nav-link" href='<c:url value="/login"/>'>Đăng nhập</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href='<c:url value="/register"/>'>Đăng ký</a>
                  </li>
                </c:otherwise>
              </c:choose>
            </ul>
            <form class="d-flex">
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
          </div>
        </div>
      </nav>

      <hr>

      </div>
      </nav>



      <!-- Đừng thêm gì dưới đoạn này -->
      <main role="main" class="container">