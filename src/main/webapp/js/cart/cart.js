const gioHangDOM = document.querySelector('#gioHangList');
const tongSoSanPhamDOM = document.querySelector('#tongSoSanPham');
const tongTienDOM = document.querySelector('#tongTien');
const mainGioHangDOM = document.querySelector('.mainGioHang');

const showGioHang = async () => {
    try {
        const { data: { gio_hangs } } = await axios.get(`${baseURL}api/v1/giohang`);
        if (gio_hangs.length === 0) {
            mainGioHangDOM.innerHTML = `
                <div class="text-center">
                    <h3>Giỏ hàng trống</h3>
                    <a href="${baseURL}" class="btn btn-primary">Tiếp tục mua hàng</a>
                </div>
            `;
            return;
        }
        const allGioHangs = gio_hangs.map((gio_hang) => {
            const { ten, username, san_phams } = gio_hang;
            const sanPhamsHTML = san_phams.map((sanPham) => {
                const { ten_san_pham, anh, so_luong, don_gia, tong_tien, ma_san_pham } = sanPham;
                const tongTienVND = tong_tien.toLocaleString("vi-VN", {
                    style: "currency",
                    currency: "VND",
                });
                return `
                    <hr>
                    <div class="row mb-3 position-relative cart-div">
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
                                    <div class="input-group">
                                        <button class="giam-gio-hang btn btn-outline-secondary border border-dark" type="button">
                                            <i class="fas fa-minus"></i>
                                        </button>
                                        <input
                                            type="text" 
                                            style="-webkit-appearance: none; margin: 0;" 
                                            class="so-luong-cart-input form-control border-0 border-top border-bottom border-dark"
                                            value="${so_luong}"
                                            data-masanpham="${ma_san_pham}"
                                            data-dongia="${don_gia}"
                                        />
                                        <button class="tang-gio-hang btn btn-outline-secondary border border-dark" type="button">
                                            <i class="fas fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="tong-tien-cart col-lg-12 col-xl-3 form-floating mb-3">
                                    ${tongTienVND}
                                </div>
                            </div>
                            <div class="position-absolute bottom-0 end-0">
                                <div class="d-flex">
                                    <button data-masanpham="${ma_san_pham}" class="move-to-fav-btn btn btn-link">Chuyển vào wishlist</button>
                                    <hr style="width: 1px; height: 20px; display: inline-block;">
                                    <button data-masanpham="${ma_san_pham}" class="remove-from-cart-btn btn btn-link">Xóa</button>
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
                        <a
                            href="${baseURL}dathang?seller=${username}"
                            class="text-nowrap btn btn-link"
                        >
                            Chỉ đặt mua của người bán này
                        </a>
                    </div>
            ` + sanPhamsHTML;
        }).join('');
        gioHangDOM.innerHTML = allGioHangs;
        getTongTienVaSoLuong();
    } catch (error) {
        console.log(error);
    }
}
showGioHang();

const getTongTienVaSoLuong = async () => {
    try {
        const { data: { gioHang } } = await axios.get(`${baseURL}api/v1/giohang/sum`);
        const { so_luong, tong_tien } = gioHang;
        tongSoSanPhamDOM.textContent = so_luong
        tongTienDOM.textContent = tong_tien.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
    } catch (error) {
        console.log(error);
    }
}

gioHangDOM.addEventListener('focusout', async (event) => {
    const eventTarget = event.target;
    if (eventTarget.classList.contains('so-luong-cart-input')) {
        const cartDivDOM = eventTarget.closest('.cart-div');
        const tangGioHangDOM = cartDivDOM.querySelector('.tang-gio-hang');
        const giamGioHangDOM = cartDivDOM.querySelector('.giam-gio-hang');
        const tongTienThisCartDOM = cartDivDOM.querySelector('.tong-tien-cart');

        tangGioHangDOM.disabled = true;
        giamGioHangDOM.disabled = true;
        eventTarget.disabled = true;

        const soluong = parseInt(eventTarget.value);
        const { masanpham, dongia } = eventTarget.dataset;
        const formData = new FormData();

        formData.append('maSanPham', masanpham);
        formData.append('soLuong', soluong);

        eventTarget.value = await updateCart(formData);
        const tongTien = parseInt(eventTarget.value) * parseInt(dongia);
        tongTienThisCartDOM.innerText = tongTien.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        await getTongTienVaSoLuong();

        tangGioHangDOM.disabled = false;
        giamGioHangDOM.disabled = false;
        eventTarget.disabled = false;
    }
});