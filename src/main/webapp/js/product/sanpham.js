//Lấy mã sản phẩm trên đường dẫn
const params = window.location.pathname.split('/').slice(0);
const maSanPham = params.at(-1);

//Các biến của chi tiết sản phẩm
const nguoiDangSanPham = document.querySelector('#nguoiDangSanPham');
const tenSanPhamDOM = document.querySelector('#tenSanPham');
const danhGiaDOM = document.querySelector('#danhGia');
const moTaSanPhamDOM = document.querySelector('#moTaSanPham');
const giaDOM = document.querySelector('#gia');
const anhSanPhamDOM = document.querySelector('#anhSanPham');
const loaiSanPhamDOM = document.querySelector('#loaiSanPham');
const soLuongDOM = document.querySelector('#soLuong');
const addToCartBtnDOM = document.querySelector('#addToCartBtn');
const addToFavBtnDOM = document.querySelector('#addToFavBtn');

//
class SanPhamVuaXem {
    constructor(_maSanPham, _tenSanPham, _donGia, _hinhAnh) {
        this.maSanPham = _maSanPham;
        this.tenSanPham = _tenSanPham;
        this.donGia = _donGia;
        this.hinhAnh = _hinhAnh;
    }
}

// lưu sản phẩm vừa xem
const luuSanPhamVuaXem = (tenSanPham, gia, anhSanPham) => {
    const danhSachSanPhamVuaXem = JSON.parse(localStorage.getItem('danhSachSanPhamVuaXem')) || [];
    // nếu sản phẩm ko bị trùng thì thêm vào đầu danh sách
    // nếu sản phẩm bị trùng thì chuyển nó lên đầu
    if (!danhSachSanPhamVuaXem.some(sp => sp.maSanPham === maSanPham)) {
        danhSachSanPhamVuaXem.unshift(new SanPhamVuaXem(maSanPham, tenSanPham, gia, anhSanPham));
    } else {
        const index = danhSachSanPhamVuaXem.findIndex(sp => sp.maSanPham === maSanPham);
        danhSachSanPhamVuaXem.splice(index, 1);
        danhSachSanPhamVuaXem.unshift(new SanPhamVuaXem(maSanPham, tenSanPham, gia, anhSanPham));
    }
    localStorage.setItem('danhSachSanPhamVuaXem', JSON.stringify(danhSachSanPhamVuaXem));
    if (danhSachSanPhamVuaXem.length > 6) {
        localStorage.setItem('danhSachSanPhamVuaXem', JSON.stringify(danhSachSanPhamVuaXem.slice(0, 6)));
    }
}

// inline gallery
const buildInlineGallery = (images) => lightGallery(anhSanPhamDOM, {
    container: anhSanPhamDOM,
    dynamic: true,
    // Turn off hash plugin in case if you are using it
    // as we don't want to change the url on slide change
    hash: false,
    // Do not allow users to close the gallery
    closable: false,
    // Add maximize icon to enlarge the gallery
    showMaximizeIcon: true,
    // Append caption inside the slide item
    // to apply some animation for the captions (Optional)
    appendSubHtmlTo: ".lg-item",
    // Delay slide transition to complete captions animations
    // before navigating to different slides (Optional)
    // You can find caption animation demo on the captions demo page
    plugins: [lgZoom, lgThumbnail],
    dynamicEl: images,
    // Completely optional
    // Adding as the codepen preview is usually smaller
    thumbWidth: 60,
    thumbHeight: "40px",
    thumbMargin: 4,
    download: false,
});

const showSanPhamDetail = async (skip = false) => {
    try {
        const { data: { sanpham } } = await axios.get(`${baseURL}api/v1/sanpham/${maSanPham}`);
        // có avatar nữa
        const { ten_san_pham, ten, ten_loai_sp, ma_loai_sp, mo_ta,
            gia, anhSanPhams, xep_hang, username, ma_loai_cha, so_luong,
            so_luong_da_ban
        } = sanpham;
        if (typeof xep_hang === 'number') {
            danhGiaDOM.innerHTML = `<span>${(Math.round(xep_hang * 10) / 10) + " &#9733;"}</span>`;
        } else {
            danhGiaDOM.innerHTML = `<span>Chưa có đánh giá</span>`;
        }
        if (skip) {
            return;
        }
        if (so_luong <= 0){
            addToCartBtnDOM.textContent = 'Sản phẩm đã hết hàng';
            addToCartBtnDOM.disabled = true;
        }
        addToCartBtnDOM.dataset.masanpham = maSanPham;
        addToFavBtnDOM.dataset.masanpham = maSanPham;
        tenSanPhamDOM.textContent = ten_san_pham;
        loaiSanPhamDOM.href = `${baseURL}category/${ma_loai_sp}`;
        loaiSanPhamDOM.textContent = ten_loai_sp;
        moTaSanPhamDOM.innerHTML = mo_ta;
        giaDOM.innerHTML = gia.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        soLuongDOM.innerHTML = `
            Còn ${so_luong.toLocaleString()} 
            <span class="text-danger">(đã bán ${so_luong_da_ban.toLocaleString()})</span>
        `;
        // danh sách sản phẩm vừa xem lưu trong localstorage
        if (formDanhGiaDOM || document.querySelector('#loginDanhGia')) {
            luuSanPhamVuaXem(ten_san_pham, gia, anhSanPhams[0]);
        }
        nguoiDangSanPham.textContent = ten;
        nguoiDangSanPham.href = `${baseURL}store/${username}`;
        const anhSanPhamData = anhSanPhams.map((anhSanPham) => {
            return {
                src: `${baseURL}images/product/${anhSanPham}`,
                thumb: `${baseURL}images/product/${anhSanPham}`
            }
        });
        buildInlineGallery(anhSanPhamData).openGallery();
        showDanhGiaSPs(1);
        getSanPhamCuaShop(username, maSanPham, ma_loai_sp, ma_loai_cha);
        getSanPhamGoiY(maSanPham);
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}