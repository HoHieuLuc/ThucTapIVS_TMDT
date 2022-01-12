<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang quản lý</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Theme style -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/admin-lte@3.1/dist/css/adminlte.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <%-- js --%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src='<c:url value="/js/function.js"/>'></script>
    <script>
        const baseURL = <c:url value="/"/>;
    </script>
</head>
<body>
      <!--Toast Thông báo -->
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
      <script src='<c:url value="/js/thongbao.js"/>'></script>
    <div class="wrapper">

  <!-- Preloader -->
 <%--  <div class="preloader flex-column justify-content-center align-items-center">
    <img class="animation__shake" src="https://via.placeholder.com/15" alt="AdminLTELogo" height="60" width="60">
  </div> --%>

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="index3.html" class="nav-link">Home</a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Contact</a>
      </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
    <%-- nút hiện thông báo nhanh --%>
  <li class="nav-item dropdown me-2">
              <a class="nav-link dropdown position-relative" href="#" id="navbarDropdown" role="button"
                data-bs-toggle="dropdown" aria-expanded="false">
                <i class="far fa-bell"></i>
                <span class="badge position-absolute top-20 start-100 translate-middle  rounded-pill bg-danger" id="soThongBao">
                  0
                </span>
              </a>
              <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
              <div class=" d-flex justify-content-start p-2">
                  <div class="p-1">
                 <h3>   Thông báo gần đây </h3>
                  </div>
                </div>
              <li class="list-group list-group-numbered" id="listThongBao">
                  Danh sách thông báo đang trống
            </li>
            <div class=" d-flex justify-content-end p-2">
              <button type="button" class="btn btn-outline-primary"  id="danhDauDaDoc" >Đánh dấu tất cả đã đọc</button>
            </div>
        </ul>
    <%-- chưa biết để làm gì nhưng sau này chắc cần --%>
      <li class="nav-item">
        <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
          <i class="fas fa-th-large"></i>
        </a>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="" class="brand-link">
      <img src="https://via.placeholder.com/30" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">
          Trang quản lý
      </span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src='<c:url value="/images/user/${sessionScope.avatar}"/>' class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">${sessionScope.ten}</a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item">
            <a href='<c:url value="/admin/"/>' id="dashboard" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Dashboard
              </p>
            </a>        
          </li>
          <li class="nav-item" id="aside-kiem-duyet">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-copy"></i>
              <p>
                Kiểm duyệt
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">6</span>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href='<c:url value="/admin/phe-duyet-san-pham"/>' class="nav-link" id="aside-kiem-duyet-san-pham">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Sản phẩm</p>
                </a>
              </li>
              <li class="nav-item">
                <a href='<c:url value="/admin/phe-duyet-bao-cao"/>' class="nav-link" id="aside-kiem-duyet-bao-cao">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Báo cáo</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item" id="aside-lsp">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-copy"></i>
              <p>
                Loại sản phẩm
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">6</span>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href='<c:url value="/admin/loaisanpham"/>' class="nav-link" id="aside-lsp-list">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Danh sách</p>
                </a>
              </li>
              <li class="nav-item">
                <a href='<c:url value="/admin/loaisanpham/them"/>' class="nav-link" id="aside-lsp-them">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Thêm mới</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
                <a href='<c:url value="/thongbao"/>' class="nav-link" id="aside-thong-bao">
                  <i class="nav-icon fas fa-bell"></i>
                  <p>
                    Thông báo
                  </p>
                </a>
          </li>
          
          <li class="nav-item">
            <a href='<c:url value="/"/>' class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Trang chủ
              </p>
            </a>
          </li>
          <li class="nav-item">
              <a href='<c:url value="/logout"/>' class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Đăng xuất
              </p>
            </a>
          </li>

        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Dashboard</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Dashboard v1</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
      



    <!-- Đừng thêm gì dưới đoạn này -->
    <main role="main" class="container">
    

