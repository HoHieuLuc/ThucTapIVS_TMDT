<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <jsp:include page="/WEB-INF/jsp/header.jsp" />
        <body>
            <div class="container">
                <!-- Chi tiết sản phẩm -->
                <div class="card">
                    <div class="container-fliud">
                        <div class="wrapper row">
                            <div class="preview col-md-6">
                                <div class="preview-pic tab-content">
                                    <div class="tab-pane active" id="pic-1">
                                        <img alt="Ảnh chính" id="anhChinh">
                                    </div>
                                </div>
                            </div>
                            <div class="details col-md-6">
                                <h3 class="product-title" id="tenSanPham"></h3>
                                <h2 class="product-title" id="nguoiDangSanPham"></h3>
                                  <a href="" target="_blank" id="pageNguoiDangSP">
                                        <button type="button" class="btn btn-success float-right">Xem trang người đăng</button>
                                    </a>
                                <div class="rating">
                                    <%-- <div class="stars"> <span class="fas fa-star checked"></span> <span
                                            class="fas fa-star checked"></span> <span class="fa fa-star checked"></span>
                                        <span class="fas fa-star"></span> <span class="fa fa-star"></span> --%>
                                    </div>Mức Đánh giá  trung bình của người dùng: <span class="review-no" id="danhGia"></span>
                                </div>
                                <p class="product-description" id="moTaSanPham"></p>
                                <h4 class="price" id="gia"></h4>
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
                        <c:choose>
                            <c:when test="${sessionScope.level == 0}">    
                                <form id="formDanhGiaSanPham">
                                    <div class="text-danger" id="errorMsg"></div>
                                    <div class="form-group" id="noiDungDanhGiaSP">
                                        <label>Nội dung</label>
                                        <textarea name="noiDung" id="" cols="30" rows="5" class="form-control"></textarea>
                                    </div>

                                    <div class="form-inline">
                                        <label>Số sao</label>
                                        <select name="soSao" id="soSao">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <button type="submit" id="updateOrSubmit" class="btn btn-success">Đăng bình luận</button>
                                    </div>
                                </form>
                            </c:when>
                            <c:when test="${sessionScope.level > 0}">
                                Bạn không phải là khách hàng
                            </c:when>
                            <c:otherwise>
                                Bạn phải <a href='<c:url value="/login"/>'>Đăng nhập</a> thì mới được đánh giá sản phẩm
                            </c:otherwise>
                        </c:choose>
                    </div>


                    
                    <div class="col-sm-12 col-md-12 col-12" id="danhGiaSPListDom">
                    </div>
                </div>
            </div>
        </body>

        <script src='<c:url value="/js/product/sanpham.js"/>'></script>
        <jsp:include page="/WEB-INF/jsp/footer.jsp" />