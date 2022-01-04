const loginLinkDOM = document.querySelector('#login');
const registerLinkDOM = document.querySelector('#register');
const logOutLinkDOM = document.querySelector('#logout');
const loginDanhGiaDOM = document.querySelector('#loginDanhGia');

const bodyDOM = document.querySelector('body');

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
        await axios.post(`${baseURL}api/v1/giohang/capnhat`, formData);
    } catch (error) {
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}


bodyDOM.addEventListener('click', async (event) => {
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
        return;
    }
    if (target.classList.contains('update-cart-btn')) {
        //await updateCart(target.dataset.masanpham, target.previousElementSibling.value);
        console.log("update cart");
        return;
    }
    if (target.classList.contains('add-to-fav-btn')) {
        console.log("add to favorite");
    }
});
