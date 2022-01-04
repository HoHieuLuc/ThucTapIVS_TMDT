<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <jsp:include page="/WEB-INF/jsp/include/header.jsp" />
            <style>
                .inline-gallery-container {
                    padding-bottom: 30%;
                }
                .lg-backdrop {
                    background-color: white !important;
                }
                .lg-components{
                    background-color: white !important;
                }
                .lg-icon{
                    background-color: transparent !important;
                    color: black !important;
                }
                .lg-thumb-outer{
                    background-color: white !important;
                }
            </style>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/css/lightgallery-bundle.min.css" integrity="sha512-91yJwfiGTCo9TM74ZzlAIAN4Eh5EWHpQJUfvo/XhpH6lzQtiRFkFRW1W+JSg4ch4XW3/xzh+dY4TOw/ILpavQA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
            <div class="container mb-3">
                <!-- Chi tiết sản phẩm -->
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-6 border border-dark inline-gallery-container" id="anhSanPham">
                        </div>
                        <div class="col-md-6 position-relative">
                            <h3 class="" id="tenSanPham"></h3>
                            Cửa hàng <a href="" class="" id="nguoiDangSanPham"></a>
                            <div class="rating"></div>
                            Mức đánh giá trung bình của người dùng: <span id="danhGia"></span>
                            <p class="tlt-description" id="moTaSanPham"></p>
                            <h4 id="gia"></h4>
                            <div class="d-block tlt-bottom-of-div">
                                <button type="button" id="addToCartBtn" class="add-to-cart-btn mb-3 w-75 btn btn-warning">
                                    Thêm vào giỏ hàng <i class="fas fa-cart-plus"></i>
                                </button>
                                <button type="button" id="addToFavBtn" class="add-to-fav-btn w-75 btn btn-danger">
                                    Thêm vào mục yêu tích <i class="far fa-heart"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Khu vực đánh giá sản phẩm -->
            <div class="row w-50">
                <!--Form nhập bình luận-->
                <div class="col-12">
                    <c:choose>
                        <c:when test="${sessionScope.level == 0}">
                            <form id="formDanhGiaSanPham">
                                <div class="form-floating mb-1">
                                    <textarea name="noiDung" style="height: 100px;" id="noiDung"
                                        class="form-control" placeholder="2 ký tự trở lên"></textarea>
                                    <label for="noiDung">Nội dung</label>
                                </div>

                                <div class="mb-1">
                                    <label for="soSao">Số sao</label>
                                    <select class="rounded" name="soSao" id="soSao">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                </div>

                                <div class="d-flex gap-2">
                                    <button type="button" id="huyDanhGiaBtn"
                                        class="d-none btn btn-block btn-outline-danger w-100">Hủy</button>
                                    <input type="submit" id="danhGiaBtn" class="btn btn-block btn-outline-success w-100" value="Đánh giá">
                                </div>
                            </form>
                        </c:when>
                        <c:when test="${sessionScope.level > 0}">
                            Bạn không phải là khách hàng
                        </c:when>
                        <c:otherwise>
                            Bạn phải <a id="loginDanhGia" href='<c:url value="/login"/>'>Đăng nhập</a> thì mới được đánh giá sản phẩm
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-12" id="danhGiaSPListDom">
                </div>
            </div>
            </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/lightgallery.min.js" integrity="sha512-0JtOysnU+g+PMlwAGKzc0vFjiDb0p9/OhzFib1lbuHn6BCdO+RSyxDN53UndKL8pNjlDhD0PIEsoVwl0i8W4lQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- lightgallery plugins -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/plugins/thumbnail/lg-thumbnail.min.js" integrity="sha512-Rr8hj+MAzJfjZ1cLsOq0PRKDS6xdEzl5ZcPSte0Emt8orLpPhU4PE7hitAZAm08d2TZK1Un0EWheK/n16inGEA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/2.4.0-beta.0/plugins/zoom/lg-zoom.min.js" integrity="sha512-UZbEchOpPFjh+sg/r2ly17+8K6uTlye0Dfox3+GOHjNguTxeOGKuKYeE/hNSdSbZ4+Uq0RHpfbK+QeaJw7eXRQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>


        <script src='<c:url value="/js/product/sanpham.js"/>'></script>

        <jsp:include page="/WEB-INF/jsp/include/footer.jsp" />