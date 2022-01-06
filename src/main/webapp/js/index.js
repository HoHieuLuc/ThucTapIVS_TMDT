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

const removeFromCart = async (formData) => {
    try {
        await axios.post(`${baseURL}api/v1/giohang/xoa`, formData);
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
        const soLuongInputDOM = target.parentElement.querySelector('.so-luong-cart-input');
        const tongTienDOM = target.parentElement.parentElement.parentElement.querySelector('.tong-tien-cart');
        const donGia = soLuongInputDOM.dataset.dongia;
        soLuongInputDOM.disabled = true;
        target.disabled = true;
        formData.append('maSanPham', soLuongInputDOM.dataset.masanpham);
        if (target.classList.contains('tang-gio-hang')) {
            formData.append('soLuong', parseInt(soLuongInputDOM.value) + 1);
        }
        else {
            formData.append('soLuong', parseInt(soLuongInputDOM.value) - 1);
        }
        soLuongInputDOM.value = await updateCart(formData);
        const tongTien = parseInt(soLuongInputDOM.value) * parseInt(donGia);
        tongTienDOM.innerText = tongTien.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        soLuongInputDOM.disabled = false;
        target.disabled = false;
        return;
    }
    if (target.classList.contains('add-to-fav-btn')) {
        console.log("add to favorite");
        return;
    }
    if (target.classList.contains('move-to-fav-btn')) {
        console.log("move to favorite");
    }
});
