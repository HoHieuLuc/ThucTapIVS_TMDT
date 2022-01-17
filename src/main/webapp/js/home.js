const sanPhamVuaXemDOM = document.querySelector('#sanPhamVuaXem');
const loaiSanPhamPhoBienDOM = document.querySelector('#loaiSanPhamPhoBien');
const storePhoBienDOM = document.querySelector('#storeDuocDanhGiaCao');
const sanPhamMoiDOM = document.querySelector('#sanPhamMoi');

const showLoaiSanPhamPhoBien = async () => {
    try {
        const { data: { popularCategory } } = await axios.get(`${baseURL}api/v1/category/popular`);
        const allPopularCategory = popularCategory.map((category) => {
            const { ma_loai_sp, ten_loai_sp, anh } = category;
            return `
                <div class="col-6 col-sm-3 col-md-3 col-lg-2 mb-3">
                    <a href="${baseURL}category/${ma_loai_sp}" 
                        class="text-decoration-none text-dark text-center" 
                    >
                        <div class="d-flex">
                            <img 
                                src="${baseURL}images/category/${anh}" 
                                class="tlt-thumbnail img-fluid rounded mx-auto my-auto" 
                                alt="${ten_loai_sp}"
                            >
                        </div>
                        <div class="tlt-overflow-ellipsis" title="${ten_loai_sp}">${ten_loai_sp}</div>
                    </a>
                </div>
            `;
        }).join('');
        loaiSanPhamPhoBienDOM.innerHTML = allPopularCategory;
    } catch (error) {
        console.log(error);
    }
}

const showTopStore = async () => {
    try {
        const { data: { topStores } } = await axios.get(`${baseURL}api/v1/store/top`);
        const allTopStores = topStores.map((store) => {
            const { username, ten, avatar, xep_hang } = store;
            return `
                <div class="col-6 col-sm-3 col-md-3 col-lg-2 mb-2">
                    <a href="${baseURL}store/${username}" 
                        class="text-decoration-none text-dark text-center" 
                    >
                        <div class="d-flex">
                            <img 
                                src="${baseURL}images/user/${avatar}" 
                                class="tlt-thumbnail img-fluid rounded my-auto mx-auto" alt="${ten}"
                            >
                        </div>
                        <div class="tlt-overflow-ellipsis" title="${ten}">${ten}</div>
                    </a>
                    <div>${(Math.round(xep_hang * 10) / 10)} &#9733;</div>
                </div>
            `;
        }).join('');
        storePhoBienDOM.innerHTML = allTopStores;
    } catch (error) {
        console.log(error);
    }
}

const showNewestProduct = async () => {
    try {
        const { data: { sanphams } } = await axios.get(`${baseURL}api/v1/sanpham/new`);
        const allNewestProduct = sanphams.map((sanpham) => {
            const { ma_san_pham, ten_san_pham, gia, anh } = sanpham;
            const giaVND = gia.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            return `
                <div class="col-6 col-sm-3 col-md-3 col-lg-2 mb-3">
                    <div class="card">
                        <a href="${baseURL}sanpham/${ma_san_pham}">
                            <div class="d-flex border-bottom">
                                <img 
                                    class="card-img-top img-fluid tlt-thumbnail mx-auto my-auto" 
                                    src="${baseURL}images/product/${anh}"
                                    alt="${ten_san_pham}"
                                >
                            </div>
                        </a>
                        <div class="card-body">
                            <a 
                                href="${baseURL}sanpham/${ma_san_pham}" 
                                class="text-dark tlt-overflow-ellipsis" 
                                title="${ten_san_pham}"
                            >
                                ${ten_san_pham}
                            </a>
                            <div class="d-flex gap-2 justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button 
                                        data-masanpham="${ma_san_pham}" 
                                        class="add-to-cart-btn btn btn-sm btn-outline-secondary"
                                    >
                                        <i class="fas fa-shopping-cart"></i>
                                    </button>
                                    <button 
                                        data-masanpham="${ma_san_pham}"
                                        class="add-to-fav-btn btn btn-sm btn-outline-secondary"
                                    >
                                        <i class="fas fa-heart"></i>
                                    </button>
                                </div>
                                <h6 
                                    title="${giaVND}"
                                    class="fw-bold tlt-overflow-ellipsis text-dark"
                                >
                                    ${giaVND}
                                </h6>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        }).join('');
        sanPhamMoiDOM.innerHTML = allNewestProduct;
    } catch (error) {
        console.log(error);
    }
}

const showRecentlyViewedProduct = () => {
    try {
        const danhSachSanPhamVuaXem = JSON.parse(localStorage.getItem('danhSachSanPhamVuaXem'));
        if (danhSachSanPhamVuaXem) {
            const allRecentlyViewedProduct = danhSachSanPhamVuaXem.map((sanpham) => {
                const { maSanPham, tenSanPham, donGia, hinhAnh } = sanpham;
                if (!maSanPham || !tenSanPham || !donGia || !hinhAnh) {
                    return ``;
                }
                const giaVND = donGia.toLocaleString("vi-VN", {
                    style: "currency",
                    currency: "VND",
                });
                return `
                    <div class="col-6 col-sm-3 col-md-3 col-lg-2 mb-3">
                        <div class="card">
                            <a href="${baseURL}sanpham/${maSanPham}">
                                <div class="d-flex border-bottom">
                                    <img 
                                        class="card-img-top img-fluid tlt-thumbnail mx-auto my-auto" 
                                        src="${baseURL}images/product/${hinhAnh}"
                                        alt="${tenSanPham}"
                                    >
                                </div>
                            </a>
                            <div class="card-body">
                                <a 
                                    href="${baseURL}sanpham/${maSanPham}" 
                                    class="text-dark tlt-overflow-ellipsis" 
                                    title="${tenSanPham}"
                                >
                                    ${tenSanPham}
                                </a>
                                <div class="d-flex gap-2 justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button 
                                            data-masanpham="${maSanPham}" 
                                            class="add-to-cart-btn btn btn-sm btn-outline-secondary"
                                        >
                                            <i class="fas fa-shopping-cart"></i>
                                        </button>
                                        <button 
                                            data-masanpham="${maSanPham}"
                                            class="add-to-fav-btn btn btn-sm btn-outline-secondary"
                                        >
                                            <i class="fas fa-heart"></i>
                                        </button>
                                    </div>
                                    <h6 
                                        title="${giaVND}"
                                        class="fw-bold tlt-overflow-ellipsis text-dark"
                                    >
                                        ${giaVND}
                                    </h6>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
            }).join('');
            const sanPhamVuaXemHtml = `
                <div class="d-flex">
                    <h4>Sản phẩm vừa xem </h4>
                </div>
                <div class="row">
                    ${allRecentlyViewedProduct}
                </div>
            `;
            sanPhamVuaXemDOM.innerHTML = sanPhamVuaXemHtml;
        }
    }
    catch (error) {
        localStorage.removeItem('danhSachSanPhamVuaXem');
        return;
    }
}
if (sanPhamVuaXemDOM) {
    showRecentlyViewedProduct();
}
showLoaiSanPhamPhoBien();
showTopStore();
showNewestProduct();