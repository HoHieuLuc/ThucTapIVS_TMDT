<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./header.jsp" />

<body>
     <div class="container">

        <div class="row">
            <!--Hiển thị tất cả bình luận của khách hàng liên quan đến sản phẩm đó-->
            <div class="col-sm-5 col-md-6 col-12 pb-4" id="danhGiaSPListDom">
                <h1>Bình luận</h1>
                <div class="comment mt-4 text-justify float-left"> <img src="https://i.imgur.com/yTFUilP.jpg"
                        alt="avatar" class="rounded-circle" width="40" height="40">
                    <h4>Jhon Doe</h4> <span>- 20 October, 2018</span> <br>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star"></span>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Accusamus numquam assumenda hic aliquam
                        vero sequi velit molestias doloremque molestiae dicta?</p>
                </div>
            </div>


            <!--Form nhập bình luận-->
            <div class="col-lg-4 col-md-5 col-sm-4 offset-md-1 offset-sm-1 col-12 mt-4">
                <form id="algin-form">
                    <div class="form-group">
                        <h4>Để lại bình luận</h4> <br/>
                        <label for="message">Nội dung</label> <textarea name="msg" id=""
                            cols="30" rows="5" class="form-control"></textarea>
                    </div>
                    <div class="form-group"> <label for="name">Tên</label> <input type="text" name="name" id="fullname"
                            disabled="disabled" class="form-control" placeholder="Lâm Minh Thiện"> </div>
                    <div class="form-group"> <label for="email">Email</label> <input type="text" name="email" id="email"
                            class="form-control"> </div>
                    <div class="form-inline"> <input type="checkbox" name="check" id="checkbx" class="mr-1"> <label
                            for="subscribe">Subscribe me to the newlettter</label> </div>
                    <div class="form-inline">
                        <label for="cars">Số sao</label>
                        <select id="cars">
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="5">5</option>
                        </select>
                    </div>
                    <div class="form-group"> <button type="button" id="post" class="btn btn-success">Đăng bình
                            luận</button> </div>
                </form>
            </div>
        </div>
    </div>
</body>

<script src='<c:url value="/js/khachHang/danhGiaSanPham.js"/>'></script>
<jsp:include page="./footer.jsp" />