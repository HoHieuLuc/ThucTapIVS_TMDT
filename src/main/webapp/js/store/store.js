// thông tin người bán
const avatarDOM = document.querySelector("#avatar");
const tenNguoiBanDOM = document.querySelector("#tenNguoiBan");
const gioiThieuDOM = document.querySelector("#gioiThieu");
const ratingDOM = document.querySelector("#rating");
const linkDOM = document.querySelector("#link");
const ngayThamGiaDOM = document.querySelector("#ngayThamGia");
const soDienThoaiDOM = document.querySelector("#soDienThoai");
const emailDOM = document.querySelector("#email");
const diaChiDOM = document.querySelector("#diaChi");
const ratingChartDOM = document.querySelector("#ratingChart");

// form đánh giá khách hàng
const formDanhGiaKhachHangDOM = document.querySelector("#formDanhGiaKhachHang");

// form tìm kiếm
const searchFormDOM = document.querySelector(".searchForm");

// danh sách sản phẩm
const productListDOM = document.querySelector(".productList");
const phanTrangProductDOM = document.querySelector(".phanTrangProduct");
const rowsPerPageDOM = document.querySelector(".rowsPerPage");

const params = window.location.pathname.split("/").slice(0);
const username = params[params.length - 1];

// Báo cáo người dùng DOM
const baoCaoButtonDOM = document.querySelector('#baoCaoButton');
const formBaoCaoDOM = document.querySelector('#formBaoCao');
const noiDungBaoCaoDom = document.querySelector('#noiDungBaoCao');

const init = () => {
    const newParams = window.location.search;
    const search = new URLSearchParams(newParams).get("search") ?? "";
    const orderBy = new URLSearchParams(newParams).get("ob") ?? "date";
    const order = new URLSearchParams(newParams).get("o") ?? "desc";
    const rowsPerPage = new URLSearchParams(newParams).get("rpp") ?? 10;
    searchFormDOM.querySelector("input[name=search]").value = search;
    searchFormDOM.querySelector("select[name=orderBy]").value = orderBy;
    searchFormDOM.querySelector("select[name=order]").value = order;
    rowsPerPageDOM.value = rowsPerPage;
}

init();

const dataList = (myData, myLabels) => ({
    labels: myLabels,
    datasets: [
        {
            label: "Số đánh giá",
            data: myData,
            backgroundColor: "lightgreen",
            borderColor: "darkgreen",
            borderWidth: 1,
        },
    ],
});

const config = (myData, myLabels) => ({
    type: "bar",
    data: dataList(myData, myLabels),
    options: {
        scales: {
            y: {
                beginAtZero: true,
            },
        },
        indexAxis: "y",
        aspectRatio: 1.5,
    },
});

