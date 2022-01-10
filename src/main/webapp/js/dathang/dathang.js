const datHangDivDOM = document.querySelector('.datHangDiv');
const datHangDOM = document.querySelector('#datHangList');
const tongSoSanPhamDOM = document.querySelector('#tongSoSanPham');
const tongTienDOM = document.querySelector('#tongTien');
const datHangBtnDOM = document.querySelector('.dat-hang-btn');

const urlParams = window.location.search;
const sellerUsername = new URLSearchParams(urlParams).get("seller") ?? null;

const showDatHang = async () => {
    try {
        const { data: { gio_hangs } } = await axios.get(`${baseURL}api/v1/giohang`, {
            params: {
                username: sellerUsername
            }
        });
        if (gio_hangs.length === 0) {
            window.location.href = `${baseURL}cart`;
            return;
        }
        let tongTien = 0;
        let soLuong = 0;
        const allGioHangs = gio_hangs.map((gio_hang) => {
            const { ten, username, san_phams } = gio_hang;
            const sanPhamsHTML = san_phams.map((sanPham) => {
                const { ten_san_pham, anh, so_luong, tong_tien, ma_san_pham } = sanPham;
                tongTien += tong_tien;
                soLuong += so_luong;
                const tongTienVND = tong_tien.toLocaleString("vi-VN", {
                    style: "currency",
                    currency: "VND",
                });
                return `
                    <hr>
                    <div class="row mb-3 position-relative">
                        <div class="col-3">
                            <img class="img-fluid" style="object-fit: contain; width: 100%; height: 12rem;" src="${baseURL}images/product/${anh}" alt="${ten_san_pham}">
                        </div>
                        <div class="col-9">
                            <div class="row">
                                <div class="col-md-6 col-lg-8 col-xl-6 mb-3">
                                    <a 
                                        href="${baseURL}sanpham/${ma_san_pham}" 
                                        class="text-dark" 
                                        style="text-overflow: ellipsis; display: block; overflow: hidden;"
                                        title="${ten_san_pham}"
                                    >
                                        ${ten_san_pham}
                                    </a>
                                </div>
                                <div class="col-md-6 col-lg-4 col-xl-3 mb-3">
                                    Số lượng: ${so_luong}
                                </div>
                                <div class="tong-tien-cart col-lg-12 col-xl-3 form-floating mb-3">
                                    ${tongTienVND}
                                </div>
                            </div>
                        </div>
                    </div>
                `;
            }).join('') + '</div>';
            return `
                <div class="container border border-1 mb-2">
                    <div class="my-3 d-flex justify-content-between">
                        <h5>Người bán <a href="${baseURL}store/${username}" class="text-dark">${ten}</a></h5>
                    </div>
            ` + sanPhamsHTML;
        }).join('');
        tongSoSanPhamDOM.textContent = soLuong;
        tongTienDOM.textContent = tongTien.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        datHangDOM.innerHTML = allGioHangs;
    } catch (error) {
        console.log(error);
    }
}

showDatHang();

datHangBtnDOM.addEventListener('click', async () => {
    try {
        const formData = new FormData;
        formData.append('username', sellerUsername);
        const { data: { message } } = await axios.post(`${baseURL}api/v1/dathang`, formData);
        datHangDivDOM.innerHTML = `
            <div class="d-flex justify-content-center fs-2 text-success">${message}</div>
            <div class="row gy-2">
                <div class="col-4"></div>
                <a href="${baseURL}" class="col-4 btn btn-primary">
                    Về trang chủ <i class="fas fa-home"></i>
                </a>
                <div class="col-4"></div>

                <div class="col-4"></div>
                <a href="${baseURL}user/buyer/dathang" class="col-4 btn btn-success">
                    Theo dõi đơn hàng <i class="fas fa-cheese"></i>
                </a>
                <div class="col-4"></div>
            </div>
        `;
    } catch (error) {
        console.log(error);
        const { response: { data: { message } } } = error;
        thongBao(message ?? 'Có lỗi xảy ra', true);
    }
});