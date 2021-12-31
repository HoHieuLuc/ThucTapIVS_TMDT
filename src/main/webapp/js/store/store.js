// thông tin người bán
const avatarDOM = document.querySelector('#avatar');
const tenNguoiBanDOM = document.querySelector('#tenNguoiBan');
const gioiThieuDOM = document.querySelector('#gioiThieu');
const ratingDOM = document.querySelector('#rating');
const linkDOM = document.querySelector('#link');
const ngayThamGiaDOM = document.querySelector('#ngayThamGia');
const soDienThoaiDOM = document.querySelector('#soDienThoai');
const emailDOM = document.querySelector('#email');
const diaChiDOM = document.querySelector('#diaChi');
const ratingChartDOM = document.querySelector('#ratingChart');

// form tìm kiếm
const searchForm = document.querySelector('.searchForm');

// danh sách sản phẩm
const productListDOM = document.querySelector('.productList');
const phanTrangProductDOM = document.querySelector('.phanTrangProduct');
const rowsPerPageDOM = document.querySelector('.rowsPerPage');

const params = window.location.pathname.split('/').slice(0);
const username = params[params.length - 1];

const data = (myData, myLabels) => ({
    labels: myLabels,
    datasets: [{
        label: 'Số đánh giá',
        data: myData,
        backgroundColor: 'lightgreen',
        borderColor: 'darkgreen',
        borderWidth: 1
    }]
});

const config = (myData, myLabels) => ({
    type: 'bar',
    data: data(myData, myLabels),
    options: {
        scales: {
            y: {
                beginAtZero: true
            },
        },
        indexAxis: 'y',
        aspectRatio: 1.5,
    },
});

