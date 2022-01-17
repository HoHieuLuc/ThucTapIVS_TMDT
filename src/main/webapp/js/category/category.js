const categoryDivDOM = document.querySelector('.categoryDiv');
const sideCategoryDOM = document.querySelector('#sideCategory');
const mainCategoryDOM = document.querySelector('#mainCategory');
const searchFormDOM = document.querySelector('.searchForm');
const phanTrangDOM = document.querySelector('.phanTrang');
const params = window.location.pathname.split('/').slice(0);
let maLoaiSanPham = params.at(-1);

const init = () => {
    if (searchFormDOM) {
        const newParams = window.location.search;
        const search = new URLSearchParams(newParams).get("search") ?? "";
        const orderBy = new URLSearchParams(newParams).get("ob") ?? "date";
        const order = new URLSearchParams(newParams).get("o") ?? "desc";
        searchFormDOM.querySelector("input[name=search]").value = search;
        searchFormDOM.querySelector("select[name=orderBy]").value = orderBy;
        searchFormDOM.querySelector("select[name=order]").value = order;
    }
}
init();

const showLoaiSanPhams = async () => {
    try {
        const newParams = window.location.search;
        const page = new URLSearchParams(newParams).get("page") ?? 1;
        const search = new URLSearchParams(newParams).get("search") ?? "";
        const orderBy = new URLSearchParams(newParams).get("ob") ?? "";
        const order = new URLSearchParams(newParams).get("o") ?? "";
        if (maLoaiSanPham === 'category') {
            maLoaiSanPham = 0;
        }
        const {
            data: {
                loaiSanPhams, loaiSanPhamHienTai, sanPhams,
                loaiSanPhamCha, loaiSanPhamCungCha, totalPages
            }
        } = await axios.get(`${baseURL}api/v1/category/${maLoaiSanPham}`, {
            params: {
                page,
                search,
                orderBy,
                order
            }
        });
        if (loaiSanPhams) {
            renderSideCategory(loaiSanPhams, loaiSanPhamHienTai);
            renderMainCategory(loaiSanPhams);
        }
        if (sanPhams) {
            searchFormDOM.classList.remove('d-none');
            renderSideProductCategory(loaiSanPhamCha, loaiSanPhamCungCha);
            renderMainProduct(sanPhams, 1, totalPages);
        }
    } catch (error) {
        console.log(error);
        categoryDivDOM.innerHTML = `
            <div class="d-flex justify-content-center">
                <div class="fs-2">${error.response.data.message}</div>
            </div>
        `;
    }
}

const renderSideCategory = (loaiSanPhams, loaiSanPhamHienTai) => {
    const html = loaiSanPhams.map((loaiSanPham) => {
        const { ma_loai_sp, ten_loai_sp } = loaiSanPham;
        return `
            <div>
                <a class="nav-link" href="${baseURL}category/${ma_loai_sp}">${ten_loai_sp}</a>
            </div>
        `;
    }).join('');
    if (loaiSanPhamHienTai) {
        const { ten_loai_sp, ma_loai_cha } = loaiSanPhamHienTai;
        if (ma_loai_cha === null || ma_loai_cha === undefined) {
            sideCategoryDOM.innerHTML = `
                <a class="fs-5 text-decoration-none" href="${baseURL}category">
                    <i class="fas fa-arrow-circle-left"></i> ${ten_loai_sp}
                </a>
            `;
        }
        else {
            sideCategoryDOM.innerHTML = `
                <a class="fs-5 text-decoration-none" href="${baseURL}category/${ma_loai_cha}">
                    <i class="fas fa-arrow-circle-left"></i> ${ten_loai_sp}
                </a>
            `;
        }
    }
    sideCategoryDOM.innerHTML += html;
}

const renderMainCategory = (loaiSanPhams) => {
    const html = loaiSanPhams.map((loaiSanPham) => {
        const { ma_loai_sp, ten_loai_sp, anh } = loaiSanPham;
        return `
            <div class="col-6 col-sm-4 col-md-4 col-lg-3 col-xl-3">
                <a href="${baseURL}category/${ma_loai_sp}" 
                    class="text-decoration-none text-dark text-center" 
                >
                    <img src="${baseURL}images/category/${anh}" class="tlt-thumbnail rounded mx-auto d-block" alt="${ten_loai_sp}">
                    <div 
                        class="text-center tlt-overflow-ellipsis"
                        title="${ten_loai_sp}"
                    >${ten_loai_sp}</div>
                </a>
            </div>
        `;
    }).join('');
    mainCategoryDOM.innerHTML = html;
}

const changePage = (page) => {
    changeURLparam("page", page);
    searchSanPham();
}

const renderMainProduct = (sanPhams, currentPage, totalPages) => {
    if (sanPhams.length === 0) {
        mainCategoryDOM.innerHTML = `
            <div class="d-flex justify-content-center">
                <div class="fs-2">Không tìm thấy sản phẩm nào</div>
            </div>
        `;
        return;
    }
    const html = sanPhams.map((sanPham) => {
        const { ma_san_pham, ten_san_pham, gia, anh, xep_hang } = sanPham;
        const giaVND = gia.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        let xepHang = '';
        if (typeof xep_hang === 'number') {
            xepHang = `${(Math.round(xep_hang * 10) / 10) + " &#9733;"}`;
        } else {
            xepHang = `Chưa có đánh giá`;
        }
        return `
            <div class="col-6 col-sm-4 col-md-4 col-lg-3 col-xl-3 mb-1">
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
                        <div>${xepHang}</div>
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
    mainCategoryDOM.innerHTML = html;
    phanTrangDOM.innerHTML = buildPagination(currentPage, totalPages, 10, "changePage");
}

const renderSideProductCategory = (loaiSanPhamCha, loaiSanPhamCungCha) => {
    const loaiSanPhamCungChaHTML = loaiSanPhamCungCha.map((item) => {
        const { ma_loai_sp, ten_loai_sp } = item;
        const disabled = ma_loai_sp == maLoaiSanPham ? 'disabled text-muted' : '';
        return `
            <div>
                <a class="nav-link ${disabled}"  href="${baseURL}category/${ma_loai_sp}">${ten_loai_sp}</a>
            </div>
        `;
    }).join('');
    const loaiSanPhamChaHTML = `
        <a class="fs-5 text-decoration-none" href="${baseURL}category/${loaiSanPhamCha.ma_loai_sp}">
            ${loaiSanPhamCha.ten_loai_sp}
        </a>
    `;
    sideCategoryDOM.innerHTML = loaiSanPhamChaHTML + loaiSanPhamCungChaHTML;
}

showLoaiSanPhams();

const searchSanPham = async () => {
    try {
        const newParams = window.location.search;
        const page = new URLSearchParams(newParams).get("page") ?? 1;
        const search = new URLSearchParams(newParams).get("search") ?? "";
        const orderBy = new URLSearchParams(newParams).get("ob") ?? "";
        const order = new URLSearchParams(newParams).get("o") ?? "";
        const { data: { sanPhams, totalPages } } = await axios.get(`${baseURL}api/v1/category/${maLoaiSanPham}`, {
            params: {
                page,
                search,
                orderBy,
                order
            }
        });
        renderMainProduct(sanPhams, page, totalPages);
    } catch (error) {
        console.log(error);
    }
}

searchFormDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(searchFormDOM);
    removeURLparam("page");
    changeURLparam("search", formData.get("search"));
    changeURLparam("ob", formData.get("orderBy"));
    changeURLparam("o", formData.get("order"));
    searchSanPham();
});