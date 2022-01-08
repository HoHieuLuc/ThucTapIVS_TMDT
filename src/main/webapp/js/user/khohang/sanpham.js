const params = window.location.pathname.split('/').slice(0);
const maSanPham = params[params.length - 1];

const tenDOM = document.querySelector('#ten');
const loaiSanPhamDOM = document.querySelector('#loaiSanPham');
const ngayDangDOM = document.querySelector('#ngayDang');
const moTaDOM = document.querySelector('#moTa');
const giaDOM = document.querySelector('#gia');
const xepHangDOM = document.querySelector('#xepHang');
const soLuongDOM = document.querySelector('#soLuong');
const soLuongDaBanDOM = document.querySelector('#soLuongDaBan');
const tinhTrangDOM = document.querySelector('#tinhTrang');

const chucNangDOM = document.querySelector('#chucNang');

const anhSanPhamDOM = document.querySelector('#anhSanPham');

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

const showSanPham = async (skip = false) => {
    try {
        const { data: { sanpham } } = await axios.get(`${baseURL}api/v1/user/sanpham/${maSanPham}`);
        const { ten_san_pham, gia, so_luong, so_luong_da_ban,
            xep_hang, status, mo_ta, ngay_dang, ten_loai_sp, anhSanPhams
        } = sanpham;
        const ngayDang = `${ngay_dang.date.day}/${ngay_dang.date.month}/${ngay_dang.date.year}`;
        const giaVND = gia.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND"
        });
        if(status === 0) {
            tinhTrangDOM.innerHTML = 'Trong kho';
            chucNangDOM.innerHTML = `
                <button class="xoa-btn btn btn-danger d-block w-100">Xóa</button>
                <button class="yeu-cau-duyet-btn btn btn-success d-block w-100">Yêu cầu duyệt</button>
            `;
        } 
        if(status === 1) {
            tinhTrangDOM.innerHTML = 'Đang chờ duyệt';
            chucNangDOM.innerHTML = `
                <button class="huy-btn btn btn-danger d-block w-100">Hủy yêu cầu duyệt</button>
            `;
        }
        if(status === 2) {
            tinhTrangDOM.innerHTML = 'Đang bán';
            chucNangDOM.innerHTML = `
                <button class="huy-btn btn btn-danger d-block w-100">Ngừng bán</button>
            `;
        }
        if (skip){
            return;
        }
        tenDOM.textContent = ten_san_pham;
        loaiSanPhamDOM.textContent = ten_loai_sp;
        ngayDangDOM.textContent = ngayDang;
        moTaDOM.textContent = mo_ta;
        giaDOM.textContent = giaVND;
        xepHangDOM.textContent = xep_hang;
        soLuongDOM.textContent = so_luong;
        soLuongDaBanDOM.textContent = so_luong_da_ban;

        const anhSanPhamData = anhSanPhams.map((anhSanPham) => {
            return {
                src: `${baseURL}images/product/${anhSanPham}`,
                thumb: `${baseURL}images/product/${anhSanPham}`
            }
        });
        buildInlineGallery(anhSanPhamData).openGallery();
    } catch (error) {
        console.log(error);
        const { data: { message } } = error.response;
        thongBao(message ?? "Có lỗi xảy ra", true);
    }
}

showSanPham();

const changeStatus = async (status) => {
    try {
        const formData = new FormData();
        formData.append('maSanPham', maSanPham);
        formData.append('status', status);
        const { data: { message } } = await axios.post(`${baseURL}api/v1/user/sanpham/changestatus`, formData);
        await showSanPham(true);
        thongBao(message);
    } catch (error) {
        console.log(error);
        const { data: { message } } = error.response;
        thongBao(message ?? "Có lỗi xảy ra", true);
    }
}

chucNangDOM.addEventListener('click', async (event) => {
    const eventTarget = event.target;
    const targetClassList = eventTarget.classList;
    if(!targetClassList.contains('xoa-btn') && !targetClassList.contains('yeu-cau-duyet-btn') && !targetClassList.contains('huy-btn')) {
        return;
    }
    const formData = new FormData();
    formData.append('maSanPham', maSanPham);
    if(eventTarget.classList.contains('xoa-btn')) {
        await changeStatus(-1);
        window.location.href = `${baseURL}user/sanpham`;
        return;
    }
    if(eventTarget.classList.contains('yeu-cau-duyet-btn')) {
        await changeStatus(1);
        return;
    }
    if(eventTarget.classList.contains('huy-btn')) {
        await changeStatus(0);
    }
});