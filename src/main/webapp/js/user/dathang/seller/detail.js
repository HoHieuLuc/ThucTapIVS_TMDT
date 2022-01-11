const thongTinNguoiDatDOM = document.querySelector('.thongTinNguoiDat');
const chiTietDatHangDOM = document.querySelector('.chiTietDatHang');
const params = window.location.pathname.split('/').slice(0);
const id = params.at(-1);

const showChiTietDatHang = async (skip = false) => {
    try {
        const { data: {
            thongTinNguoiDat, chiTietDatHang
        } } = await axios.get(`${baseURL}api/v1/user/seller/dathang/${id}`);
        const {
            ten, so_dien_thoai, dia_chi, email, ngay_dat
        } = thongTinNguoiDat;

        const allChiTietDatHang = chiTietDatHang.map((item) => {
            const { ma_san_pham, so_luong_dat, anh, ma_nhan_hang,
                status, don_gia, ten_san_pham, tong_tien, so_luong,
            } = item;
            let chucNangHtml = "";
            if (status === -1) {
                chucNangHtml = `
                    <div class="d-flex justify-content-evenly text-danger">
                        Đơn hàng đã bị hủy
                    </div>
                `;
            }
            else if (status === 0) {
                chucNangHtml = `
                <button 
                    data-id="${ma_san_pham}" data-status="-1"
                    class="cap-nhat-btn d-block w-100 btn btn-danger"
                >
                    Từ chối
                </button>
                <button data-id="${ma_san_pham}" data-status="1"
                    class="cap-nhat-btn d-block w-100 btn btn-primary"
                >
                    Bắt đầu chuyển hàng
                </button>
                `;
            } else if (status === 1) {
                chucNangHtml = `
                <button 
                    data-id="${ma_san_pham}" data-status="0"
                    class="cap-nhat-btn d-block w-50 btn btn-danger"
                >
                    Ngừng chuyển hàng
                </button>
                `;
            } else if(status === 2){
                chucNangHtml = `
                    <div class="d-flex justify-content-evenly text-success">
                        Đã giao hàng
                    </div>
                `;
            }

            const donGiaVND = don_gia.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            const tongTienVND = tong_tien.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            return `
                <div class="row">
                    <div class="col-2">
                        <img 
                            src="${baseURL}images/product/${anh}" 
                            class="tlt-thumbnail rounded mx-auto d-block img-fluid"
                            style="height:11rem; width:11rem; object-fit: scale-down;"
                            alt="${ten_san_pham}"
                        >
                    </div>
                    <div class="col-10">
                        <div class="row">
                            <div class="col-12">
                                <a
                                    href="${baseURL}sanpham/${ma_san_pham}"
                                    class="text-decoration-none text-dark fw-bold"
                                >
                                    ${ten_san_pham}
                                </a> (còn ${so_luong})
                            </div>
                            <div class="col-2">
                                Đơn giá
                            </div>
                            <div class="col-10">
                                ${donGiaVND}
                            </div>
                            <div class="col-2">
                                Số lượng đặt
                            </div>
                            <div class="col-10">
                                ${so_luong_dat}
                            </div>
                            <div class="col-2">
                                Thành tiền
                            </div>
                            <div class="col-10">
                                ${tongTienVND}
                            </div>
                            <div class="col-2">
                                Mã nhận hàng
                            </div>
                            <div class="col-10">
                                ${ma_nhan_hang}
                            </div>
                            <div class="col-8 d-flex gap-2">
                                ${chucNangHtml}
                            </div>
                        </div>
                    </div>
                </div>        
            `;
        }).join('');
        if (!skip) {
            thongTinNguoiDatDOM.innerHTML = `
            <div class="col-2 fw-bold">Tên người đặt</div>
            <div class="col-10">${ten}</div>
            <div class="col-2 fw-bold">Địa chỉ</div>
            <div class="col-10">${dia_chi}</div>
            <div class="col-2 fw-bold">Số điện thoại</div>
            <div class="col-10">${so_dien_thoai}</div>
            <div class="col-2 fw-bold">Email</div>
            <div class="col-10">${email}</div>
            <div class="col-2 fw-bold">Ngày đặt</div>
            <div class="col-10">${ngay_dat}</div>
        `;
        }
        chiTietDatHangDOM.innerHTML = allChiTietDatHang;
    } catch (error) {
        console.log(error);
        const { response: { data: { message } } } = error;
        thongBao(message ?? 'Có lỗi xảy ra', true);
    }
}

showChiTietDatHang();

const capNhatStatus = async (maSanPham, status) => {
    const formData = new FormData();
    formData.append('maSanPham', maSanPham);
    formData.append('status', status);
    try {
        await axios.post(`${baseURL}api/v1/user/seller/dathang/${id}/capnhat`, formData);
        showChiTietDatHang(true);
    } catch (error) {
        console.log(error);
        const { response: { data: { message } } } = error;
        thongBao(message ?? 'Có lỗi xảy ra', true);
    }
}

chiTietDatHangDOM.addEventListener('click', async (event) => {
    const eventTarget = event.target;
    const { classList } = eventTarget;
    if (classList.contains('cap-nhat-btn')) {
        const { dataset: { id: maSanPham, status } } = eventTarget;
        capNhatStatus(maSanPham, status);
    }
});