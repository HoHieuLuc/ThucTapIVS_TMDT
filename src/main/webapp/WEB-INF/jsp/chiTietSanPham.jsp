<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <jsp:include page="./header.jsp" />

        <body>
            <div class="container">
                <!-- Chi tiết sản phẩm -->
                <div class="card">
                    <div class="container-fliud">
                        <div class="wrapper row">
                            <div class="preview col-md-6">
                                <div class="preview-pic tab-content">
                                    <div class="tab-pane active" id="pic-1"><img
                                            src="https://product.hstatic.net/1000379579/product/bbia-last-velvet-lip-tint-ver-5-bici-cosmetic6-min_6f6e5c698a3348ae8a0b7fa515f50c5f_master.jpg">
                                    </div>
                                    <div class="tab-pane" id="pic-2"><img
                                            src="https://product.hstatic.net/1000379579/product/bbia-last-velvet-lip-tint-ver-5-bici-cosmetic1-min_b2665c723a3342e4940cbdfd578c54d0_master.jpg">
                                    </div>
                                    <div class="tab-pane" id="pic-3"><img
                                            src="https://product.hstatic.net/1000379579/product/006pcvrpgy1fvrgeb1xnuj30u00u0ace_236b267334b74cdfad7c38a504ec135e_master.jpg">
                                    </div>
                                    <div class="tab-pane" id="pic-4"><img
                                            src="https://product.hstatic.net/1000379579/product/bbia-last-velvet-lip-tint-ver-5-bici-cosmetic13-min_a5e3985ce9694933a0eac3d78317de02_master.jpg">
                                    </div>
                                    <div class="tab-pane" id="pic-5"><img
                                            src="https://product.hstatic.net/1000379579/product/bbia-last-velvet-lip-tint-ver-5-bici-cosmetic12-min_ce96926aecd54249bbfca1fc56b69cc2_master.jpg">
                                    </div>
                                </div>
                                <ul class="preview-thumbnail nav nav-tabs">
                                    <li class="active"><a data-target="#pic-1" data-toggle="tab"><img
                                                src="https://product.hstatic.net/1000379579/product/bbia-last-velvet-lip-tint-ver-5-bici-cosmetic6-min_6f6e5c698a3348ae8a0b7fa515f50c5f_master.jpg"></a>
                                    </li>
                                    <li><a data-target="#pic-2" data-toggle="tab"><img
                                                src="https://product.hstatic.net/1000379579/product/bbia-last-velvet-lip-tint-ver-5-bici-cosmetic1-min_b2665c723a3342e4940cbdfd578c54d0_master.jpg"></a>
                                    </li>
                                    <li><a data-target="#pic-3" data-toggle="tab"><img
                                                src="https://product.hstatic.net/1000379579/product/006pcvrpgy1fvrgeb1xnuj30u00u0ace_236b267334b74cdfad7c38a504ec135e_master.jpg"></a>
                                    </li>
                                    <li><a data-target="#pic-4" data-toggle="tab"><img
                                                src="https://product.hstatic.net/1000379579/product/bbia-last-velvet-lip-tint-ver-5-bici-cosmetic13-min_a5e3985ce9694933a0eac3d78317de02_master.jpg"></a>
                                    </li>
                                    <li><a data-target="#pic-5" data-toggle="tab"><img
                                                src="https://product.hstatic.net/1000379579/product/bbia-last-velvet-lip-tint-ver-5-bici-cosmetic12-min_ce96926aecd54249bbfca1fc56b69cc2_master.jpg"></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="details col-md-6">
                                <h3 class="product-title" id="tenSanPham">Son kem lì BBIA Last Velvet Lip Tint Version 5 (màu 23 - 24 -
                                    25)</h3>
                                <div class="rating">
                                    <div class="stars"> <span class="fa fa-star checked"></span> <span
                                            class="fa fa-star checked"></span> <span class="fa fa-star checked"></span>
                                        <span class="fa fa-star"></span> <span class="fa fa-star"></span>
                                    </div> <span class="review-no" id="danhGia">123 đánh giá</span>
                                </div>
                                <p class="product-description" id="moTaSanPham">Son kem lì nhà BBIA – BBlA Last Velvet Lip Tint đã trở
                                    lại và lợi hại hơn xưa. Comeback ấn tượng với Bộ sưu tập son mang tên Crystal
                                    Series, BBIA hứa hẹn sẽ tiếp tục làm điên đảo chị em mê son. Đặc biệt là bảng màu
                                    son cực kỳ ấm áp sắc thu, cho bạn hóa thân thành cô nàng \"tóc nâu môi trầm\" hoàn
                                    hảo. Bạn đã sẵn sàng khuynh đảo mùa thu này với BBIA chưa?</p>
                                <h4 class="price" id="gia">Giá bán: 200.000 đ</h4>
                                <div class="card-footer">
                                    <a href="" target="_blank">
                                        <button type="button" class="btn btn-success">Thêm vào giỏ hàng</button>
                                    </a>
                                    <a href="" target="_blank">
                                        <button type="button" class="btn btn-success float-right">Mua ngay</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Khu vực đánh giá sản phẩm -->
                <div class="row">
                    <!--Form nhập bình luận-->
                    <div class="col-lg-4 col-md-5 col-sm-4 offset-md-1 offset-sm-1 col-12 mt-4">
                        <form id="formDanhGiaSanPham">

                            <div class="form-group"> <label>Username</label> <input type="text" name="ten" id="ten"
                                    disabled="disabled" class="form-control"
                                    placeholder="Lấy từ session scope username">
                                <c:choose>
                                    <c:when test="${sessionScope.loggedIn != null && sessionScope.loggedIn == true}">
                                        Xin chào ${sessionScope.username}
                                    </c:when>
                                    <c:otherwise>
                                        Bạn phải đăng nhập thì mới đánh giá sản phẩm
                                    </c:otherwise>
                                </c:choose>
                            </div>


                            <div class="form-group">
                                <label>Nội dung</label>
                                <textarea name="noiDung" id="" cols="30" rows="5" class="form-control"></textarea>
                            </div>

                            <div class="form-inline">
                                <label>Số sao</label>
                                <select name="soSao">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <button type="submit" id="post" class="btn btn-success">Đăng bình luận</button>
                            </div>
                        </form>
                    </div>


                    <!--Hiển thị tất cả bình luận của khách hàng liên quan đến sản phẩm đó-->
                    <div class="col-sm-12 col-md-12 col-12" id="danhGiaSPListDom">
                        <h1>Bình luận</h1>
                        <div class="comment mt-4 text-justify float-left"> <img src="https://i.imgur.com/yTFUilP.jpg"
                                alt="avatar" class="rounded-circle" width="40" height="40">
                            <h4>Jhon Doe</h4> <span>- 20 October, 2018</span> <br>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                            <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Accusamus numquam assumenda hic
                                aliquam
                                vero sequi velit molestias doloremque molestiae dicta?</p>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        <script src='<c:url value="/js/khachHang/chiTietSanPham.js"/>'></script>
        <jsp:include page="./footer.jsp" />