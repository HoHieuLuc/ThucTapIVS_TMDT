const sideCategoryDOM = document.querySelector('#sideCategory');
const mainCategoryDOM = document.querySelector('#mainCategory');
const searchFormDOM = document.querySelector('.searchForm');
const phanTrangDOM = document.querySelector('.phanTrang');
const params = window.location.pathname.split('/').slice(0);
let maLoaiSanPham = params.at(-1);

const showLoaiSanPhams = async () => {
    try {
        if (maLoaiSanPham === 'category') {
            maLoaiSanPham = 0;
        }
        const {
            data: {
                loaiSanPhams, loaiSanPhamHienTai, sanPhams,
                loaiSanPhamCha, loaiSanPhamCungCha, totalPages
            }
        } = await axios.get(`${baseURL}api/v1/category/${maLoaiSanPham}`);
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
        sideCategoryDOM.innerHTML = `
            <p class="fs-5">${loaiSanPhamHienTai.ten_loai_sp}</p>
        `
    }
    sideCategoryDOM.innerHTML += html;
}

const renderMainCategory = (loaiSanPhams) => {
    const html = loaiSanPhams.map((loaiSanPham) => {
        const { ma_loai_sp, ten_loai_sp, anh } = loaiSanPham;
        return `
            <div class="col-3">
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
    }
    const html = sanPhams.map((sanPham) => {
        const { ma_san_pham, ten_san_pham, gia, anh, xep_hang } = sanPham;
        const giaVND = gia.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        const xepHang = xep_hang === undefined ? 'Chưa có đánh giá' : `${xep_hang} &#9733;`;
        return `
            <div class="col-3">
                <a href="${baseURL}sanpham/${ma_san_pham}" class="text-decoration-none text-dark">
                    <div class="d-flex">
                        <img 
                            src="${baseURL}images/product/${anh}" 
                            class="tlt-thumbnail img-fluid rounded my-auto mx-auto" alt="${ten_san_pham}"
                        >
                    </div>
                    <p 
                        title="${ten_san_pham}"
                        class="tlt-overflow-ellipsis"
                    >
                        ${ten_san_pham}
                    </p>
                </a>
                <div class="text-center">${xepHang}</div>
                <div class="text-center">${giaVND}</div>
                <button type="button" data-masanpham="${ma_san_pham}" class="add-to-cart-btn w-100 btn btn-block btn-warning">
                    <i class="fas fa-cart-plus"></i>
                </button>
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
        <p class="fs-5">${loaiSanPhamCha.ten_loai_sp}</p>
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