const sanPhamDOM = document.querySelector('#sanpham');
const params = window.location.pathname.split('/').slice(0);
const maSanPham = params[params.length - 1];

const showSanPham = async () => {
    sanPhamDOM.textContent = "Đang tải...";
    try {
        const { data: { sanpham } } = await axios.get(`${baseURL}api/v1/user/sanpham/${maSanPham}`);
        sanPhamDOM.textContent = JSON.stringify(sanpham);
    } catch (error) {
        sanPhamDOM.textContent = error.response.data.message;
    }
}

showSanPham();