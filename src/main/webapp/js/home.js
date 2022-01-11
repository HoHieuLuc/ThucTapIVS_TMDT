const sanPhamDaXemDOM = document.querySelector('#sanPhamDaXem');
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
                        <img src="${baseURL}images/category/${anh}" class="tlt-thumbnail rounded mx-auto d-block" alt="${ten_loai_sp}">
                        <div class="tlt-overflow-eclipse" title="${ten_loai_sp}">${ten_loai_sp}</div>
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
                        <img src="${baseURL}images/user/${avatar}" class="tlt-thumbnail rounded mx-auto d-block" alt="${ten}">
                        <div class="tlt-overflow-eclipse" title="${ten}">${ten}</div>
                        <div>${xep_hang} &#9733;</div>
                    </a>
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
                <div class="col-6 col-sm-3 col-md-3 col-lg-2 mb-2">
                    <a href="${baseURL}sanpham/${ma_san_pham}" 
                        class="text-decoration-none text-dark text-center" 
                    >
                        <img src="${baseURL}images/product/${anh}" class="tlt-thumbnail rounded mx-auto d-block" alt="${ten_san_pham}">
                        <div class="tlt-overflow-eclipse" title="${ten_san_pham}">
                            ${ten_san_pham}
                        </div>
                        <div>${giaVND}</div>
                    </a>
                    <button type="button" data-masanpham="${ma_san_pham}" class="add-to-cart-btn w-100 btn btn-block btn-warning">
                        <i class="fas fa-cart-plus"></i>
                    </button>
                </div>
            `;
        }).join('');
        sanPhamMoiDOM.innerHTML = allNewestProduct;
    } catch (error) {
        console.log(error);
    }
}

showLoaiSanPhamPhoBien();
showTopStore();
showNewestProduct();