const showStoreInfo = async () => {
    try {
        const { data: { store_info, product_rating } } = await axios.get(`${baseURL}api/v1/store/${username}/info`);
        const { ten,ma_khach_hang, dia_chi, gioi_thieu, ngay_tao, email, so_dien_thoai,
            avatar, twitter_link, facebook_link, personal_link, rating, so_danh_gia
        } = store_info;
        avatarDOM.src = `${baseURL}images/user/${avatar}`;
        tenNguoiBanDOM.textContent = ten;
        gioiThieuDOM.textContent = gioi_thieu;
        ratingDOM.innerHTML = rating === undefined ? 'Chưa có đánh giá' : `${rating} &#11088; (${so_danh_gia} đánh giá)`;
        ngayThamGiaDOM.textContent = `${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}`;
        soDienThoaiDOM.textContent = so_dien_thoai;
        emailDOM.textContent = email;
        diaChiDOM.textContent = dia_chi;

        const facebookLink = facebook_link === undefined ? '' : `<a href="${facebook_link}" target="_blank"><i class="fab fa-facebook"></i></a>`;
        const twitterLink = twitter_link === undefined ? '' : `<a href="${twitter_link}" target="_blank"><i class="fab fa-twitter"></i></a>`;
        const personalLink = personal_link === undefined ? '' : `<a href="${personal_link}" target="_blank"><i class="fas fa-external-link-alt"></i></a>`;
        linkDOM.innerHTML = `${facebookLink} ${twitterLink} ${personalLink}`;

        const myData = [];
        let currentItemIndex = 0;
        for (let i = 5; i > 0; i--) {
            if (currentItemIndex < product_rating.length && product_rating[currentItemIndex].so_sao == i) {
                myData.push(product_rating[currentItemIndex].so_luong);
                currentItemIndex++;
            }
            else {
                myData.push(0);
            }
        }
        console.log(myData);
        const labels = [
            '5 sao',
            '4 sao',
            '3 sao',
            '2 sao',
            '1 sao'
        ];
        const myConfig = config(myData, labels);
        new Chart(
            ratingChartDOM,
            myConfig
        );
        /* 
            ========================= ========================= ========================= =========================
            Khu vực đánh giá khách hàng
            ========================= ========================= ========================= =========================
        */
        //Lấy số sao đã chọn
        const soSao = document.querySelector("#soSao");
        console.log(soSao.value);

        //Tạo formDG_Data
        var formDG_Data = new FormData();
        //Thêm dữ liệu như: maKHDuocDanhGia, soSao vào formDG_Data
        formDG_Data.append('maKHDuocDanhGia',ma_khach_hang);
        formDG_Data.append('soSao', soSao);

        //Log dữ liệu trong form test
        // Display the values
        console.log("Đây là chỗ xem dữ liệu form Đánh giá khách hàng");
        for (var value of formDG_Data.values()) {
            console.log(value);
        }
        console.log("Đây là chỗ xem dữ liệu form Đánh giá khách hàng");
        /* 
       ========================= ========================= ========================= =========================
       Khu vực đánh giá khách hàng
       ========================= ========================= ========================= =========================
   */
        showProductList();
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

showStoreInfo();

const changePage = (page) => {
    changeURLparam('page', page);
    showProductList();
}

const showProductList = async () => {
    try {
        const newParams = window.location.search;
        const page = new URLSearchParams(newParams).get('page') ?? 1;
        // có thể sử dụng ${baseURL}api/v1/store/${username}/products${newParams}
        // thay vì đống loằng ngoằng bên dưới nhưng nếu làm vậy thì
        // biến trên thanh url phải giống biến trong action
        // như vậy thì url nó dài ra và không còn hay nữa
        const rowsPerPage = new URLSearchParams(newParams).get('rpp') ?? 10;
        const search = new URLSearchParams(newParams).get('search') ?? '';
        const orderBy = new URLSearchParams(newParams).get('ob') ?? 'ngay_dang';
        const order = new URLSearchParams(newParams).get('o') ?? 'desc';
        const {
            data: {
                products,
                total_page
            }
        } = await axios.get(
            `${baseURL}api/v1/store/${username}/products`,
            {
                params: {
                    page: page,
                    rowsPerPage: rowsPerPage,
                    search: search,
                    orderBy: orderBy,
                    order: order
                }
            }
        );
        console.log(products);
        if (products.length === 0) {
            productListDOM.innerHTML = `<div class="text-center">Không tìm thấy sản phẩm</div>`;
            return;
        }
        const sanPhamsHTML = products.map((sanPham) => {
            const { ma_san_pham, ten_san_pham, gia, ten_loai_sp, so_luong, ngay_dang, anh, xep_hang } = sanPham;
            const giaVND = (gia).toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND',
            });
            const xepHang = xep_hang === undefined ? 'Chưa có đánh giá' : `${xep_hang} &#11088;`;
            return `
                <div class="row mt-3">
                    <a href="${baseURL}sanpham/${ma_san_pham}" class="col-md-3 bg-light rounded store-product-img-link">
                        <img alt="" 
                        src="${baseURL}images/product/${anh}"" 
                        class="d-block img-fluid store-product-img">
                    </a>
                    <div class="col-md-9">
                        <a href="${baseURL}sanpham/${ma_san_pham}" class="fs-5 text-decoration-none">${ten_san_pham}</a>
                        <p>Xếp hạng: ${xepHang}</p>
                        <p class="fs-4">${giaVND}</p>
                        <button class="add-to-card-btn btn btn-link" data-id="${ma_san_pham}">Thêm vào giỏ</button>
                        <button class="add-to-wishlist-btn btn btn-link" data-id="${ma_san_pham}">Thêm vào mục yêu thích</button>
                    </div>
                </div>
                <hr>
            `;
        }).join('');
        productListDOM.innerHTML = sanPhamsHTML;
        phanTrangProductDOM.innerHTML = buildPagination(page, total_page, 5, "changePage");
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

// 2 hàm thêm vào giỏ này sẽ bỏ vào file js khác vì nhiều trang sử dụng 2 hàm này
// giờ tạm thời bỏ vô đây để test
// thêm vào giỏ
const addToCart = async (maSanPham) => {
    try {
        // await axios.post(...);
        alert(`test thêm vào giỏ: ${maSanPham}`);
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

// thêm vào mục yêu thích
const addToWishList = async (maSanPham) => {
    try {
        // await axios.post(...);
        alert(`test mục yêu thích: ${maSanPham}`);
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

// thêm sự kiện
// khi submit form tìm kiếm
searchForm.addEventListener('submit', (event) => {
    event.preventDefault();
    removeURLparam('page');
    const search = document.querySelector('#search').value;
    const orderBy = document.querySelector('#orderBy').value;
    const order = document.querySelector('#order').value;
    changeURLparam('search', search);
    changeURLparam('ob', orderBy);
    changeURLparam('o', order);
    showProductList();
});

// khi đổi giá trị số mặt hàng mỗi trang
rowsPerPageDOM.addEventListener('change', () => {
    changeURLparam('rpp', rowsPerPageDOM.value);
    showProductList();
});

// khi click vào nút thêm vào giỏ và thêm vào mục yêu thích
productListDOM.addEventListener('click', (event) => {
    const el = event.target;
    if (el.classList.contains('add-to-card-btn')) {
        addToCart(el.dataset.id);
    }
    if (el.classList.contains('add-to-wishlist-btn')) {
        addToWishList(el.dataset.id);
    }
});