const showStoreInfo = async () => {
    try {
        const {
            data: { store_info, product_rating },
        } = await axios.get(`${baseURL}api/v1/store/${username}/info`);
        const { ten, dia_chi, gioi_thieu, ngay_tao, email,
            so_dien_thoai, avatar, twitter_link, facebook_link,
            personal_link, rating, so_danh_gia,
        } = store_info;

        ratingDOM.innerHTML =
            rating === undefined
                ? "Chưa có đánh giá"
                : `${(Math.round(rating * 10) / 10)} &#9733; (${so_danh_gia} đánh giá)`;
        avatarDOM.src = `${baseURL}images/user/${avatar}`;
        tenNguoiBanDOM.textContent = ten;
        gioiThieuDOM.textContent = gioi_thieu;
        ngayThamGiaDOM.textContent = `${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}`;
        soDienThoaiDOM.textContent = so_dien_thoai;
        emailDOM.textContent = email;
        diaChiDOM.textContent = dia_chi;

        const facebookLink =
            facebook_link === ""
                ? ""
                : `<a href="${facebook_link}" target="_blank"><i class="fab fa-facebook"></i></a>`;
        const twitterLink =
            twitter_link === ""
                ? ""
                : `<a href="${twitter_link}" target="_blank"><i class="fab fa-twitter"></i></a>`;
        const personalLink =
            personal_link === ""
                ? ""
                : `<a href="${personal_link}" target="_blank"><i class="fas fa-external-link-alt"></i></a>`;
        linkDOM.innerHTML = `${facebookLink} ${twitterLink} ${personalLink}`;

        const myData = [];
        let currentItemIndex = 0;
        for (let i = 5; i > 0; i--) {
            if (
                currentItemIndex < product_rating.length &&
                product_rating[currentItemIndex].so_sao == i
            ) {
                myData.push(product_rating[currentItemIndex].so_luong);
                currentItemIndex++;
            } else {
                myData.push(0);
            }
        }
        const labels = ["5 sao", "4 sao", "3 sao", "2 sao", "1 sao"];
        const myConfig = config(myData, labels);
        new Chart(ratingChartDOM, myConfig);
        showProductList();
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
};

showStoreInfo();

const changePage = (page) => {
    changeURLparam("page", page);
    showProductList();
    productListDOM.scrollIntoView();
};

const showProductList = async () => {
    try {
        const newParams = window.location.search;
        const page = new URLSearchParams(newParams).get("page") ?? 1;
        const rowsPerPage = new URLSearchParams(newParams).get("rpp") ?? 10;
        const search = new URLSearchParams(newParams).get("search") ?? "";
        const orderBy = new URLSearchParams(newParams).get("ob") ?? "date";
        const order = new URLSearchParams(newParams).get("o") ?? "desc";
        const {
            data: { products, totalPages },
        } = await axios.get(`${baseURL}api/v1/store/${username}/products`, {
            params: {
                page: page,
                rowsPerPage: rowsPerPage,
                search: search,
                orderBy: orderBy,
                order: order,
            },
        });
        console.log(products);
        if (products.length === 0) {
            productListDOM.innerHTML = `<div class="text-center">Không tìm thấy sản phẩm</div>`;
            return;
        }
        const sanPhamsHTML = products.map((sanPham) => {
            const { ma_san_pham, ten_san_pham, gia, ma_loai_sp,
                ten_loai_sp, anh, xep_hang,
            } = sanPham;
            const giaVND = gia.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            const xepHang = xep_hang === undefined ? "Chưa có đánh giá" : `${(Math.round(xep_hang * 10) / 10)} &#9733;`;
            return `
                <div class="row mt-3">
                    <a href="${baseURL}sanpham/${ma_san_pham}" 
                        class="col-md-3 d-flex store-product-img-link rounded"
                    >
                        <img alt="${ten_san_pham}" 
                        src="${baseURL}images/product/${anh}"" 
                        class="store-product-img mx-auto my-auto img-fluid">
                    </a>
                    <div class="col-md-9">
                        <a 
                            href="${baseURL}sanpham/${ma_san_pham}" 
                            style="text-overflow: ellipsis; display: block; overflow: hidden;" 
                            class="fs-5 text-decoration-none"
                            title="${ten_san_pham}"
                        >
                            ${ten_san_pham}
                        </a>
                        <br>
                        <a href="${baseURL}category/${ma_loai_sp}" class="text-muted">${ten_loai_sp}</a>
                        <p>Xếp hạng: ${xepHang}</p>
                        <p class="fs-4">${giaVND}</p>
                        <div class="row gy-2">
                            <div class="col-md-6 col-lg-6 col-xl-5 col-sm-12">
                                <button 
                                    type="button" data-masanpham="${ma_san_pham}" 
                                    class="w-100 add-to-cart-btn btn btn-block btn-warning"
                                >
                                    Thêm vào giỏ hàng <i class="fas fa-cart-plus"></i>
                                </button>
                            </div>
                            <div class="col-md-6 col-lg-6 col-xl-5 col-sm-12">
                                <button 
                                    type="button" data-masanpham="${ma_san_pham}" 
                                    class="w-100 add-to-fav-btn btn btn-block btn-danger"
                                >
                                    Thêm vào mục yêu thích <i class="far fa-heart"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
            `;
        }).join("");
        productListDOM.innerHTML = sanPhamsHTML;
        phanTrangProductDOM.innerHTML = buildPagination(
            page,
            totalPages,
            5,
            "changePage"
        );
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
};

// thêm sự kiện
// khi submit form tìm kiếm
searchFormDOM.addEventListener("submit", (event) => {
    event.preventDefault();
    removeURLparam("page");
    const formData = new FormData(searchFormDOM);
    const search = formData.get("search");
    const orderBy = formData.get("orderBy");
    const order = formData.get("order");
    changeURLparam("search", search);
    changeURLparam("ob", orderBy);
    changeURLparam("o", order);
    showProductList();
    productListDOM.scrollIntoView();
});

// khi đổi giá trị số mặt hàng mỗi trang
rowsPerPageDOM.addEventListener("change", () => {
    changeURLparam("rpp", rowsPerPageDOM.value);
    removeURLparam("page");
    showProductList();
    productListDOM.scrollIntoView();
});

//Mở form báo cáo ng dùng
if (baoCaoButtonDOM) {
    baoCaoButtonDOM.addEventListener('click', (event) => {
        document.getElementById('formBaoCao').classList.remove("d-none");
    });
    //Bắt sự kiện xử lý form và lấy dữ liệu
    formBaoCaoDOM.addEventListener('click', async (event) => {
        const el = event.target;
        if (el.textContent == "Gửi") {
            console.log("Tiến hành gửi báo cáo");
            const formData = new FormData();
            formData.append('userName', username);
            formData.append('noiDung', noiDungBaoCaoDom.value);
            try {
                await axios.post(`${baseURL}api/v1/baocao/${username}/submit`, formData);
                thongBao('Gửi báo cáo thành công');
                //Sau khi gửi, xóa nội dung trong noiDungBaoCaoDOM
                noiDungBaoCaoDom.value = "";
            }
            catch (error) {
                thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
            }
        }
        if (el.textContent == "Đóng") document.getElementById('formBaoCao').classList.add("d-none");
    })
}