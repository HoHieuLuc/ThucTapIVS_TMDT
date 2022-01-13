const thongTinNguoiDatDOM = document.querySelector('.thongTinNguoiDat');
const avatarDOM = document.querySelector('.avatar');
const tenNguoiBanDOM = document.querySelector('.tenNguoiBan');
const ngayDatDOM = document.querySelector('.ngayDat');
const soDienThoaiDOM = document.querySelector('.soDienThoai');
const emailDOM = document.querySelector('.email');
const storeLinkDOM = document.querySelector('.storeLink');

const anhSanPhamDOM = document.querySelector('.anhSanPham');
const tenSanPhamDOM = document.querySelector('.tenSanPham');
const soLuongDatDOM = document.querySelector('.soLuongDat');
const donGiaDOM = document.querySelector('.donGia');
const tongTienDOM = document.querySelector('.tongTien');
const chucNangDOM = document.querySelector('.chucNang');
    

const params = window.location.pathname.split('/').slice(0);
const maSanPham = params.at(-1);
const id = params.at(-2);

const showChiTietDatHang = async (skip = false) => {
    try {
        const { data: {
            chiTietDatHang
        } } = await axios.get(`${baseURL}api/v1/user/buyer/dathang/${id}/${maSanPham}`);
        const {
            username, email, so_dien_thoai, avatar, ten, so_luong_dat, status,
            tong_tien, ten_san_pham, ngay_dat, don_gia, anh
        } = chiTietDatHang;
        let chucNangHtml = "";
        if (status === -1){
            chucNangHtml = `
                <div class="d-flex justify-content-evenly text-danger">
                    Đơn hàng đã bị hủy
                </div>
            `;
        }
        else if (status === 0) {
            chucNangHtml = `
                <button class="cap-nhat-btn d-block w-50 btn btn-warning">
                    Hủy đơn hàng
                </button>
                <button class="disabled d-block w-50 btn btn-primary">
                    Đang chờ...
                </button>
            `;
        }
        else if (status === 1) {
            chucNangHtml = `
                <div class="input-group mb-3">
                    <input id="maNhanHang" type="text" class="form-control border border-success" placeholder="Mã nhận hàng">
                    <button class="cap-nhat-btn btn btn-success w-50" type="button">Đã nhận được hàng</button>
                </div>
            `;
        } else if (status === 2) {
            chucNangHtml = `
                <div class="d-flex justify-content-evenly text-success">
                    Đã nhận được hàng
                </div>
            `;
        }
        chucNangDOM.innerHTML = chucNangHtml;
        if(skip){
            return;
        }

        const donGiaVND = don_gia.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        const tongTienVND = tong_tien.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        avatarDOM.src = `${baseURL}images/user/${avatar}`;
        tenNguoiBanDOM.innerText = ten;
        ngayDatDOM.innerText = ngay_dat;
        soDienThoaiDOM.innerText = so_dien_thoai;
        emailDOM.innerText = email;
        storeLinkDOM.href = `${baseURL}store/${username}`;

        anhSanPhamDOM.src = `${baseURL}images/product/${anh}`;
        tenSanPhamDOM.innerText = ten_san_pham;
        tenSanPhamDOM.href = `${baseURL}sanpham/${maSanPham}`;
        soLuongDatDOM.innerText = so_luong_dat;
        donGiaDOM.innerText = donGiaVND;
        tongTienDOM.innerText = tongTienVND;

    } catch (error) {
        console.log(error);
        const { response: { data: { message } } } = error;
        thongBao(message ?? 'Có lỗi xảy ra', true);
    }
}

showChiTietDatHang();


chucNangDOM.addEventListener('click', async (event) => {
    const eventTarget = event.target;
    const { classList } = eventTarget;
    const maNhanHangDOM = document.querySelector('#maNhanHang');
    let maNhanHang = "";
    if (maNhanHangDOM){
        maNhanHang = maNhanHangDOM.value;
    }
    if (classList.contains('cap-nhat-btn')) {
        try {
            const formData = new FormData();
            formData.append('maNhanHang', maNhanHang);
            await axios.post(`${baseURL}api/v1/user/buyer/dathang/${id}/${maSanPham}/capnhat`, formData);
            showChiTietDatHang(true);
        } catch (error) {
            console.log(error);
            const { response: { data: { message } } } = error;
            thongBao(message ?? 'Có lỗi xảy ra', true);
        }
    }
});