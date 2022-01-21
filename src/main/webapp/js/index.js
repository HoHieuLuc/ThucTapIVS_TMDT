const loginLinkDOM = document.querySelector('#login');
const registerLinkDOM = document.querySelector('#register');
const logOutLinkDOM = document.querySelector('#logout');
const loginDanhGiaDOM = document.querySelector('#loginDanhGia');

const mainDOM = document.querySelector('#main');

const currentURL = new URL(window.location);
currentURL.searchParams.delete('redirect');

if (loginLinkDOM) {
    loginLinkDOM.href = `${baseURL}login?redirect=${encodeURIComponent(currentURL)}`;
    registerLinkDOM.href = `${baseURL}register?redirect=${encodeURIComponent(currentURL)}`;
    if (loginDanhGiaDOM) {
        loginDanhGiaDOM.href = `${baseURL}login?redirect=${encodeURIComponent(currentURL)}`;
    }
}

if (logOutLinkDOM) {
    logOutLinkDOM.addEventListener('click', async (event) => {
        event.preventDefault();
        await axios.post(`${baseURL}logout`);
        location.reload();
    })
}

const addToCart = async (formData) => {
    try {
        await axios.post(`${baseURL}api/v1/giohang/them`, formData);
        thongBao('Thêm vào giỏ hàng thành công');
    } catch (error) {
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}

const removeFromCart = async (formData, khongThongBao = false) => {
    try {
        await axios.post(`${baseURL}api/v1/giohang/xoa`, formData);
        if (khongThongBao) {
            return;
        }
        thongBao('Xóa khỏi giỏ hàng thành công');
    } catch (error) {
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}

const updateCart = async (formData) => {
    try {
        const { data: { soLuong } } = await axios.post(`${baseURL}api/v1/giohang/capnhat`, formData);
        return soLuong;
    } catch (error) {
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
        return error.response.data.soLuong ?? 1;
    }
}

const addToFav = async (formData, khongThongBao = false) => {
    try {
        const { data: { message } } = await axios.post(`${baseURL}api/v1/fav/them`, formData);
        if (khongThongBao) {
            return true;
        }
        thongBao(message);
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
        return false;
    }
}

const removeFromFav = async (formData) => {
    try {
        const { data: { message } } = await axios.post(`${baseURL}api/v1/fav/xoa`, formData);
        thongBao(message);
        showSanPhamYeuThich();
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}

mainDOM.addEventListener('click', async (event) => {
    const target = event.target;
    const formData = new FormData();
    if (target.classList.contains('add-to-cart-btn')) {
        formData.append('maSanPham', target.dataset.masanpham);
        await addToCart(formData);
        return;
    }
    if (target.classList.contains('remove-from-cart-btn')) {
        formData.append('maSanPham', target.dataset.masanpham);
        await removeFromCart(formData);
        showGioHang();
        return;
    }
    if (target.classList.contains('tang-gio-hang') || target.classList.contains('giam-gio-hang')) {
        const cartDivDOM = target.closest('.cart-div');
        const soLuongInputDOM = cartDivDOM.querySelector('.so-luong-cart-input');
        const tangGioHangDOM = cartDivDOM.querySelector('.tang-gio-hang');
        const giamGioHangDOM = cartDivDOM.querySelector('.giam-gio-hang');
        const tongTienThisCartDOM = cartDivDOM.querySelector('.tong-tien-cart');
        const donGia = soLuongInputDOM.dataset.dongia;

        soLuongInputDOM.disabled = true;
        tangGioHangDOM.disabled = true;
        giamGioHangDOM.disabled = true;

        formData.append('maSanPham', soLuongInputDOM.dataset.masanpham);
        if (target.classList.contains('tang-gio-hang')) {
            formData.append('soLuong', parseInt(soLuongInputDOM.value) + 1);
        }
        else {
            formData.append('soLuong', parseInt(soLuongInputDOM.value) - 1);
        }

        soLuongInputDOM.value = await updateCart(formData);
        const tongTien = parseInt(soLuongInputDOM.value) * parseInt(donGia);
        tongTienThisCartDOM.innerText = tongTien.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        await getTongTienVaSoLuong();
        soLuongInputDOM.disabled = false;
        tangGioHangDOM.disabled = false;
        giamGioHangDOM.disabled = false;
        return;
    }
    if (target.classList.contains('add-to-fav-btn')) {
        formData.append('maSanPham', target.dataset.masanpham);
        await addToFav(formData);
        return;
    }
    if (target.classList.contains('remove-from-fav-btn')) {
        formData.append('maSanPham', target.dataset.masanpham);
        await removeFromFav(formData);
        return;
    }
    if (target.classList.contains('move-to-fav-btn')) {
        formData.append('maSanPham', target.dataset.masanpham);
        const check = await addToFav(formData, true);
        if (check) {
            await removeFromCart(formData, true);
            showGioHang();
            thongBao('Đã chuyển sang danh sách yêu thích');
        }
    }
});


const loaiSPListDOM = document.querySelector('.loaiSanPham');
const showLoaiSPList = async () => {

    try {
        const { data: { loaiSanPhams } } = await axios.get(`${baseURL}api/v1/category_have_product`);
        loaiSPListDOM.innerHTML = buildOptions(
            loaiSanPhams,
            'maLoaiSanPham',
            'tenLoaiSanPham',
            'Tất cả',
            false
        );
    } catch (error) {
        console.log(error);
    }
}

const tltMainSearchFormDOM = document.querySelector('.tlt-main-search-form');

if (tltMainSearchFormDOM) {
    showLoaiSPList();
    const params = window.location.search;
    const search = new URLSearchParams(params).get('q') ?? "";
    tltMainSearchFormDOM.querySelector('input[name="q"]').value = search;
    tltMainSearchFormDOM.addEventListener('submit', async (event) => {
        event.preventDefault();
        const formData = new FormData(tltMainSearchFormDOM);
        const query = formData.get('q');
        const category = formData.get('cat');
        if (formData.get('q') === "") {
            return;
        }
        if (category === "") {
            window.location.href = `${baseURL}search?q=${encodeURIComponent(query)}`;
        } else {
            window.location.href = `${baseURL}category/${category}?search=${encodeURIComponent(query)}`;
        }
    })
}