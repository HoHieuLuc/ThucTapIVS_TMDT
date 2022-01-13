const sanPhamListDOM = document.querySelector('.sanPhamBrowser');
const boLocFormDOM = document.querySelector('.boLocForm');
const phanTrangSearchSanPhamDOM = document.querySelector('.phanTrangSearchSanPham');

const init = () => {
    const newParams = window.location.search;
    const minPrice = new URLSearchParams(newParams).get("minPrice") ?? 0;
    const maxPrice = new URLSearchParams(newParams).get("maxPrice") ?? 0;
    const order = new URLSearchParams(newParams).get("order");
    boLocFormDOM.querySelector('input[name="minPrice"]').value = minPrice;
    boLocFormDOM.querySelector('input[name="maxPrice"]').value = maxPrice;
    if (order === null) {
        boLocFormDOM.querySelector('input[name="order"]').checked = false;
    }
    const allRadio = boLocFormDOM.querySelectorAll('input[name="order"]');
    allRadio.forEach((radio) => {
        if (radio.value === order) {
            radio.checked = true;
        }
    });
}
init();

const changePage = (page) => {
    changeURLparam('page', page);
    searchSanPham();
    sanPhamListDOM.scrollIntoView();
}

const searchSanPham = async () => {
    try {
        const newParams = window.location.search;
        const page = new URLSearchParams(newParams).get("page") ?? 1;
        const search = new URLSearchParams(newParams).get("q") ?? "";
        const minPrice = new URLSearchParams(newParams).get("minPrice") ?? 0;
        const maxPrice = new URLSearchParams(newParams).get("maxPrice") ?? 0;
        const order = new URLSearchParams(newParams).get("order") ?? "";
        const { data: { sanphams, totalPages } } = await axios.get(`${baseURL}api/v1/sanpham`, {
            params: {
                search,
                minPrice,
                maxPrice,
                order,
                page
            }
        });
        if (sanphams.length === 0){
            sanPhamListDOM.innerHTML = `
                <div class="d-flex justify-content-center">
                    Không tìm thấy sản phẩm nào
                </div>
            `;
        }
        const allSanPhams = sanphams.map((sanpham) => {
            const { ma_san_pham, ten_san_pham, gia, anh, xep_hang } = sanpham;
            let xepHang = '';
            if (typeof xep_hang === 'number') {
                xepHang = `${(Math.round(xep_hang * 10) / 10) + " &#9733;"}`;
            } else {
                xepHang = `Chưa có đánh giá`;
            }
            const giaVND = gia.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            return `
                <div class="col-6 col-sm-3 col-md-3 col-lg-3 mb-2">
                    <a href="${baseURL}sanpham/${ma_san_pham}" 
                        class="text-decoration-none text-dark text-center" 
                    >
                        <div class="d-flex">
                            <img 
                                src="${baseURL}images/product/${anh}" 
                                class="tlt-thumbnail img-fluid rounded my-auto mx-auto" alt="${ten_san_pham}"
                            >
                        </div>
                        <div class="tlt-overflow-ellipsis" title="${ten_san_pham}">
                            ${ten_san_pham}
                        </div>
                    </a>
                    <div class="text-center">${xepHang}</div>
                    <div class="text-center">${giaVND}</div>
                    <button type="button" data-masanpham="${ma_san_pham}" class="add-to-cart-btn w-100 btn btn-block btn-warning">
                        <i class="fas fa-cart-plus"></i>
                    </button>
                </div>
            `;
        }).join('');
        sanPhamListDOM.innerHTML = allSanPhams;
        phanTrangSearchSanPhamDOM.innerHTML = buildPagination(page, totalPages, 10, "changePage");
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

searchSanPham();

boLocFormDOM.addEventListener('click', (event) => {
    const eventTarget = event.target;
    if (eventTarget.classList.contains('chinh-gia')) {
        const formData = new FormData(boLocFormDOM);
        const minPrice = formData.get('minPrice');
        const maxPrice = formData.get('maxPrice');
        changeURLparam("minPrice", minPrice);
        changeURLparam("maxPrice", maxPrice);
        removeURLparam("page");
        searchSanPham();
        return;
    }
    if (eventTarget.classList.contains('order-check-input')) {
        const formData = new FormData(boLocFormDOM);
        const order = formData.get('order');
        changeURLparam("order", order);
        removeURLparam("page");
        searchSanPham();
        return;
    }
    if (eventTarget.classList.contains('reset-form')) {
        boLocFormDOM.reset();
        removeURLparam("minPrice");
        removeURLparam("maxPrice");
        removeURLparam("order");
        removeURLparam("page");
        searchSanPham();
    }
});